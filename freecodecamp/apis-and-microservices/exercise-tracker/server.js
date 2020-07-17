const express = require('express')
const app = express()
const bodyParser = require('body-parser')

const cors = require('cors')

var mongo = require('mongodb');
const mongoose = require('mongoose')
mongoose.connect(process.env.DB_URI, {
  useNewUrlParser: true,
  useUnifiedTopology: true
});

app.use(cors())

app.use(bodyParser.urlencoded({extended: false}))
app.use(bodyParser.json())


app.use(express.static('public'))
app.get('/', (req, res) => {
  res.sendFile(__dirname + '/views/index.html')
});

let UserExerciseSchema = new mongoose.Schema({
  description: String,
  duration: Number,
  date: Date
});

let UserSchema = new mongoose.Schema({
  username: String,
  exercises: [UserExerciseSchema]
});

let User = new mongoose.model("User", UserSchema);


app.post("/api/exercise/new-user", function (req, res) {
  
  let body = req.body;
  let user = new User({
    username: body.username
  });

  user.save(function (err, data) {
    if (err) {
      return res.send(err);
    }
    return res.json({username: body.username, _id: data._id});
  });
});

app.get("/api/exercise/users", function (req, res) {
  
  User.find({})
    .select({ _id: 1, username: 1 })
    .exec(function (err, data) {
      if (err) {
        return res.send(err);
      }
      return res.send(data);
    })
});

app.post("/api/exercise/add", function (req, res) {
  
  let body = req.body;
  body.date = body.date ? new Date(body.date) : new Date();

  let exercise = {
    description: body.description,
    duration: parseInt(body.duration),
    date: new Date(body.date)
  };

  User.findByIdAndUpdate(mongoose.Types.ObjectId(body.userId), { '$push': {exercises: exercise}}, function (err, data) {
    
    exercise.date = exercise.date.toDateString();
    exercise._id = data._id;
    exercise.username = data.username;
    return res.json(exercise);
  });
});

app.get("/api/exercise/log", function (req, res) {
  
  let user_id = req.query.userId;

  let query = User.findOne({
    _id: mongoose.Types.ObjectId(user_id)
  });

  let to_date, from_date, limit;
  if (req.query.from) {
    from_date = new Date(req.query.from);
  }
  if (req.query.to) {
    to_date = new Date(req.query.to);
  }
  if (req.query.limit) {
    limit = parseInt(req.query.limit);
  }
    
  query.exec(function (err, data) {
    
    if (err) {
      return res.send(err);
    }

    let result = {
      _id: data._id,
      username: data.username,
      count: data.exercises.length
    }
    let log = [];
    for (let doc of data.exercises) {

      if (from_date && from_date > doc.date) {
        continue;
      } else if (to_date && to_date < doc.date) {
        continue;
      } else if (limit && log.length === limit) {
        break;
      }

      log.push({
        description: doc.description,
        duration: doc.duration,
        date: doc.date.toDateString()
      })
    }
    result.log = log;
    return res.send(result);
  });
});


// Not found middleware
app.use((req, res, next) => {
  return next({status: 404, message: 'not found'})
})

// Error Handling middleware
app.use((err, req, res, next) => {
  let errCode, errMessage

  if (err.errors) {
    // mongoose validation error
    errCode = 400 // bad request
    const keys = Object.keys(err.errors)
    // report the first validation error
    errMessage = err.errors[keys[0]].message
  } else {
    // generic or custom error
    errCode = err.status || 500
    errMessage = err.message || 'Internal Server Error'
  }
  res.status(errCode).type('txt')
    .send(errMessage)
})

const listener = app.listen(process.env.PORT || 3000, () => {
  console.log('Your app is listening on port ' + listener.address().port)
})
