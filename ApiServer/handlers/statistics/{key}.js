'use strict';
var dataProvider = require('../../data/statistics/{key}.js');
/**
 * Operations on /statistics/{key}
 */
module.exports = {
    /**
     * summary: Get all statistics based on a key
     * description: 
     * parameters: key
     * produces: application/json
     * responses: 200, 400, 404
     */
    get: function getpolicy(req, res, next) {
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
     * summary: Add a new policy statistic
     * description: 
     * parameters: key, body
     * produces: application/json
     * responses: 200, 405
     */
    put: function addpolicy(req, res, next) {
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