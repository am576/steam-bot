package iitc.steam;

import iitc.steam.trade.MarketService;
import iitc.steam.trade.TradeService;

/**
 * Steam
 * <p>
 * An object that retrieves various game data and harnesses the ability to trade items for games.
 *
 * @author Ian
 * @version 1.0
 */
public class Steam implements AutoCloseable {
    //TODO:Replace with proper api key for launch site
    private static final String DEFAULT_KEY = "1B9A1673848A9BC878143718651A9A5E"; //Ian's key
    //private static final String DEFAULT_KEY = "F329AD4475CA4C3B9BD47683B0ECB01F"; //Aidden's key
    private static final long DEFAULT_ACCOUNT = 76561198059918087L;
    private static Steam steam;
    private final TradeService tradeService;
    private final Communicator communicator;
    private final DataParser parser;
    private final MarketService marketService;
    private String key;
    private long userId;

    /**
     * Creates a new steam object with the default WebAPI key.
     */
    public Steam() {
        this(DEFAULT_KEY, DEFAULT_ACCOUNT);
    }

    /**
     * Creates a new steam object with a preset WebAPI key and account associated with the key.
     *
     * @param key    the WebAPI key
     * @param userId the account associated with the key
     */
    public Steam(String key, long userId) {
        this.key = key;
        this.userId = userId;
        this.parser = new DataParser();
        this.communicator = new Communicator(this);
        this.tradeService = new TradeService(this);
        this.marketService = new MarketService(this);
    }

    /**
     * The global Steam WebAPI wrapper for the server.
     *
     * @return the server's Steam WebAPI wrapper
     */
    public static Steam getSteam() {
        if (steam == null)
            throw new IllegalStateException("Access before application has loaded.");
        return steam;
    }

    /**
     * Updates the global Steam WebAPI wrapper for the server.
     *
     * @param steam the new global Steam WebAPI wrapper
     */
    public static void setSteam(Steam steam) {
        Steam.steam = steam;
    }

    /**
     * The Steam WebAPI communicator which calls specific API methods.
     *
     * @return the Steam WebAPI communicator
     */
    public Communicator getCommunicator() {
        return communicator;
    }

    /**
     * The data parser which translates text into new Java object instances.
     *
     * @return the data parser
     */
    public DataParser getDataParser() {
        return parser;
    }

    /**
     * The WebAPI key associated with a specific Steam account and domain name.
     *
     * @return the current WebAPI key
     */
    public String getAPIKey() {
        return key;
    }

    /**
     * Updates the current WebAPI key.
     *
     * @param key the new WebAPI key
     */
    public void setAPIKey(String key) {
        this.key = key;
    }

    /**
     * The trade service to use to make Steam trades.
     *
     * @return the Steam trade service
     */
    public TradeService getTradeService() {
        return tradeService;
    }

    /**
     * The market service to use for retrieving recent item pricing information.
     *
     * @return the Steam market service
     */
    public MarketService getMarketService() {
        return marketService;
    }

    /**
     * The current user associated with the WebAPI key.
     * This will correspond to a trade bot or some sort of mule in which other client accounts interact with.
     *
     * @return the current user associated with the WebAPI key
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Updates the user that is associated with the WebAPI, to retrieve host inventory and other values.
     *
     * @param userId the new user account to be used with the WebAPI
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the latest game news for a specific game up to 100 entries of size 32,767 using a JSON format.
     *
     * @param gameId the identifier for the game in which to grab news for
     * @return the latest game news for the specified game
     */
    public GameNews getGameNews(long gameId) {
        return getGameNews(gameId, DataFormat.JSON);
    }

    /**
     * Retrieves the latest game news for a specific game up to 100 entries of size 32,767.
     *
     * @param gameId the identifier for the game in which to grab news for
     * @param format the format in which to retrieve the data in
     * @return the latest game news for the specified game
     */
    public GameNews getGameNews(long gameId, DataFormat format) {
        return getGameNews(gameId, 1000, Short.MAX_VALUE, format);
    }

