package iitc.steam;

/**
 * Interface
 * An object representing a class that contains methods to be used in the Steam WebAPI.
 *
 * @author Ian
 * @version 1.0
 */
public enum Interface implements QueryToken {
    ECONOMY("IEconService"),
    NEWS("ISteamNews"),
    PLAYER_SERVICE("IPlayerService"),
    USER("ISteamUser"),
    USER_STATS("ISteamUserStats");
    private final String token;

    /**
     * Creates a new Steam WebAPI interface with the following characteristics.
     *
     * @param token the query string token
     * @throws NullPointerException if <code>token == null</code>
     */
    Interface(String token) {
        if (token == null)
            throw new IllegalArgumentException();
        this.token = token;
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
