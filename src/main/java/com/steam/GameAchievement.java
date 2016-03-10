package com.steam;

/**
 * GameAchievement
 * <p>
 * An object holding pertinent game achievement meta data.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class GameAchievement {
    private final String name;
    private final long defaultValue;
    private final String displayName;
    private final boolean hidden;
    private final String description;
    private final String iconURL;
    private final String iconGreyURL;

    /**
     * Creates a new game achievement with the specified characteristics.
     *
     * @param name         the mapping name/key of the achievement
     * @param defaultValue the default value for the achievement
     * @param displayName  the formal display name of the achievement
     * @param hidden       whether or not the achievement is visible or not
     * @param description  the description of the achievement
     * @param iconURL      the url of the achievement icon url
     * @param iconGreyURL  the url of the achievement icon, grey version, url
     */
    public GameAchievement(String name, long defaultValue, String displayName, boolean hidden, String description, String iconURL, String iconGreyURL) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.displayName = displayName;
        this.hidden = hidden;
        this.description = description;
        this.iconURL = iconURL;
        this.iconGreyURL = iconGreyURL;
    }

    /**
     * The mapping name/key of the achievement.
     *
     * @return the achievement mapping name/key
     */
    public String getName() {
        return name;
    }

    /**
     * The default value of the achievement.
     *
     * @return the achievement default value
     */
    public long getDefaultValue() {
        return defaultValue;
    }

    /**
     * The formal display name of the achievement.
     *
     * @return the formal achievement display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Whether or not the achievement is visible or not.
     *
     * @return <code>true</code> if the achievement is hidden, <code>false</code> otherwise
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * The description of the achievement.
     *
     * @return the achievement description
     */
    public String getDescription() {
        return description;
    }

    /**
     * The url for the achievement icon.
     *
     * @return the achievement icon url
     */
    public String getIconURL() {
        return iconURL;
    }

    /**
     * The url for the grey achievement icon.
     *
     * @return the grey achievement icon url
     */
    public String getIconGreyURL() {
        return iconGreyURL;
    }
}
