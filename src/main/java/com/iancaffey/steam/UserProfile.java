package com.iancaffey.steam;

/**
 * UserProfile
 * <p>
 * An object holding all public and private user profile data.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class UserProfile {
    public static final int OFFLINE = 0;
    public static final int ONLINE = 1;
    public static final int BUSY = 2;
    public static final int AWAY = 3;
    public static final int SNOOZE = 4;
    public static final int LOOKING_TO_TRADE = 5;
    public static final int LOOKING_TO_PLAY = 6;
    private final long userId;
    private final String displayName;
    private final String profileUrl;
    private final String smallAvatarUrl;
    private final String mediumAvatarUrl;
    private final String fullAvatarUrl;
    private final long status;
    private final boolean visible;
    private final boolean communityProfileConfigured;
    private final long lastLogoffTime;
    private final String realName;
    private final long primaryClanId;
    private final long dateRegistered;
    private final int currentGameId;
    private final String currentGameIP;
    private final String extraGameInfo;
    private final String countryCode;
    private final String stateCode;
    private final int cityCode;

    /**
     * Creates a new user profile summary with the specified characteristics.
     *
     * @param userId                     the identifier for the user
     * @param displayName                the display name of the user
     * @param profileUrl                 the url for the user profile
     * @param smallAvatarUrl             the url for the small avatar icon
     * @param mediumAvatarUrl            the url for the medium avatar icon
     * @param fullAvatarUrl              the url for the full avatar icon
     * @param status                     the current status of the user
     * @param visible                    whether or not the user's profile is visible
     * @param communityProfileConfigured whether or not the user's community profile is configured
     * @param lastLogoffTime             the last time the user was logged off
     * @param realName                   the real name of the user
     * @param primaryClanId              the primary group identifier for the user
     * @param dateRegistered             the time in which the user registered
     * @param currentGameId              the identifier for the user's current game, if playing any
     * @param currentGameIP              the IP information for the user's current game, if playing any
     * @param extraGameInfo              the extra info for the user's current game, if playing any
     * @param countryCode                the 2-character ISO country code for the country of residence of the user
     * @param stateCode                  the state of residence of the user
     * @param cityCode                   the city of residence of the user
     */
    public UserProfile(long userId, String displayName, String profileUrl, String smallAvatarUrl, String mediumAvatarUrl, String fullAvatarUrl, long status, boolean visible, boolean communityProfileConfigured, long lastLogoffTime, String realName, long primaryClanId, long dateRegistered, int currentGameId, String currentGameIP, String extraGameInfo, String countryCode, String stateCode, int cityCode) {
        this.userId = userId;
        this.displayName = displayName;
        this.profileUrl = profileUrl;
        this.smallAvatarUrl = smallAvatarUrl;
        this.mediumAvatarUrl = mediumAvatarUrl;
        this.fullAvatarUrl = fullAvatarUrl;
        this.status = status;
        this.visible = visible;
        this.communityProfileConfigured = communityProfileConfigured;
        this.lastLogoffTime = lastLogoffTime;
        this.realName = realName;
        this.primaryClanId = primaryClanId;
        this.dateRegistered = dateRegistered;
        this.currentGameId = currentGameId;
        this.currentGameIP = currentGameIP;
        this.extraGameInfo = extraGameInfo;
        this.countryCode = countryCode;
        this.stateCode = stateCode;
        this.cityCode = cityCode;
    }

    /**
     * The identifier for the user.
     *
     * @return the user's identifier
     */
    public long getUserId() {
        return userId;
    }

    /**
     * The display name of the user.
     *
     * @return the user's display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * The url of the user's profile page.
     *
     * @return the user's profile page url
     */
    public String getProfileUrl() {
        return profileUrl;
    }

    /**
     * The url of the user's small avatar.
     *
     * @return the user's small avatar url
     */
    public String getSmallAvatarUrl() {
        return smallAvatarUrl;
    }

    /**
     * The url of the user's medium avatar.
     *
     * @return the user's medium avatar url
     */
    public String getMediumAvatarUrl() {
        return mediumAvatarUrl;
    }

    /**
     * The url of the user's full avatar.
     *
     * @return the user's full avatar url
     */
    public String getFullAvatarUrl() {
        return fullAvatarUrl;
    }

    /**
     * The current status of the user.
     *
     * @return the user's current status
     */
    public long getStatus() {
        return status;
    }

    /**
     * Whether or not the user's profile page is available for viewing.
     *
     * @return <code>true</code> if the user's profile page is visible, <code>false</code> otherwise
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Whether or not the user's community profile page is configured.
     *
     * @return <code>true</code> if the user's community profile page is configured, <code>false</code> otherwise
     */
    public boolean isCommunityProfileConfigured() {
        return communityProfileConfigured;
    }

    /**
     * The time of the last logoff of the user.
     *
     * @return the user's last logoff time
     */
    public long getLastLogoffTime() {
        return lastLogoffTime;
    }

    /**
     * The real name of the user.
     *
     * @return the user's real name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * The identifier for the primary group of the user.
     *
     * @return the user's primary group identifier
     */
    public long getPrimaryClanId() {
        return primaryClanId;
    }

    /**
     * The time in which the user registered.
     *
     * @return the user's time of registration
     */
    public long getDateRegistered() {
        return dateRegistered;
    }

    /**
     * The identifier for the current game the user is playing, if any.
     *
     * @return the user's current game identifier
     */
    public int getCurrentGameId() {
        return currentGameId;
    }

    /**
     * The current IP address, including port number, of the current game the user is playing, if any.
     *
     * @return the IP address of the user's current game
     */
    public String getCurrentGameIP() {
        return currentGameIP;
    }

    /**
     * The extra info provided about the current game the user is playing, if any.
     *
     * @return the current user's game extra info
     */
    public String getExtraGameInfo() {
        return extraGameInfo;
    }

    /**
     * The code for the country of residence of the user.
     *
     * @return the user's country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * The code for the state of residence of the user.
     *
     * @return the user's state code
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * The code for the city of residence of the user.
     *
     * @return the user's city code
     */
    public int getCityCode() {
        return cityCode;
    }
}
