package com.iancaffey.steam;

import java.util.Map;

/**
 * GameAchievementPercentages
 * <p>
 * An object holding the achievement percentages for a game.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class GameAchievementPercentages {
    private final Map<String, Double> mapping;

    /**
     * Creates a new game achievement percentage mapping using the existing mapping.
     *
     * @param mapping the percentage mapping by achievement mapping name/key
     */
    public GameAchievementPercentages(Map<String, Double> mapping) {
        if (mapping == null)
            throw new IllegalArgumentException();
        this.mapping = mapping;
    }

    /**
     * The current percentage for an achievement for a game.
     *
     * @param name the mapping name/key of the achievement
     * @return the current achievement percentage
     */
    public double getPercentage(String name) {
        Double percentage = mapping.get(name);
        return percentage == null ? -1.0D : percentage;
    }
}