    /**
     * Retrieves the latest game news for a specific game using a JSON format.
     *
     * @param gameId    the identifier for the game in which to grab news for
     * @param count     the number of entries to retrieve
     * @param maxLength the maximum length of each news entry
     * @return the latest game news for the specified game
     */
    public GameNews getGameNews(long gameId, int count, int maxLength) {
        return getGameNews(gameId, count, maxLength, DataFormat.JSON);
    }

    /**
     * Retrieves the latest game news for a specific game.
     *
     * @param gameId    the identifier for the game in which to grab news for
     * @param count     the number of entries to retrieve
     * @param maxLength the maximum length of each news entry
     * @param format    the format in which to retrieve the data in
     * @return the latest game news for the specified game
     */
    public GameNews getGameNews(long gameId, int count, int maxLength, DataFormat format) {
        return getDataParser().build(GameNews.class, getCommunicator().retrieve(Method.GET_GAME_NEWS, format, gameId, count, maxLength), format);
    }

    /**
     * Retrieves all global game achievement percentages for a specific game using a JSON format.
     *
     * @param gameId the identifier for the game in which to grab achievement percentages
     * @return the global game achievement percentages for the specified game
     */
    public GameAchievementPercentages getGlobalGameAchievementPercentages(long gameId) {
        return getGlobalGameAchievementPercentages(gameId, DataFormat.JSON);
    }

    /**
     * Retrieves all global game achievement percentages for a specific game.
     *
     * @param gameId the identifier for the game in which to grab achievement percentages
     * @param format the format in which to retrieve the data in
     * @return the global game achievement percentages for the specified game
     */
    public GameAchievementPercentages getGlobalGameAchievementPercentages(long gameId, DataFormat format) {
        return getDataParser().build(GameAchievementPercentages.class, getCommunicator().retrieve(Method.GET_GLOBAL_GAME_ACHIEVEMENT_PERCENTAGES, format, gameId), format);
    }

    /**
     * Retrieves the global game stats for a specific game using a JSON format.
     *
     * @param gameId           the identifier for the game in which to grab game stats
     * @param achievementNames the names of all the achievements to grab stats for
     * @return the global game stats for the specified achievements
     */
    public GameStats getGlobalGameStats(long gameId, String... achievementNames) {
        return getGlobalGameStats(gameId, DataFormat.JSON, achievementNames);
    }

    /**
     * Retrieves the global game stats for a specific game.
     *
     * @param gameId           the identifier for the game in which to grab game stats
     * @param format           the format in which to retrieve the data in
     * @param achievementNames the names of all the achievements to grab stats for
     * @return the global game stats for the specified achievements
     */
    public GameStats getGlobalGameStats(long gameId, DataFormat format, String... achievementNames) {
        return achievementNames == null ? null : getDataParser().build(GameStats.class, getCommunicator().retrieve(Method.GET_GLOBAL_GAME_STATS, format, gameId, achievementNames.length, achievementNames), format);
    }

    /**
     * Retrieves the user profile of the user associated with the WebAPI key using a JSON format.
     *
     * @return the profile for the user associated with the WebAPI key
     */
    public UserProfile getUserProfile() {
        return getUserProfile(DataFormat.JSON);
    }

    /**
     * Retrieves the user profile of the user associated with the WebAPI key.
     *
     * @param format the format in which to retrieve the data in
     * @return the profile for the user associated with the WebAPI key
     */
    public UserProfile getUserProfile(DataFormat format) {
        return getUserProfile(format, getUserId());
    }

    /**
     * Retrieves the user profile of the specified user using a JSON format.
     *
     * @param userId the identifiers for the user to lookup the profile for
     * @return the profile for the specified user
     */
    public UserProfile getUserProfile(long userId) {
        return getUserProfile(DataFormat.JSON, userId);
    }

    /**
     * Retrieves the user profile of the specified user.
     *
     * @param format the format in which to retrieve the data in
     * @param userId the identifiers for the user to lookup the profile for
     * @return the profile for the specified user
     */
    public UserProfile getUserProfile(DataFormat format, long userId) {
        if (userId == -1)
            return null;
        UserProfile[] profiles = getUserProfiles(format, userId);
        return profiles == null || profiles.length != 1 ? null : profiles[0];
    }

