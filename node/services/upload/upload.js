

module.exports = {

	upload: function upload(data) {
	const MongoClient = require('mongodb').MongoClient;

	const dbName = 'DenmarkTraffic';
	const url = 'mongodb://localhost:27017';
	const dbCollection = 'ais_prod';

	MongoClient.connect(url , {useUnifiedTopology: true} )
		.then( function(clt){
			const db = clt.db(dbName);
			db.collection(dbCollection).insertOne(data)
				.then( function(){
				clt.close();
				})
			console.log("Successful upload");
		})

	}
}
