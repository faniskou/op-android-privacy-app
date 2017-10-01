'use strict';
var dataProvider = require('../../../data/statistics/{key}/{domainurl}.js');
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
     */
    get: function getpolicyurl(req, res, next) {
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
     * summary: Update an existing statistic
     * description: 
     * parameters: key, domainurl, body
     * produces: application/json
     * responses: 200, 405
     */
    post: function updatePeturl(req, res, next) {
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
