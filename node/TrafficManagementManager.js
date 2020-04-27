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
