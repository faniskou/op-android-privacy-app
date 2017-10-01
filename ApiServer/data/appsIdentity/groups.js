'use strict';
var Mockgen = require('../mockgen.js');
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
     * operationId: getgroups
     */
    get: {
        200: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/appsIdentity/groups',
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
                path: '/appsIdentity/groups',
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
                path: '/appsIdentity/groups',
                operation: 'get',
                response: '404'
            }, callback);
        }
    }
};
