'use strict';
var Mockgen = require('../../mockgen.js');
var errorsmsg = require("../../errorsmessages.js");


// Set some defaults
db.defaults({ statistics: [], appidentity: [] }).write();
/**
 * Operations on /statistics/{key}/{domainurl}
 */
module.exports = {
    /**
     * summary: Get one statistic for a domain and a key
     * description: 
     * parameters: key, domainurl
     * produces: application/json
     * responses: 200, 400, 404
     * operationId: getpolicyurl
     */
    get: {
        200: function (req, res, callback) {
            if (
                db.get("statistics")
                  .filter({ name: req.params.key  })
                  .filter({  domainurl: req.params.domainurl })
                  .size()
                  .value() == 0
              ) {
                res.status(404).send(errorsmsg[404]());
              } else {
                try {
                //  var datares ={"Statistics": [] };
                var   datares = db
                    .get("statistics")
                    .filter({ name: req.params.key,domainurl: req.params.domainurl })
                    .value();
                  res.status(200).send(datares[0]);
                } catch (err) {
                  res.status(500).send(errorsmsg[500]());
                }
              }
        }
    },
    /**
     * summary: Update an existing statistic
     * description: 
     * parameters: key, domainurl, body
     * produces: application/json
     * responses: 200, 405
     * operationId: updatePeturl
     */
    post: {
        200: function (req, res, callback) {
            if (
                db
                  .get("statistics")
                  .filter({ name: req.params.key,domainurl: req.params.domainurl })
                  .size()
                  .value() == 0
              ) {
                res.status(404).send(errorsmsg[404]());
              } else {
                try {
                  var  input=req.body;
                  delete input.id;
                  input.name = req.params.key;
                  input.domainurl = req.params.domainurl;
                  var datares ={"Statistics": [] };
                   datares.Statistics = db
                    .get("statistics")
                    .find({ name: req.params.key,domainurl: req.params.domainurl })
                    .assign(input)
                    .write()
                  res.status(200).send(datares);
                } catch (err) {
                  res.status(500).send(errorsmsg[500]());
                }
              }
        }
    }
};
