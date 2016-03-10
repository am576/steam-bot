package com.iancaffey.steam;

/**
 * Relationship
 * <p>
 * A listing of possible relationship states.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public enum Relationship implements QueryToken {
    /**
     * A relationship state of being wither a stranger or a friend.
     */
    ALL,
    /**
     * A relationship state of being friends.
     */
    FRIEND;
    private final String token;

    Relationship() {
        this.token = name().toLowerCase();
    }

    /**
     * The token to be used in a query string.
     *
     * @return the query string token
     */
    @Override
    public String getToken() {
        return token;
    }
}
