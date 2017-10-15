var express = require('express');
var app = express();
var Http = require('http');
const swaggerUi = require('swagger-ui-express');
var BodyParser = require('body-parser');
var Swaggerize = require('swaggerize-express');
var Path = require('path');
const low = require("lowdb");
const FileSync = require("lowdb/adapters/FileSync");


const adapter = new FileSync("db.json");
global.db = low(adapter);

app.set('port', (process.env.PORT || 8000));


// views is directory for all template files

app.use(BodyParser.json());
app.use(BodyParser.urlencoded({
    extended: true
}));
const swaggerDocument = require('./config/swagger.heroku.json');
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocument));
app.use(Swaggerize({
    api: Path.resolve('./config/swagger.json'),
    handlers: Path.resolve('./handlers')
}));


app.listen(app.get('port'), function() {
  console.log('Node app is running on port', app.get('port'));
});
