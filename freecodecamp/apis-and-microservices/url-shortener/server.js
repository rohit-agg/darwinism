'use strict';

var express = require('express');
var mongo = require('mongodb');
var mongoose = require('mongoose');
let dns = require('dns');
let bodyParser = require("body-parser");

var cors = require('cors');

var app = express();

// Basic Configuration 
var port = process.env.PORT || 3000;

/** this project needs a db !! **/ 
mongoose.connect(process.env.DB_URI, {
  useNewUrlParser: true,
  useUnifiedTopology: true
});

let urlSchema = new mongoose.Schema({
  url: String,
  short_url: String
});

let Url = new mongoose.model("Url", urlSchema);

app.use(cors());
app.use(bodyParser.urlencoded({
  extended: 'false'
}));
app.use(bodyParser.json());

/** this project needs to parse POST bodies **/
// you should mount the body-parser here

app.use('/public', express.static(process.cwd() + '/public'));

app.get('/', function(req, res){
  res.sendFile(process.cwd() + '/views/index.html');
});

  
// your first API endpoint... 
app.get("/api/hello", function (req, res) {
  res.json({greeting: 'hello API'});
});

app.post("/api/shorturl/new", function (req, res) {
  
  let url = req.body.url;
  if (typeof url === "undefined" || url.length === 0) {
    return res.json({ "error": "invalid URL" });
  }

  let urlRegex = /^http(s)?:\/\/www\.([a-z]+\.[a-z]{3})(\/[a-z]+)*$/;
  let matcher = url.match(urlRegex);
  if (matcher == null) {
    return res.json({ "error": "invalid URL" });
  }

  dns.lookup(matcher[2], function(err1, address, family) {

    if(err1) {
      return res.json({ "error": "invalid URL" });
    }

    Url.countDocuments({}, function (err2, count) {
      
      if (err2) {
        return res.json({ "error": "invalid URL" });
      }

      let newUrl = {
        url: url,
        short_url: ++count
      };
      Url.create(newUrl, function (err3, data) {
        
        if (err3) {
          return res.json({ "error": "invalid URL" });
        }

        return res.json({"original_url": url, "short_url": count});
      });
    });
  });
}); 

app.get("/api/shorturl/:shorturl", function (req, res) {
  
  let short_url = req.params.shorturl;
  Url.findOne({ short_url: short_url }, function (err, data) {
    if (err) {
      return res.json({ error: "URL not found" });
    }

    return res.redirect(302, data.url);
  });
});

app.listen(port, function () {
  console.log('Node.js listening at port ' + port);
});