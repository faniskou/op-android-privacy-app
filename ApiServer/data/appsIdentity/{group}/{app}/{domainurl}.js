'use strict';
var Mockgen = require('../../../mockgen.js');
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
     * operationId: getgroupappdomain
     */
    get: {
        200: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/appsIdentity/{group}/{app}/{domainurl}',
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
                path: '/appsIdentity/{group}/{app}/{domainurl}',
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
                path: '/appsIdentity/{group}/{app}/{domainurl}',
                operation: 'get',
                response: '404'
            }, callback);
        }
    },
    /**
     * summary: Add a new policy
     * description: 
     * parameters: group, app, domainurl, body
     * produces: application/json
     * responses: 200, 405
     * operationId: addgroupapp
     */
    put: {
        200: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/appsIdentity/{group}/{app}/{domainurl}',
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
                path: '/appsIdentity/{group}/{app}/{domainurl}',
                operation: 'put',
                response: '405'
            }, callback);
        }
    },
    /**
     * summary: Update an existing app identity domain
     * description: 
     * parameters: group, app, domainurl, body
     * produces: application/json
     * responses: 200, 400, 404, 405
     * operationId: updateGroupdomain
     */
    post: {
        200: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/appsIdentity/{group}/{app}/{domainurl}',
                operation: 'post',
                response: '200'
            }, callback);
        },
        400: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/appsIdentity/{group}/{app}/{domainurl}',
                operation: 'post',
                response: '400'
            }, callback);
        },
        404: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/appsIdentity/{group}/{app}/{domainurl}',
                operation: 'post',
                response: '404'
            }, callback);
        },
        405: function (req, res, callback) {
            /**
             * Using mock data generator module.
             * Replace this by actual data for the api.
             */
            Mockgen().responses({
                path: '/appsIdentity/{group}/{app}/{domainurl}',
                operation: 'post',
                response: '405'
            }, callback);
        }
    }
};
