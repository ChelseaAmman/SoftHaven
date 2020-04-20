/***
 * AIS_TRANSMITTER: feeding AIS messages to SoftHaven.
 *
 * Revisions:
 *	1.0 	NPR	03/07/2020	Initial version.
 *	1.1 	NPR	03/15/2020	Use a single MongoDB client for all requests.
 *	1.2	NPR	03/16/2020	The POST request passes the timestamp as a parameter; documentation added.
 *
 * This utility replays one month worth of AIS data transmissions in the area to be covered by the Traffic Monitoring module
 * (55.51S, 55.81N, 12.50W, 13.0755E). Starting on the 1st of September 2018, it posts every second to your REST service 
 * all AIS messages found for that date and time, in a JSON array of the form:
 *
 *
 * [ { _id: '5e62e293badaafdc541085ff', Class: 'Class A', ICES_Rect: 4421, MMSI: 219385000, PositionReport: { CoG: 80.3, NavigationalStatus: 'Under way using engine', Position: [Object], SoG: 0 }, StaticData: { A: 10, B: 20, Breadth: 6, C: 3, CallSign: 'OXXM', D: 3, DataSourceType: 'AIS', Destination: 'COPENHAGEN', IMO: 5161158, Length: 30, Name: 'BJORNSHOLM', PositionFixingDevice: 'GPS', VesselType: 'Passenger' }, Timestamp: '2018-09-11T10:38:00.000Z' },
  { _id: '5e62e293badaafdc54108602', Class: 'Class A', ETA: '2018-09-13T18:00:00.000Z', ICES_Rect: 4421, MMSI: 218759000, PositionReport: { CoG: 174.2, Heading: 173, NavigationalStatus: 'Under way using engine', Position: [Object], RoT: 0, SoG: 8.5 }, StaticData: { A: 74, B: 8, Breadth: 13, C: 9, CallSign: 'DFYT', D: 4, DataSourceType: 'AIS', Destination: 'LV RIX', IMO: 8518558, Length: 82, Name: 'HALLAND', PositionFixingDevice: 'GPS', VesselType: 'Cargo' }, Timestamp: '2018-09-11T10:38:00.000Z' },
  ... ]
 *
 *
 *
 * The default URL for the target service is:
 * 
 *  http://localhost:3000/TrafficService/<timestamp>
 * 
 * where <timestamp> represents the date and time of the messages posted through the request (a string like this: "2018-09-11T10:39:06.000Z").
 *
 * You may configure the script parameters:
 *
 * + SERVICE_NAME: replace the default value ('TrafficService') with a name of your choice
 * + INTERVAL: modify this value (in milliseconds) to accelerate (ex. 500), or slow down (ex. 2000) the simulation
 * + START_DATETIME: modify to start the simulation later than 09/01/01-00:00:00.
 * 
 **/

const http = require("http");
const MongoClient = require('mongodb').MongoClient;

const dbName = 'DenmarkTraffic'; // do not modify
const url = 'mongodb://localhost:27017'; // do not modify
const dbCollection = 'aisdk_201809'; // do not modify

// Modify to fit your service implementation
const SERVICE_NAME="TrafficService";

// Time interval between two POST requests (1000 ms for real-time, 
// faster (ex. 500 ms) to accelerate the simulation, or slower (ex. 2000s)...
var INTERVAL=1000;
// Drop the POST request if the server does not answer within this timeframe
var TIMEOUT=4;


START_DATETIME = new Date("2018-09-11T10:38:00Z");


// Testing access to the database
MongoClient.connect( url + "/" + dbName , { useUnifiedTopology: true, serverSelectionTimeoutMS: 5000 },
			function( err, clt ){
				if (err){
					console.log("Could not connect! Verify the following:" +
					"\n\t - MongoDB is up and running;" +
					"\n\t - the " + dbName + " database has been uploaded." ); 
					process.exit(2);
				}
				console.log("Successful connection!");
				clt.close();
			});


// Fetching the documents
MongoClient.connect(url , {useUnifiedTopology: true} )
	.then( function(clt){
		
		offset = 0;
		const db = clt.db(dbName);
		
		setInterval(
			() => {
			
			let docs = [];
			let mseconds = START_DATETIME.getTime() + offset*1000;
			offset++;
			let timestamp = new Date();
			timestamp.setTime( mseconds );
		
			db.collection(dbCollection).find({"Timestamp": { $eq: timestamp }}, {_id:0}).toArray()
			.then(
				function(docs){
					console.log("Found " + docs.length + " docs");
					return make_post_request( JSON.stringify( docs ), timestamp);
				}
			).then( 
				function(response){ 
					console.log("Placed POST request");
					console.log( response );
				}
			).catch( function(err){ console.log(err)});
		}, INTERVAL );
		})
		.catch( function(err){ console.log(err)});
			
		

// Send the messages to the server: the target service is of the form:
//
//     http://localhost:3000/TrafficService/2018-09-11T10:39:05.000Z
//
// The transmitter's behaviour is to place a request every second, even if
// no new message has been found: in this case, an empty JSON array gets
// posted - passing the timestamp as a URL parameter allows for handling this
// request for other purpose (deleting stale data, for example).
function make_post_request( data, timestamp ){
	console.log(timestamp);
	return new Promise ( function(resolve, reject){	
		let req = http.request({
				hostname: 'localhost',
				port: 3000,
				path: '/' + SERVICE_NAME + '/' + timestamp.toJSON(), 
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
					'Content-Length': data.length
				}
			}, 
			(resp) => { 
				resp.on('data', (d) => {
					resolve(d.toString());
				});
			});
		req.on('error', (error) => { 
			reject( error ); 
		});

		req.write(data);
		req.end();
	});
}



console.log("**************************************************************\n" +
	"Started ais_transmitter client:\n" + 
	"\ttarget service: http://localhost:3000/" + SERVICE_NAME + "\n" +
	"\tstarting date: " + START_DATETIME + 
	"\n(change the START_DATETIME constant in index.js for an earlier or later date)\n" +
	"*****************************************************************\n" );