    /**
     * Retrieves the user profiles of all the specified users using a JSON format.
     *
     * @param userIds the identifiers for all the users to lookup profiles for
     * @return the profiles for the specified users
     */
    public UserProfile[] getUserProfiles(long... userIds) {
        return getUserProfiles(DataFormat.JSON, userIds);
    }

    /**
     * Retrieves the user profiles of all the specified users.
     *
     * @param format  the format in which to retrieve the data in
     * @param userIds the identifiers for all the users to lookup profiles for
     * @return the profiles for the specified users
     */
    public UserProfile[] getUserProfiles(DataFormat format, long... userIds) {
        return userIds == null ? null : userIds.length == 0 ? new UserProfile[0] : getDataParser().build(UserProfile[].class, getCommunicator().retrieve(Method.GET_USER_PROFILES, format, new Object[]{userIds}), format);
    }

    /**
     * Retrieves the friends list consisting of all applicable friends for the user associated with the WebAPI key using a JSON format.
     *
     * @return the friends list of the user associated with the WebAPI key
     */
    public Friend[] getFriendList() {
        return getFriendList(DataFormat.JSON);
    }

    /**
     * Retrieves the friends list consisting of all applicable friends for the user associated with the WebAPI key.
     *
     * @return the friends list of the user associated with the WebAPI key
     */
    public Friend[] getFriendList(DataFormat format) {
        return getFriendList(getUserId(), format);
    }

    /**
     * Retrieves the friends list consisting of all applicable friends for a specific user using a JSON format.
     *
     * @param userId the identifier for the user to retrieve friends for
     * @return the friends list of the specified user
     */
    public Friend[] getFriendList(long userId) {
        return getFriendList(userId, DataFormat.JSON);
    }

    /**
     * Retrieves the friends list consisting of all applicable friends for a specific user.
     *
     * @param userId the identifier for the user to retrieve friends for
     * @param format the format in which to retrieve the data in
     * @return the friends list of the specified user
     */
    public Friend[] getFriendList(long userId, DataFormat format) {
        return getFriendList(userId, Relationship.ALL, format);
    }

    /**
     * Retrieves the friends list for a specific user using a JSON format.
     *
     * @param userId       the identifier for the user to retrieve friends for
     * @param relationship the relationship of the users to retrieve
     * @return the friends list of the specified user
     */
    public Friend[] getFriendList(long userId, Relationship relationship) {
        return getFriendList(userId, relationship, DataFormat.JSON);
    }

    /**
     * Retrieves the friends list for a specific user.
     *
     * @param userId       the identifier for the user to retrieve friends for
     * @param relationship the relationship of the users to retrieve
     * @param format       the format in which to retrieve the data in
     * @return the friends list of the specified user
     */
    public Friend[] getFriendList(long userId, Relationship relationship, DataFormat format) {
        return userId == -1 || relationship == null ? null : getDataParser().build(Friend[].class, getCommunicator().retrieve(Method.GET_FRIENDS_LIST, format, userId, relationship.getToken()), format);
    }

    /**
     * Retrieves the current user achievements for a specific game using a JSON format and English.
     *
     * @return the current game achievements of the user associated with the WebAPI key
     */
    public UserAchievements getUserAchievements(long gameId) {
        return getUserAchievements(gameId, Language.ENGLISH);
    }

    /**
     * Retrieves the current user achievements for a specific game using a JSON format.
     *
     * @param gameId   the identifier for the game to find stats for
     * @param language the language of the the game stats
     * @return the current game achievements of the user associated with the WebAPI key
     */
    public UserAchievements getUserAchievements(long gameId, Language language) {
        return getUserAchievements(gameId, language, DataFormat.JSON);
    }

    /**
     * Retrieves the current user achievements for a specific game in English.
     *
     * @param gameId the identifier for the game to find stats for
     * @param format the format in which to retrieve data in
     * @return the current game achievements of the user associated with the WebAPI key
     */
    public UserAchievements getUserAchievements(long gameId, DataFormat format) {
        return getUserAchievements(gameId, Language.ENGLISH, format);
    }

