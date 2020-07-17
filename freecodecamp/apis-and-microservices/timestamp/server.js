// server.js
// where your node app starts

// init project
var express = require('express');
var app = express();

// enable CORS (https://en.wikipedia.org/wiki/Cross-origin_resource_sharing)
// so that your API is remotely testable by FCC 
var cors = require('cors');
app.use(cors({optionSuccessStatus: 200}));  // some legacy browsers choke on 204

// http://expressjs.com/en/starter/static-files.html
app.use(express.static('public'));

// http://expressjs.com/en/starter/basic-routing.html
app.get("/", function (req, res) {
  res.sendFile(__dirname + '/views/index.html');
});


// your first API endpoint... 
app.get("/api/hello", function (req, res) {
  res.json({greeting: 'hello API'});
});

app.get("/api/timestamp/:date_string?", function (req, res) {
  
  let date_string = req.params.date_string;
  let date_str;

  try {

    if (typeof date_string === "undefined" || date_string.length === 0) {
      date_str = new Date();
    } else if (isFinite(date_string)) {
      date_str = new Date(parseInt(date_string));
    } else {
      date_str = new Date(date_string);
    }
    
    if (date_str.toString() === "Invalid Date") {
      return res.json({
        "error": "Invalid Date"
      });
    }

    return res.json({
      unix: date_str.getTime(),
      utc: date_str.toUTCString()
    });

  } catch (err) {

    return res.json({ "error": "Invalid Date" });
  }
});

// listen for requests :)
var listener = app.listen(process.env.PORT, function () {
  console.log('Your app is listening on port ' + listener.address().port);
});