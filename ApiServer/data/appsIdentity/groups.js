'use strict';
var Mockgen = require('../mockgen.js');
var errorsmsg = require("../errorsmessages.js");
var unique = require('array-unique');

/**
 * Operations on /appsIdentity/groups
 */
module.exports = {
    /**
     * summary: Get all app groups
     * description: 
     * parameters: 
     * produces: application/json
     * responses: 200, 400, 404
     * operationId: getgroups
     */
    get: {
        200: function (req, res, callback) {
            if (
                db
                  .get("appidentity")
                  .size()
                  .value() == 0
              ) {
                res.status(404).send(errorsmsg[404]());
              } else {
                try {
                  var datares ={"groups": [] };
                   datares.groups = unique(db
                    .get("appidentity")
                    .map('name')                    
                    .value());
                  res.status(200).send(datares);
                } catch (err) {
                  res.status(500).send(errorsmsg[500]());
                }
              }
        }
        
    }
};