    /**
     * Retrieves the current user achievements for a specific game.
     *
     * @param gameId   the identifier for the game to find stats for
     * @param language the language of the the game stats
     * @param format   the format in which to retrieve data in
     * @return the current game achievements of the user associated with the WebAPI key
     */
    public UserAchievements getUserAchievements(long gameId, Language language, DataFormat format) {
        return getUserAchievements(getUserId(), gameId, language, format);
    }

    /**
     * Retrieves the current user achievements for a specific game using a JSON format and English.
     *
     * @param userId the identifier for the user to find game stats for
     * @param gameId the identifier for the game to find stats for
     * @return the current game achievements of a user
     */
    public UserAchievements getUserAchievements(long userId, long gameId) {
        return getUserAchievements(userId, gameId, Language.ENGLISH);
    }

    /**
     * Retrieves the current user achievements for a specific game using a JSON format.
     *
     * @param userId   the identifier for the user to find game stats for
     * @param gameId   the identifier for the game to find stats for
     * @param language the language of the the game stats
     * @return the current game achievements of a user
     */
    public UserAchievements getUserAchievements(long userId, long gameId, Language language) {
        return getUserAchievements(userId, gameId, language, DataFormat.JSON);
    }

    /**
     * Retrieves the current user achievements for a specific game in English.
     *
     * @param userId the identifier for the user to find game stats for
     * @param gameId the identifier for the game to find stats for
     * @param format the format in which to retrieve data in
     * @return the current game achievements of a user
     */
    public UserAchievements getUserAchievements(long userId, long gameId, DataFormat format) {
        return getUserAchievements(userId, gameId, Language.ENGLISH, format);
    }

    /**
     * Retrieves the current user achievements for a specific game.
     *
     * @param userId   the identifier for the user to find game stats for
     * @param gameId   the identifier for the game to find stats for
     * @param language the language of the the game stats
     * @param format   the format in which to retrieve data in
     * @return the current game achievements of a user
     */
    public UserAchievements getUserAchievements(long userId, long gameId, Language language, DataFormat format) {
        return getDataParser().build(UserAchievements.class, getCommunicator().retrieve(Method.GET_USER_ACHIEVEMENTS, format, userId, gameId, (language == null ? Language.ENGLISH : language).getToken()), format);
    }

    /**
     * Retrieves the current stats of the user associated with the WebAPI key for a specific game using a JSON format in English.
     *
     * @param gameId the identifier for the game to find stats for
     * @return the current game stats of the user associated with the WebAPI key
     */
    public UserGameStats getUserGameStats(long gameId) {
        return getUserGameStats(gameId, Language.ENGLISH, DataFormat.JSON);
    }

    /**
     * Retrieves the current stats of the user associated with the WebAPI key for a specific game using a JSON format.
     *
     * @param gameId   the identifier for the game to find stats for
     * @param language the language of the the game stats
     * @return the current game stats of the user associated with the WebAPI key
     */
    public UserGameStats getUserGameStats(long gameId, Language language) {
        return getUserGameStats(gameId, language, DataFormat.JSON);
    }

    /**
     * Retrieves the current stats of the user associated with the WebAPI key for a specific game.
     *
     * @param gameId   the identifier for the game to find stats for
     * @param language the language of the the game stats
     * @param format   the format in which to retrieve data in
     * @return the current game stats of the user associated with the WebAPI key
     */
    public UserGameStats getUserGameStats(long gameId, Language language, DataFormat format) {
        return getUserGameStats(getUserId(), gameId, language, format);
    }

    /**
     * Retrieves the current stats of a user for a specific game using a JSON format in English.
     *
     * @param userId the identifier for the user to find game stats for
     * @param gameId the identifier for the game to find stats for
     * @return the current game stats of a user
     */
    public UserGameStats getUserGameStats(long userId, long gameId) {
        return getUserGameStats(userId, gameId, Language.ENGLISH, DataFormat.JSON);
    }

