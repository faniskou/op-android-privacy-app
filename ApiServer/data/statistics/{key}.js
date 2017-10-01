'use strict';
var Mockgen = require('../mockgen.js');
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
     * operationId: getpolicy
     */
    get: {
        200: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/statistics/{key}',
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
                path: '/statistics/{key}',
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
                path: '/statistics/{key}',
                operation: 'get',
                response: '404'
            }, callback);
        }
    },
    /**
     * summary: Add a new policy statistic
     * description: 
     * parameters: key, body
     * produces: application/json
     * responses: 200, 405
     * operationId: addpolicy
     */
    put: {
        200: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/statistics/{key}',
                operation: 'put',
                response: '200'
            }, callback);
        },
        405: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/statistics/{key}',
                operation: 'put',
                response: '405'
            }, callback);
        }
    }
};
