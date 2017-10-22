'use strict';
/**
 * error answers
 */

module.exports = {
    400: function () { return "The request cannot be fulfilled due to bad syntax"; },
    401: function () { return "The request was a legal request, but the server is refusing to respond to it. For use when authentication is possible but has failed or not yet been provided"; },
    402: function () { return "Reserved for future use"; },
    403: function () { return "The request was a legal request, but the server is refusing to respond to it"; },
    404: function () { return "The requested page could not be found but may be available again in the future"; },
    405: function () { return "A request was made of a page using a request method not supported by that page"; },
    406: function () { return "The server can only generate a response that is not accepted by the client"; },
    407: function () { return "The client must first authenticate itself with the proxy"; },
    408: function () { return "The server timed out waiting for the request"; },
    409: function () { return "The request could not be completed because of a conflict in the request.Or may a duplication."; },
    410: function () { return "The requested page is no longer available"; },
    411: function () { return "The <Content-Length> is not defined. The server will not accept the request without it"; },
    412: function () { return "The precondition given in the request evaluated to false by the server"; },
    413: function () { return "The server will not accept the request, because the request entity is too large"; },
    414: function () { return "The server will not accept the request, because the URL is too long. Occurs when you convert a POST request to a GET request with a long query information"; },
    415: function () { return "The server will not accept the request, because the media type is not supported"; },
    416: function () { return "The client has asked for a portion of the file, but the server cannot supply that portion"; },
    417: function () { return "The server cannot meet the requirements of the Expect request-header field"; },
    500: function () { return "A generic error message, given when no more specific message is suitable"; },
    501: function () { return "The server either does not recognize the request method, or it lacks the ability to fulfill the request"; },
    502: function () { return "The server was acting as a gateway or proxy and received an invalid response from the upstream server"; },
    503: function () { return "The server is currently unavailable (overloaded or down)"; },
    504: function () { return "The server was acting as a gateway or proxy and did not receive a timely response from the upstream server"; },
    505: function () { return "The server does not support the HTTP protocol version used in the request"; },
    511: function () { return "The client needs to authenticate to gain network access"; },
    200: function () { return "{'body'='The request is OK (this is the standard response for successful HTTP requests)'}"; },
    201: function () { return "The request has been fulfilled, and a new resource is created"; },
    202: function () { return "The request has been accepted for processing, but the processing has not been completed"; },
    203: function () { return "The request has been successfully processed, but is returning information that may be from another source"; },
    204: function () { return "The request has been successfully processed, but is not returning any content"; },
    205: function () { return "The request has been successfully processed, but is not returning any content, and requires that the requester reset the document view"; },
    206: function () { return "The server is delivering only part of the resource due to a range header sent by the client"; }
};