    /**
     * Retrieves the current stats of a user for a specific game using a JSON format.
     *
     * @param userId   the identifier for the user to find game stats for
     * @param gameId   the identifier for the game to find stats for
     * @param language the language of the the game stats
     * @return the current game stats of a user
     */
    public UserGameStats getUserGameStats(long userId, long gameId, Language language) {
        return getUserGameStats(userId, gameId, language, DataFormat.JSON);
    }

    /**
     * Retrieves the current stats of a user for a specific game.
     *
     * @param userId   the identifier for the user to find game stats for
     * @param gameId   the identifier for the game to find stats for
     * @param language the language of the the game stats
     * @param format   the format in which to retrieve data in
     * @return the current game stats of a user
     */
    public UserGameStats getUserGameStats(long userId, long gameId, Language language, DataFormat format) {
        return userId == -1 || gameId == -1 ? null : getDataParser().build(UserGameStats.class, getCommunicator().retrieve(Method.GET_USER_GAME_STATS, format, userId, gameId, (language == null ? Language.ENGLISH : language).getToken()), format);
    }

    /**
     * Retrieves all the owned games for the user associated with the WebAPI key including all game info and played free games using a JSON format.
     *
     * @return the owned games for the user associated with the WebAPI key
     */
    public Game[] getOwnedGames() {
        return getOwnedGames(DataFormat.JSON);
    }

    /**
     * Retrieves all the owned games for the user associated with the WebAPI key including all game info and played free games.
     *
     * @param format the format in which to retrieve data in
     * @return the owned games for the user associated with the WebAPI key
     */
    public Game[] getOwnedGames(DataFormat format) {
        return getOwnedGames(true, true, format);
    }

    /**
     * Retrieves all the owned games for the user associated with the WebAPI key using a JSON format.
     *
     * @param includeGameInfo        whether or not to include game info
     * @param includePlayedFreeGames whether or not to include played free games
     * @return the owned games for the user associated with the WebAPI key
     */
    public Game[] getOwnedGames(boolean includeGameInfo, boolean includePlayedFreeGames) {
        return getOwnedGames(includeGameInfo, includePlayedFreeGames, DataFormat.JSON);
    }

    /**
     * Retrieves all the owned games for the user associated with the WebAPI key.
     *
     * @param includeGameInfo        whether or not to include game info
     * @param includePlayedFreeGames whether or not to include played free games
     * @param format                 the format in which to retrieve data in
     * @return the owned games for the user associated with the WebAPI key
     */
    public Game[] getOwnedGames(boolean includeGameInfo, boolean includePlayedFreeGames, DataFormat format) {
        return getOwnedGames(getUserId(), includeGameInfo, includePlayedFreeGames, format);
    }

    /**
     * Retrieves all the owned games for a specific user including all game info and played free games using a JSON format.
     *
     * @param userId the identifier for the user the games belong to
     * @return the owned games for the specified user
     */
    public Game[] getOwnedGames(long userId) {
        return getOwnedGames(userId, DataFormat.JSON);
    }

    /**
     * Retrieves all the owned games for a specific user including all game info and played free games.
     *
     * @param userId the identifier for the user the games belong to
     * @param format the format in which to retrieve data in
     * @return the owned games for the specified user
     */
    public Game[] getOwnedGames(long userId, DataFormat format) {
        return getOwnedGames(userId, true, true, format);
    }

    /**
     * Retrieves all the owned games for a specific user using a JSON format.
     *
     * @param userId                 the identifier for the user the games belong to
     * @param includeGameInfo        whether or not to include game info
     * @param includePlayedFreeGames whether or not to include played free games
     * @return the owned games for the specified user
     */
    public Game[] getOwnedGames(long userId, boolean includeGameInfo, boolean includePlayedFreeGames) {
        return getOwnedGames(userId, includeGameInfo, includePlayedFreeGames, DataFormat.JSON);
    }

    /**
     * Retrieves all the owned games for a specific user.
     *
     * @param userId                 the identifier for the user the games belong to
     * @param includeGameInfo        whether or not to include game info
     * @param includePlayedFreeGames whether or not to include played free games
     * @param format                 the format in which to retrieve data in
     * @return the owned games for the specified user
     */
    public Game[] getOwnedGames(long userId, boolean includeGameInfo, boolean includePlayedFreeGames, DataFormat format) {
        return userId == -1 ? null : getDataParser().build(Game[].class, getCommunicator().retrieve(Method.GET_OWNED_GAMES, format, userId, includeGameInfo, includePlayedFreeGames), format);
    }

