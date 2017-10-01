'use strict';
var dataProvider = require('../../data/appsIdentity/groups.js');
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
     */
    get: function getgroups(req, res, next) {
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
    }
};
