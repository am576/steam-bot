package com.steam;

/**
 * QueryToken
 * An object representing a component of a Steam Web API query string.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public interface QueryToken {
    /**
     * The token to be used in a query string.
     *
     * @return the query string token
     */
    public String getToken();
}
