'use strict';
var dataProvider = require('../../../../data/appsIdentity/{group}/{app}/{domainurl}.js');
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
     */
    get: function getgroupappdomain(req, res, next) {
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
     * summary: Add a new policy
     * description: 
     * parameters: group, app, domainurl, body
     * produces: application/json
     * responses: 200, 405
     */
    put: function addgroupapp(req, res, next) {
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
    },
    /**
     * summary: Update an existing app identity domain
     * description: 
     * parameters: group, app, domainurl, body
     * produces: application/json
     * responses: 200, 400, 404, 405
     */
    post: function updateGroupdomain(req, res, next) {
        /**
         * Get the data for response 200
         * For response `default` status 200 is used.
         */
        var status = 200;
        var provider = dataProvider['post']['200'];
        provider(req, res, function (err, data) {
            if (err) {
                next(err);
                return;
            }
            res.status(status).send(data && data.responses);
        });
    }
};