    /**
     * Retrieves the recently played games for the user associated with the WebAPI key using a JSON format up to 1000 games.
     *
     * @return the recently played games for the user associated with the WebAPI key
     */
    public Game[] getRecentlyPlayedGames() {
        return getRecentlyPlayedGames(DataFormat.JSON);
    }

    /**
     * Retrieves the recently played games for the user associated with the WebAPI key using a JSON format.
     *
     * @param gameLimit the limit of games to retrieve
     * @return the recently played games for the user associated with the WebAPI key
     */
    public Game[] getRecentlyPlayedGames(int gameLimit) {
        return getRecentlyPlayedGames(gameLimit, DataFormat.JSON);
    }

    /**
     * Retrieves the recently played games for the user associated with the WebAPI key up to 1000.
     *
     * @param format the format in which to retrieve the data in
     * @return the recently played games for the user associated with the WebAPI key
     */
    public Game[] getRecentlyPlayedGames(DataFormat format) {
        return getRecentlyPlayedGames(1000, format);
    }

    /**
     * Retrieves the recently played games for the user associated with the WebAPI key
     *
     * @param gameLimit the limit of games to retrieve
     * @param format    the format in which to retrieve the data in
     * @return the recently played games for the user associated with the WebAPI key
     */
    public Game[] getRecentlyPlayedGames(int gameLimit, DataFormat format) {
        return getRecentlyPlayedGames(getUserId(), gameLimit, format);
    }

    /**
     * Retrieves the recently played games for a specific user using a JSON format up to 1000 games.
     *
     * @param userId the identifier for the user to get recently played games for
     * @return the recently played games for a specific user
     */
    public Game[] getRecentlyPlayedGames(long userId) {
        return getRecentlyPlayedGames(userId, DataFormat.JSON);
    }

    /**
     * Retrieves the recently played games for a specific user using a JSON format.
     *
     * @param userId    the identifier for the user to get recently played games for
     * @param gameLimit the limit of games to retrieve
     * @return the recently played games for a specific user
     */
    public Game[] getRecentlyPlayedGames(long userId, int gameLimit) {
        return getRecentlyPlayedGames(userId, gameLimit, DataFormat.JSON);
    }

    /**
     * Retrieves the recently played games for a specific user up to 1000.
     *
     * @param userId the identifier for the user to get recently played games for
     * @param format the format in which to retrieve the data in
     * @return the recently played games for a specific user
     */
    public Game[] getRecentlyPlayedGames(long userId, DataFormat format) {
        return getRecentlyPlayedGames(userId, 1000, format);
    }

    /**
     * Retrieves the recently played games for a specific user.
     *
     * @param userId    the identifier for the user to get recently played games for
     * @param gameLimit the limit of games to retrieve
     * @param format    the format in which to retrieve the data in
     * @return the recently played games for a specific user
     */
    public Game[] getRecentlyPlayedGames(long userId, int gameLimit, DataFormat format) {
        return userId == -1 || gameLimit == -1 ? null : getDataParser().build(Game[].class, getCommunicator().retrieve(Method.GET_RECENTLY_PLAYED_GAMES, format, userId, gameLimit), format);
    }

    /**
     * Retrieves the real owner of a shared game the current user is playing using a JSON format.
     *
     * @param gameId the identifier for the game the user is playing
     * @return the identifier for the real owner of the shared game the current user is playing
     */
    public long getSharedGameOwner(long gameId) {
        return getSharedGameOwner(gameId, DataFormat.JSON);
    }

    /**
     * Retrieves the real owner of a shared game the current user is playing.
     *
     * @param gameId the identifier for the game the user is playing
     * @param format the format in which to retrieve the data in
     * @return the identifier for the real owner of the shared game the current user is playing
     */
    public long getSharedGameOwner(long gameId, DataFormat format) {
        return getSharedGameOwner(getUserId(), gameId, format);
    }

