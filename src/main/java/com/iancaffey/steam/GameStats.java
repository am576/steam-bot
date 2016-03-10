package com.iancaffey.steam;

import java.util.Map;

/**
 * GameStats
 * <p>
 * An object holding a stat listing for a certain game.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class GameStats {
    private final Map<String, Long> statMapping;

    /**
     * Creates a new game stats mapping using the existing mapping.
     *
     * @param statMapping the stat mapping using stat name/key
     */
    public GameStats(Map<String, Long> statMapping) {
        if (statMapping == null)
            throw new IllegalArgumentException();
        this.statMapping = statMapping;
    }

    /**
     * The current value of a stat for the game.
     *
     * @param name the mapping name/key of the stat
     * @return the current stat value
     */
    public long getStat(String name) {
        Long stat = statMapping.get(name);
        return stat == null ? -1 : stat;
    }
}
