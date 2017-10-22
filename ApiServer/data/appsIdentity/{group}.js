'use strict';
var Mockgen = require('../mockgen.js');
var errorsmsg = require("../errorsmessages.js");
var uuid = require('uuid');
/**
 * Operations on /appsIdentity/{group}
 */
module.exports = {
    /**
     * summary: Get all apps identities of a group
     * description: 
     * parameters: group
     * produces: application/json
     * responses: 200, 400, 404
     * operationId: getgroup
     */
    get: {
        200: function (req, res, callback) {
            if (
                db
                  .get("appidentity")
                  .filter({ name: req.params.group })                  
                  .size()
                  .value() == 0
              ) {
                res.status(404).send(errorsmsg[404]());
              } else {
                try {
                  var datares ={"AppIdentities": [] };
                   datares.AppIdentities = db
                    .get("appidentity")
                    .filter({ name: req.params.group })                       
                    .value();
                  res.status(200).send(datares);
                } catch (err) {
                  res.status(500).send(errorsmsg[500]());
                }
              }
        }
    },
    /**
     * summary: Add a new policy or update one
     * description: 
     * parameters: group, body
     * produces: application/json
     * responses: 200, 405
     * operationId: addgroup
     */
    put: {
        200: function (req, res, callback) {
            req.body.name = req.params.group;
            req.body.id = uuid.v4();
      
            if (
              db
                .get("appidentity")
                .filter({ name: req.body.name, domainurl: req.body.domainurl , app_name: req.body.app_name })
                .size()
                .value() > 0
            ) {
              res.status(409).send(errorsmsg[409]());
            } else {
              try {
                var datares = db
                  .get("appidentity")
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
