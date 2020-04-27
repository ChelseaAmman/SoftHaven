var data = [] //TODO hook var data to backend/make get request
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
		}
	});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});
//TODO get array of objects/ all we need here is data
var data = XMLHttp.open("GET",'') //I am Unsure what to do here, there may be a better way???

function getShip(imo){
	var ship = data.find( ({ IMO }) => IMO === imo );
	return ship
}
//num is the IMO of the Ship, they all have different IMO's so I assumed it would be the best choice
function getProperty(num, shipProp){
	switch(shipProp) {
		case "Flag":
			var P = getShip(num).Flag;
		  break;
		case "Name":
			var P = getShip(num).Name;
		  break;
		case "Built":
			var P = getShip(num).Built;
		  break;
		case "CallSign":
			var P = getShip(num).CallSign;
		  break;  
		case "Length":
			var P = getShip(num).Length;
		  break;
		case "Breadth":
			var P = getShip(num).Breadth;
		  break;
		case "Tonnage":
			var P = getShip(num).Tonnage;
		  break;
		case "MMSI":
			var P = getShip(num).MMSI;
		  break;  
		case "Type":
			var P = getShip(num).Type;
		  break;
		case "Owner_Code":
			var P = getShip(num).Owner_Code;
		  break; 
		default:
		  var P = 'Invalid'
	  }
	console.log(P)
	return P;
}