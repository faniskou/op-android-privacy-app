'use strict';
var dataProvider = require('../../data/appsIdentity/{group}.js');
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
     */
    get: function getgroup(req, res, next) {
        /**
         * Get the data for response 200
         * For response `default` status 200 is used.
         */
        var status = 200;
        var provider = dataProvider['get']['200'];
        provider(req, res, function (err, data) {
            if (err) {
                next(err);
                return;
            }
            res.status(status).send(data && data.responses);
        });
    },
    /**
     * summary: Add a new policy or update one
     * description: 
     * parameters: group, body
     * produces: application/json
     * responses: 200, 405
     */
    put: function addgroup(req, res, next) {
        /**
         * Get the data for response 200
         * For response `default` status 200 is used.
         */
        var status = 200;
        var provider = dataProvider['put']['200'];
        provider(req, res, function (err, data) {
            if (err) {
                next(err);
                return;
            }
            res.status(status).send(data && data.responses);
        });
    }
};
