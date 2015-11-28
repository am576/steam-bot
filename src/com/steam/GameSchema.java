package com.steam;

import java.util.Arrays;

/**
 * GameSchema
 * <p>
 * An object holding pertinent game meta data, such as current version, possible achievements, and game statistics.
 *
 * @author Ian
 * @version 1.0
 */
public class GameSchema {
    private final String name;
    private final int version;
    private final GameAchievement[] achievements;
    private final GameStat[] stats;

    /**
     * Creates a new game schema with the specified characteristics.
     *
     * @param name         the name of the game
     * @param version      the latest release version of the game
     * @param achievements the possible game achievements
     * @param stats        the current game stats
     */
    public GameSchema(String name, int version, GameAchievement[] achievements, GameStat[] stats) {
        this.name = name;
        this.version = version;
        this.achievements = achievements;
        this.stats = stats;
    }

    /**
     * The name of the game.
     *
     * @return the game name
     */
    public String getName() {
        return name;
    }

    /**
     * The current release version of the game.
     *
     * @return the current game release version
     */
    public int getVersion() {
        return version;
    }

    /**
     * All of the possible game achievements.
     *
     * @return all possible game achievements
     */
    public GameAchievement[] getAchievements() {
        return achievements == null ? null : Arrays.copyOf(achievements, achievements.length);
    }

    /**
     * The current stats for the game.
     *
     * @return the current game stats
     */
    public GameStat[] getStats() {
        return stats == null ? null : Arrays.copyOf(stats, stats.length);
    }
}
