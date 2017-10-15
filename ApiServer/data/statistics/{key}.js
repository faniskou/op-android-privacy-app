"use strict";
var Mockgen = require("../mockgen.js");
var errorsmsg = require("../errorsmessages.js");
var uuid = require('uuid');

// Set some defaults
db.defaults({ statistics: [], appidentity: {} }).write();

module.exports = {
  /**
     * summary: Get all statistics based on a key
     * description: 
     * parameters: key
     * produces: application/json
     * responses: 200, 400, 404
     * operationId: getpolicy
     */
  get: {
    200: function(req, res, callback) {
        if (
          db
            .get("statistics")
            .filter({ name: req.params.key })
            .size()
            .value() == 0
        ) {
          res.status(404).send(errorsmsg[404]());
        } else {
          try {
            var datares ={"Statistics": [] };
             datares.Statistics = db
              .get("statistics")
              .filter({ name: req.params.key})
              .value();
            res.status(200).send(datares);
          } catch (err) {
            res.status(500).send(errorsmsg[500]());
          }
        }
    }
  },
  /**
     * summary: Add a new policy statistic
     * description: 
     * parameters: key, body
     * produces: application/json
     * responses: 200, 405
     * operationId: addpolicy
     */
  put: {
    200: function(req, res, callback) {
      req.body.name = req.params.key;
      req.body.id = uuid.v4();

      if (
        db
          .get("statistics")
          .filter({ name: req.body.name, domainurl: req.body.domainurl })
          .size()
          .value() > 0
      ) {
        res.status(409).send(errorsmsg[409]());
      } else {
        try {
          var datares = db
            .get("statistics")
            .push(req.body)
            .write();
          res.status(200).send(errorsmsg[200]());
        } catch (err) {
          res.status(500).send(errorsmsg[500]());
        }
      }
   }
  }
};
