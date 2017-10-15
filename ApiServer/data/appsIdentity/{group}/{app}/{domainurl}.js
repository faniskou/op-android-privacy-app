'use strict';
var Mockgen = require('../../../mockgen.js');
var errorsmsg = require("../../../errorsmessages.js");
var uuid = require('uuid');

/**
 * Operations on /appsIdentity/{group}/{app}/{domainurl}
 */
module.exports = {
    /**
     * summary: Get an app identity url
     * description: 
     * parameters: group, app, domainurl
     * produces: application/json
     * responses: 200, 400, 404
     * operationId: getgroupappdomain
     */
    get: {
        200: function (req, res, callback) {
            if (
                db
                  .get("appidentity")
                  .filter({ name: req.params.group , app_name: req.params.app, domainurl: req.params.domainurl })                  
                  .size()
                  .value() == 0
              ) {
                res.status(404).send(errorsmsg[404]());
              } else {
                try {
               //   var datares ={"AppIdentities": [] };
                  var  datares = db
                    .get("appidentity")
                    .filter({ name: req.params.group , app_name: req.params.app, domainurl: req.params.domainurl })                       
                    .value();
                  res.status(200).send(datares);
                } catch (err) {
                  res.status(500).send(errorsmsg[500]());
                }
              }
        }
    },
    /**
     * summary: Add a new policy
     * description: 
     * parameters: group, app, domainurl, body
     * produces: application/json
     * responses: 200, 405
     * operationId: addgroupapp
     */
    put: {
        200: function (req, res, callback) {
            req.body.name = req.params.group;
            req.body.domainurl= req.params.domainurl 
            req.body.app_name= req.params.app
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
    },
    /**
     * summary: Update an existing app identity domain
     * description: 
     * parameters: group, app, domainurl, body
     * produces: application/json
     * responses: 200, 400, 404, 405
     * operationId: updateGroupdomain
     */
    post: {
        200: function (req, res, callback) {
            if (
                db
                  .get("appidentity")
                  .filter({ name: req.params.group , app_name: req.params.app, domainurl: req.params.domainurl })                  
                  .size()
                  .value() == 0
              ) {
                res.status(404).send(errorsmsg[404]());
              } else {
                try {
                  var  input=req.body;
                  delete input.id;
                  input.name = req.params.group;
                  input.app_name = req.params.app;                  
                  input.domainurl = req.params.domainurl;
                  var datares ={"appidentity": [] };
                   datares.Statistics = db
                    .get("appidentity")
                    .find({ name: req.params.group , app_name: req.params.app, domainurl: req.params.domainurl })                  
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