    /**
     * Retrieves the real owner of a shared game another user is playing using a JSON format.
     *
     * @param userPlayingId the identifier for the user playing the shared game
     * @param gameId        the identifier for the game the user is playing
     * @return the identifier for the real owner of the shared game the user is playing
     */
    public long getSharedGameOwner(long userPlayingId, long gameId) {
        return getSharedGameOwner(userPlayingId, gameId, DataFormat.JSON);
    }

    /**
     * Retrieves the real owner of a shared game another user is playing.
     *
     * @param userPlayingId the identifier for the user playing the shared game
     * @param gameId        the identifier for the game the user is playing
     * @param format        the format in which to retrieve the data in
     * @return the identifier for the real owner of the shared game the user is playing
     */
    public long getSharedGameOwner(long userPlayingId, long gameId, DataFormat format) {
        return userPlayingId == -1 || gameId == -1 ? -1 : getDataParser().build(Long.class, getCommunicator().retrieve(Method.GET_SHARED_GAME_OWNER, format, userPlayingId, gameId), format);
    }

    /**
     * Retrieves the game schema for the specified game using a JSON format.
     *
     * @param gameId the identifier for the game to retrieve the schema of
     * @return the game schema for the specified game
     */
    public GameSchema getGameSchema(long gameId) {
        return getGameSchema(gameId, DataFormat.JSON);
    }

    /**
     * Retrieves the game schema for the specified game.
     *
     * @param gameId the identifier for the game to retrieve the schema of
     * @param format the format in which to retrieve the data in
     * @return the game schema for the specified game
     */
    public GameSchema getGameSchema(long gameId, DataFormat format) {
        return gameId == -1 ? null : getDataParser().build(GameSchema.class, getCommunicator().retrieve(Method.GET_GAME_SCHEMA, format, gameId), format);
    }

    /**
     * Retrieves the ban history of the user associated with the WebAPI key using a JSON format.
     *
     * @return the ban history of the user associated with the WebAPI key
     */
    public BanHistory getBanHistory() {
        return getBanHistory(DataFormat.JSON);
    }

    /**
     * Retrieves the ban history of the user associated with the WebAPI key.
     *
     * @param format the format in which to retrieve the data in
     * @return the ban history of the user associated with the WebAPI key
     */
    public BanHistory getBanHistory(DataFormat format) {
        return getBanHistory(getUserId(), format);
    }

    /**
     * Retrieves the ban history of the specified user using a JSON format.
     *
     * @param userId the identifier of the user to lookup a ban history for
     * @return the ban history of the specified user
     */
    public BanHistory getBanHistory(long userId) {
        return getBanHistory(userId, DataFormat.JSON);
    }

    /**
     * Retrieves the ban history of the specified user.
     *
     * @param userId the identifier of the user to lookup a ban history for
     * @param format the format in which to retrieve the data in
     * @return the ban history of the specified user
     */
    public BanHistory getBanHistory(long userId, DataFormat format) {
        if (userId == -1)
            return null;
        BanHistory[] histories = getBanHistory(format, userId);
        return histories == null || histories.length != 1 ? null : histories[0];
    }

    /**
     * Retrieves the ban history of the specified users using a JSON format.
     *
     * @param userIds the identifiers of the users to lookup ban histories for
     * @return the ban history of the specified users
     */
    public BanHistory[] getBanHistory(long... userIds) {
        return getBanHistory(DataFormat.JSON, userIds);
    }

    /**
     * Retrieves the ban history of the specified users.
     *
     * @param userIds the identifiers of the users to lookup ban histories for
     * @param format  the format in which to retrieve the data in
     * @return the ban history of the specified users
     */
    public BanHistory[] getBanHistory(DataFormat format, long... userIds) {
        return userIds == null ? null : userIds.length == 0 ? new BanHistory[0] : getDataParser().build(BanHistory[].class, getCommunicator().retrieve(Method.GET_USER_BAN_HISTORY, format, new Object[]{userIds}), format);
    }

    /**
     * Terminates all current communications with the Steam WebAPI.
     */
    @Override
    public void close() {
        getCommunicator().close();
    }
}
