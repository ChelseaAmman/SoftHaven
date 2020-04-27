
module.exports = {

	query: function query(data) {
	const MongoClient = require('mongodb').MongoClient;
	const dbName = 'DenmarkTraffic';
	const url = 'mongodb://localhost:27017';
	const dbCollection = 'ais_prod';
	const query_mmsi = { MMSI: data.MMSI };

	function new_client(){
		return MongoClient.connect(url , {useUnifiedTopology: true}).then(query_mdb);

	}

	function query_mdb(mc) {
			const mdb = mc.db(dbName).collection(dbCollection);
			return mdb.find(query_mmsi).toArray().finally(mc.close);
	}
	return new_client();
	}
}
