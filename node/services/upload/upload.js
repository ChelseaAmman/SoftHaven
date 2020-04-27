

module.exports = {

	upload: function upload(data) {
	const MongoClient = require('mongodb').MongoClient;
	const dbName = 'DenmarkTraffic';
	const url = 'mongodb://localhost:27017';
	const dbCollection = 'ais_prod';

	MongoClient.connect(url , {useUnifiedTopology: true} )
		.then( function(clt){
			const mdb = clt.db(dbName).collection(dbCollection);
			if (data.length > 25) { //TODO: find a better way to filter empty documents
				mdb.insertMany(JSON.parse(data))
					.then( function(){
					clt.close();
					})
			}
			else {
				clt.close();
			}
			console.log("Successful upload");
		});

	}
}
