package com.steam;

/**
 * Language
 * <p>
 * An enum containing the language presets for API method calls.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public enum Language implements QueryToken {
    ENGLISH("en");
    private final String token;

    /**
     * Creates a new language constant with the specified token.
     *
     * @param token the language code token
     */
    Language(String token) {
        this.token = token.toLowerCase();
    }

    /**
     * The token to be added to the query string
     *
     * @return the query string token
     */
    @Override
    public String getToken() {
        return token;
    }
}
