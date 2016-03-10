package com.iancaffey.steam;

import java.util.Map;

/**
 * UserGameStats
 * <p>
 * An object which holds a statistics listing and basic user/game information.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class UserGameStats extends GameStats {
    private final long userId;
    private final String gameName;
    private final Map<String, Boolean> achievementMapping;

    /**
     * Creates a new game stats mapping using the existing mapping.
     *
     * @param userId             the identifier of the user
     * @param gameName           the name of the game
     * @param statMapping        the stat mapping using stat name/key
     * @param achievementMapping the accomplished achievements of the user
     */
    public UserGameStats(long userId, String gameName, Map<String, Long> statMapping, Map<String, Boolean> achievementMapping) {
        super(statMapping);
        if (achievementMapping == null)
            throw new IllegalArgumentException();
        this.userId = userId;
        this.gameName = gameName;
        this.achievementMapping = achievementMapping;
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
     * The name of the game the stats are for.
     *
     * @return the game the stats are for
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * All of the achievements for the user.
     *
     * @return the user achievement names
     */
    public String[] getAchievementNames() {
        return achievementMapping.keySet().toArray(new String[achievementMapping.size()]);
    }

    /**
     * Whether or not the user has accomplished the achievement.
     *
     * @param achievement the achievement mapping name/key
     * @return <code>true</code> if the user has accomplished the achievement, <code>false</code> otherwise
     */
    public boolean hasAchieved(String achievement) {
        Boolean accomplished = achievementMapping.get(achievement);
        return accomplished != null && accomplished;
    }
}
