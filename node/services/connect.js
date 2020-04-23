

module.exports = {



	uri: function(){
		const dbName = 'DenmarkTraffic';
		const url = 'mongodb://localhost:27017';
		return url + "/" + dbName;
		},

	connect: function connect() {
	const MongoClient = require('mongodb').MongoClient;

	const dbName = 'DenmarkTraffic';
	const url = 'mongodb://localhost:27017';
	const dbCollection = 'ais_prod';
		MongoClient.connect( url + "/" + dbName , { useUnifiedTopology: true, serverSelectionTimeoutMS: 5000 },
				function( err, clt ){
					if (err){
						console.log("Could not connect!");
						process.exit(2);
					}
					console.log("Successful connection!");
					clt.close();
				});
	}

}
