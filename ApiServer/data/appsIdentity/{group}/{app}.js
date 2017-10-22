'use strict';
var Mockgen = require('../../mockgen.js');
var errorsmsg = require("../../errorsmessages.js");

/**
 * Operations on /appsIdentity/{group}/{app}
 */
module.exports = {
    /**
     * summary: Get a group app identity
     * description: 
     * parameters: group, app
     * produces: application/json
     * responses: 200, 400, 404
     * operationId: getgroupapp
     */
    get: {
        200: function (req, res, callback) {
            if (
                db
                  .get("appidentity")
                  .filter({ name: req.params.group , app_name: req.params.app })                  
                  .size()
                  .value() == 0
              ) {
                res.status(404).send(errorsmsg[404]());
              } else {
                try {
                  var datares ={"AppIdentities": [] };
                   datares.AppIdentities = db
                    .get("appidentity")
                    .filter({ name: req.params.group , app_name: req.params.app })                       
                    .value();
                  res.status(200).send(datares);
                } catch (err) {
                  res.status(500).send(errorsmsg[500]());
                }
              }
        }
    }
};
