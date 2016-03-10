package com.iancaffey.steam;

/**
 * BanHistory
 * <p>
 * An object holding information about the ban history of a player.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class BanHistory {
    private final long userId;
    private final boolean communityBan;
    private final boolean vacBan;
    private final int vacBanCount;
    private final int daysSinceLastBan;
    private final int banCount;
    private final String economyBan;

    /**
     * Creates a new ban history for a user with the specified characteristics
     *
     * @param userId           the identifier of the user the ban history is associated with
     * @param communityBan     whether or not the user is banned from Steam Community
     * @param vacBan           whether or not the user has VAC bans on record
     * @param vacBanCount      the number of VAC bans on record
     * @param daysSinceLastBan the number of days since the last ban
     * @param banCount         the number of bans the user has been issued
     * @param economyBan       the user's current economy status
     */
    public BanHistory(long userId, boolean communityBan, boolean vacBan, int vacBanCount, int daysSinceLastBan, int banCount, String economyBan) {
        this.userId = userId;
        this.communityBan = communityBan;
        this.vacBan = vacBan;
        this.vacBanCount = vacBanCount;
        this.daysSinceLastBan = daysSinceLastBan;
        this.banCount = banCount;
        this.economyBan = economyBan;
    }

    /**
     * The identifier of the user.
     *
     * @return the user identifier
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Whether or not the user is banned from Steam Community.
     *
     * @return <code>true</code> if the user is banned from Steam Community, <code>false</code> otherwise
     */
    public boolean isCommunityBanned() {
        return communityBan;
    }

    /**
     * Whether or not the user is VAC banned.
     *
     * @return <code>true</code> if the user is VAC banned, <code>false</code> otherwise
     */
    public boolean isVacBanned() {
        return vacBan;
    }

    /**
     * The number of VAC bans on record for the user.
     *
     * @return the user's total number of VAC bans on record
     */
    public int getVacBanCount() {
        return vacBanCount;
    }

    /**
     * The number of days since the last ban issued for the user.
     *
     * @return the number of days since last ban
     */
    public int getDaysSinceLastBan() {
        return daysSinceLastBan;
    }

    /**
     * The total number of bans the user has been issued.
     *
     * @return the total number of user bans
     */
    public int getBanCount() {
        return banCount;
    }

    /**
     * The user's current economy status on Steam Community.
     *
     * @return the user's Steam Community economy status
     */
    public String getEconomyBan() {
        return economyBan;
    }
}
