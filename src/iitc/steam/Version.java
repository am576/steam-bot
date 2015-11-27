package iitc.steam;

/**
 * Version
 * <p>
 * An object representing various API versions which are used in the Steam WebAPI.
 *
 * @author Ian
 * @version 1.0
 */
public enum Version implements QueryToken {
    V1, V2;
    private final String token;

    /**
     * Creates a new version representation.
     */
    Version() {
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
