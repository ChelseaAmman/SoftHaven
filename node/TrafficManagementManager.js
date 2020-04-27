
const http = require('http');

const hostname = '127.0.0.1';
const port = 3000;
const upload = require('./services/upload/upload');


const server = http.createServer((req, res) => {
	if (req.method === "POST") {
		req_url = new URL(req.url, `http://${req.headers.host}`);


		if ( /upload/g.test(req_url.pathname)) {
			let body = '';
			req.on('data', chunk => {
				body += chunk.toString();
			});
			req.on('end', () => {
				console.log(body);
				upload.upload(body);
				res.end('ok');
			});
			}


		else if ( /query/g.test(req_url.pathname)) {
			var test = 7;
		}

		}
	});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});

// var shipdata = [[
// 	{
// 	  "IMO": "1000000",
// 	  "Flag": "Finland",
// 	  "Name": "Triton Star",
// 	  "Built": "2012",
// 	  "CallSign": "null",
// 	  "Length": "null",
// 	  "Breadth": "null",
// 	  "Tonnage": "41101",
// 	  "MMSI": "367303060",
// 	  "Type": "Bulk Carrier",
// 	  "Owner_Code": "null"
// 	},
// 	{
// 	  "IMO": "1000001",
// 	  "Flag": "Indonesia",
// 	  "Name": "Spob Athamara",
// 	  "Built": "null",
// 	  "CallSign": "null",
// 	  "Length": "47",
// 	  "Breadth": "6",
// 	  "Tonnage": "null",
// 	  "MMSI": "525000003",
// 	  "Type": "Product Tanker",
// 	  "Owner_Code": "null"
// 	},
// 	{
// 	  "IMO": "1000019",
// 	  "Flag": "United Kingdom",
// 	  "Name": "Lady K Ii",
// 	  "Built": "1961",
// 	  "CallSign": "3FOV",
// 	  "Length": "57",
// 	  "Breadth": "8",
// 	  "Tonnage": "551",
// 	  "MMSI": "235095435",
// 	  "Type": "Yacht",
// 	  "Owner_Code": "11715"
// 	}
//   ]]

function sendData(url,data){
	var xhr = new XMLHttpRequest();
	xhr.open("POST", url, true);
	xhr.setRequestHeader('Content-Type', 'json');
	xhr.send(JSON.stringify({
    	data
}));
}

function getCoordinates(mmsi, url){
	let C = { "mmsi" : mmsi };
	// C = notAfunction({ "mmsi" : mmsi });
	sendData(url,C)
	return ;
}

function getEntrance(mmsi, url){
	let E = { "mmsi" : mmsi };
	// C = notAfunction({ "mmsi" : mmsi });
	sendData(url,E)
	return E;
}

function getDeparture(mmsi, url){
	let D = { "mmsi" : mmsi };
	// C = notAfunction({ "mmsi" : mmsi });
	sendData(url,D)
	return D;
	
}

function getMessage(mmsi, url){
	let M = { "mmsi" : mmsi };
	// C = notAfunction({ "mmsi" : mmsi });
	sendData(url,M)
	return M;
}


function getShip(mmsi, url){
	let s = getShip.find( ({ MMSI }) => MMSI === mmsi )
	sendData(url,s)
	return;
}

function getShipData(mmsi, property){
	switch(property){
		case "IMO":
			var p = getShip(mmsi).IMO
			break;
		case "Flag":
				var p = getShip(mmsi).Flag
			break;
		case "Name":
				var p = getShip(mmsi).Name
			break;
		case "Built":
				var p = getShip(mmsi).Built
			break;
		case "CallSign":
				var p = getShip(mmsi).CallSign
			break;
		case "Length":
				var p = getShip(mmsi).Length
			break;
		case "Breadth":
				var p = getShip(mmsi).Breadth
			break;
		case "Tonnage":
				var p = getShip(mmsi).Tonnage
			break;
		case "Type":
				var p = getShip(mmsi).Type
			break;
		case "Owner_Code":
				var p = getShip(mmsi).Owner_Code
			break;						
	}
	return p;
}
