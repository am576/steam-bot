package com.steam;

/**
 * GameStat
 * <p>
 * An object holding the pertinent information for game statistics.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class GameStat {
    private final String name;
    private final long defaultValue;
    private final String displayName;

    /**
     * Creates a new game stat with the specified characteristics.
     *
     * @param name         the mapping name/key of the stat
     * @param defaultValue the default value of the stat
     * @param displayName  the display name of the stat
     */
    public GameStat(String name, long defaultValue, String displayName) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.displayName = displayName;
    }

    /**
     * The mapping name/key of the stat.
     *
     * @return the stat mapping name/key
     */
    public String getName() {
        return name;
    }

    /**
     * The default value for the stat.
     *
     * @return the default stat value
     */
    public long getDefaultValue() {
        return defaultValue;
    }

    /**
     * The display name of the stat.
     *
     * @return the stat display name
     */
    public String getDisplayName() {
        return displayName;
    }
}
