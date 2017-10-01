'use strict';
var Mockgen = require('../../mockgen.js');
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
     * operationId: getpolicyurl
     */
    get: {
        200: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/statistics/{key}/{domainurl}',
                operation: 'get',
                response: '200'
            }, callback);
        },
        400: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/statistics/{key}/{domainurl}',
                operation: 'get',
                response: '400'
            }, callback);
        },
        404: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/statistics/{key}/{domainurl}',
                operation: 'get',
                response: '404'
            }, callback);
        }
    },
    /**
     * summary: Update an existing statistic
     * description: 
     * parameters: key, domainurl, body
     * produces: application/json
     * responses: 200, 405
     * operationId: updatePeturl
     */
    post: {
        200: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/statistics/{key}/{domainurl}',
                operation: 'post',
                response: '200'
            }, callback);
        },
        405: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/statistics/{key}/{domainurl}',
                operation: 'post',
                response: '405'
            }, callback);
        }
    }
};
