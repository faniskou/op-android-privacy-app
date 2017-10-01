'use strict';
var Mockgen = require('../mockgen.js');
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
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/appsIdentity/{group}',
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
                path: '/appsIdentity/{group}',
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
                path: '/appsIdentity/{group}',
                operation: 'get',
                response: '404'
            }, callback);
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
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/appsIdentity/{group}',
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
                path: '/appsIdentity/{group}',
                operation: 'put',
                response: '405'
            }, callback);
        }
    }
};
