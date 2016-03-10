package com.iancaffey.steam;

/**
 * RequestMethod
 * <p>
 * An object representing a possible HTTP request method.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public enum RequestMethod {
    /**
     * Requests that the origin server delete the resource identified by the Request-URI.
     */
    DELETE,
    /**
     * Retrieves whatever information (in the form of an entity) is identified by the Request-URI.
     */
    GET,
    /**
     * Requests that the origin server accept the entity enclosed in the request as a new subordinate of the resource identified by the Request-URI in the Request-Line.
     */
    POST,
    /**
     * Requests that the enclosed entity be stored under the supplied Request-URI.
     */
    PUT
}
