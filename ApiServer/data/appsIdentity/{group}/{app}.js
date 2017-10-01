'use strict';
var Mockgen = require('../../mockgen.js');
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
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/appsIdentity/{group}/{app}',
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
                path: '/appsIdentity/{group}/{app}',
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
                path: '/appsIdentity/{group}/{app}',
                operation: 'get',
                response: '404'
            }, callback);
        }
    }
};
