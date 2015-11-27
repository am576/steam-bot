package iitc.steam;

/**
 * Game
 * <p>
 * An object holding pertinent game meta data.
 *
 * @author Ian
 * @version 1.0
 */
public class Game {
    public static final long COUNTER_STRIKE_GLOBAL_OFFENSIVE = 730;
    private final long id;
    private final String name;
    private final long lastTwoWeeksPlaytime;
    private final long totalPlaytime;
    private final String iconURL;
    private final String logoURL;
    private final boolean communityVisibleStats;

    /**
     * Creates a new Game with the specified characteristics.
     *
     * @param id                    the game identifier
     * @param name                  the display name of the game
     * @param lastTwoWeeksPlaytime  the total playtime over the past two weeks by the current user
     * @param totalPlaytime         the total playtime, since 2009 when data tracking began, of the current user
     * @param iconURL               the url of the game icon
     * @param logoURL               the url of the game logo
     * @param communityVisibleStats whether or not there is a community stats page for this game
     */
    public Game(long id, String name, long lastTwoWeeksPlaytime, long totalPlaytime, String iconURL, String logoURL, boolean communityVisibleStats) {
        this.id = id;
        this.name = name;
        this.lastTwoWeeksPlaytime = lastTwoWeeksPlaytime;
        this.totalPlaytime = totalPlaytime;
        this.iconURL = iconURL;
        this.logoURL = logoURL;
        this.communityVisibleStats = communityVisibleStats;
    }

    /**
     * The identifier for the game.
     *
     * @return the game identifier
     */
    public long getId() {
        return id;
    }

    /**
     * The display name of the game.
     *
     * @return the game display name
     */
    public String getName() {
        return name;
    }

    /**
     * The total playtime over the past two weeks by the current user associated with the WebAPI key.
     *
     * @return the total playtime for the past two weeks
     */
    public long getLastTwoWeeksPlaytime() {
        return lastTwoWeeksPlaytime;
    }

    /**
     * The total playtime, since 2009 when data tracking began, by the current user associated with the WebAPI key.
     *
     * @return the total playtime for the past two weeks
     */
    public long getTotalPlaytime() {
        return totalPlaytime;
    }

    /**
     * The url for the game icon.
     *
     * @return the game icon url
     */
    public String getIconURL() {
        return iconURL;
    }

    /**
     * The url for the game logo.
     *
     * @return the game logo url
     */
    public String getLogoURL() {
        return logoURL;
    }

    /**
     * Whether or not the game has a stats page with achievements or other game stats available for this game.
     *
     * @return <code>true</code> if the game has a community stats page, <code>false</code> otherwise
     */
    public boolean hasCommunityVisibleStats() {
        return communityVisibleStats;
    }
}
