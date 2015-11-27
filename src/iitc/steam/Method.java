package iitc.steam;

/**
 * Method
 * <p>
 * An object representing a Steam WebAPI method to be called.
 *
 * @author Ian
 * @version 1.0
 */
public enum Method implements QueryToken {
    /**
     * Attempts to cancel a trade offer that has been sent.
     * The trade offer must have been sent by the account associated with the WebAPI key.
     * You cannot call this API for accounts other than that.
     */
    CANCEL_TRADE_OFFER(Interface.ECONOMY, "CancelTradeOffer", Version.V1, "tradeofferid=%s", RequestMethod.POST),
    /**
     * Attempts to decline a trade offer that has been received.
     * The trade offer must have been sent to the account associated with the WebAPI key.
     * You cannot call this API for accounts other than that.
     */
    DECLINE_TRADE_OFFER(Interface.ECONOMY, "DeclineTradeOffer", Version.V1, "tradeofferid=%s", RequestMethod.POST),
    /**
     * Retrieves the friends list for a specific user.
     */
    GET_FRIENDS_LIST(Interface.USER, "GetFriendList", Version.V1, "steamid=%s&relationship=%s&", RequestMethod.GET),
    /**
     * Retrieves the latest game news for a specific game.
     */
    GET_GAME_NEWS(Interface.NEWS, "GetNewsForApp", Version.V2, "appid=%s&count=%s&maxlength=%s", RequestMethod.GET),
    /**
     * Retrieves the game schema for a specified game.
     */
    GET_GAME_SCHEMA(Interface.USER_STATS, "GetSchemaForGame", Version.V2, "appid=%s", RequestMethod.GET),
    /**
     * Retrieves all global game achievement percentages for a specific game.
     */
    GET_GLOBAL_GAME_ACHIEVEMENT_PERCENTAGES(Interface.USER_STATS, "GetGlobalAchievementPercentagesForApp", Version.V2, "gameid=%s", RequestMethod.GET),
    /**
     * Retrieves the global game stats for a specific game.
     */
    GET_GLOBAL_GAME_STATS(Interface.USER_STATS, "GetGlobalStatsForGame", Version.V1, "appid=%s&count=%s&name=%s", RequestMethod.GET),
    /**
     * Retrieves owned games for a specific user.
     */
    GET_OWNED_GAMES(Interface.PLAYER_SERVICE, "GetOwnedGames", Version.V1, "steamid=%s&include_appinfo=%s&include_played_free_games=%s", RequestMethod.GET),
    /**
     * Retrieves the real owner of a shared game another user is playing.
     */
    GET_SHARED_GAME_OWNER(Interface.PLAYER_SERVICE, "IsPlayingSharedGame", Version.V1, "steamid=%s&appid_playing=%s", RequestMethod.GET),
    /**
     * Retrieves the trade history (up to a maximum of 500 sent or 1000 received regardless of <code>historicalCutoff</code>) for the account associated with the WebAPI key in English.
     */
    GET_TRADE_HISTORY(Interface.ECONOMY, "GetTradeOffers", Version.V1, "get_sent_offers=%s&get_received_offers=%s&get_descriptions=%s&language=%s&active_only=%s&historical_only=%s&time_historical_cutoff=%s", RequestMethod.GET),
    /**
     * Retrieves details about a single trade offer.
     * The trade offer must have been sent to or from the account associated with the WebAPI key.
     */
    GET_TRADE_OFFER(Interface.ECONOMY, "GetTradeOffer", Version.V1, "tradeofferid=%s&language=%s", RequestMethod.GET),
    /**
     * Retrieves the recently played games for a specific user.
     */
    GET_RECENTLY_PLAYED_GAMES(Interface.PLAYER_SERVICE, "GetRecentlyPlayedGames", Version.V1, "steamid=%s&count=%s", RequestMethod.GET),
    /**
     * Retrieves achievements of a user for a specific game.
     */
    GET_USER_ACHIEVEMENTS(Interface.USER_STATS, "GetPlayerAchievements", Version.V1, "steamid=%s&appid=%s&l=%s", RequestMethod.GET),
    /**
     * Retrieves the current stats of a user for a specific game.
     */
    GET_USER_GAME_STATS(Interface.USER_STATS, "GetUserStatsForGame", Version.V2, "steamid=%s&appid=%s&l=%s", RequestMethod.GET),
    /**
     * Retrieves the user profiles of specified users.
     */
    GET_USER_PROFILES(Interface.USER, "GetPlayerSummaries", Version.V2, "steamids=%s", RequestMethod.GET),
    /**
     * Retrieves the ban history of specified users.
     */
    GET_USER_BAN_HISTORY(Interface.USER, "GetPlayerBans", Version.V1, "steamids=%s", RequestMethod.GET);
    private final Interface iface;
    private final String token;
    private final Version version;
    private final String parameterFormat;
    private final RequestMethod requestMethod;

    /**
     * Creates a new Steam WebAPI method representation with the following characteristics.
     *
     * @param iface           the parent interface for this method
     * @param token           the query string token
     * @param version         the WebAPI version this method belongs to
     * @param parameterFormat the formatter for the query string parameters
     * @param requestMethod   the type of request method for the HTTP request (POST, GET, PUT, DELETE)
     * @throws IllegalArgumentException if <code>iface</code>, <code>token</code>, or <code>version</code> == <code>null</code>
     */
    Method(Interface iface, String token, Version version, String parameterFormat, RequestMethod requestMethod) {
        this.parameterFormat = parameterFormat;
        if (iface == null || token == null || version == null)
            throw new IllegalArgumentException();
        this.iface = iface;
        this.token = token;
        this.version = version;
        this.requestMethod = requestMethod;
    }

    /**
     * The parent interface that the method belongs to.
     *
     * @return the method's parent interface
     */
    public Interface getInterface() {
        return iface;
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

    /**
     * The version of the Steam WebAPI this method available.
     *
     * @return the method's Steam WebAPI
     */
    public Version getVersion() {
        return version;
    }

    /**
     * The format in which the parameters, if any, will be arranged in the query string.
     *
     * @return the query string parameter format
     */
    public String getParameterFormat() {
        return parameterFormat;
    }

    /**
     * The request method to be used for the Steam WebAPI request.
     *
     * @return the Steam WebAPI request method
     */
    public RequestMethod getRequestMethod() {
        return requestMethod;
    }
}
