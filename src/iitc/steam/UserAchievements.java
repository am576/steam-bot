package iitc.steam;

import java.util.Arrays;
import java.util.Map;

/**
 * UserAchievements
 * <p>
 * An object holding information about playing achievements and looking up the user progress of said achievements.
 *
 * @author Ian
 * @version 1.0
 */
public class UserAchievements {
    private final long userId;
    private final String name;
    private final String[] names;
    private final Map<String, String> localizedNames;
    private final Map<String, String> localizedDescriptions;
    private final Map<String, Boolean> achieved;

    /**
     * Creates a new user achievement listing with the specified mappings.
     *
     * @param userId                the user identifier for the friend
     * @param name                  the display name of the game
     * @param names                 all of the possible achievement names for the user
     * @param localizedNames        the localized names of the achievements
     * @param localizedDescriptions the localized descriptions of the achievements
     * @param achieved              the listing of whether achievements have been achieved
     */
    public UserAchievements(long userId, String name, String[] names, Map<String, String> localizedNames, Map<String, String> localizedDescriptions, Map<String, Boolean> achieved) {
        if (userId == -1 || names == null || localizedNames == null || localizedDescriptions == null || achieved == null)
            throw new IllegalArgumentException();
        this.userId = userId;
        this.name = name;
        this.names = names;
        this.localizedNames = localizedNames;
        this.localizedDescriptions = localizedDescriptions;
        this.achieved = achieved;
    }

    /**
     * The identifier of the user whose achievements belong to.
     *
     * @return the user identifier
     */
    public long getUserId() {
        return userId;
    }

    /**
     * The display name of the game whose achievements relate to.
     *
     * @return the game display name
     */
    public String getGameName() {
        return name;
    }

    /**
     * All of the achievements the user could possible achieve.
     *
     * @return all possible user achievements
     */
    public String[] getAchievementNames() {
        return Arrays.copyOf(names, names.length);
    }

    /**
     * The localized achievement name, if it exists.
     *
     * @param achievement the achievement mapping name/key
     * @return the localized achievement name, if it exists
     */
    public String getLocalizedAchievementName(String achievement) {
        return localizedNames.get(achievement);
    }

    /**
     * The localized achievement description, if it exists.
     *
     * @param achievement the achievement mapping name/key
     * @return the localized achievement description, if it exists
     */
    public String getLocalizedDescription(String achievement) {
        return localizedDescriptions.get(achievement);
    }

    /**
     * Whether or not the user has accomplished the achievement.
     *
     * @param achievement the achievement mapping name/key
     * @return <code>true</code> if the user has accomplished the achievement, <code>false</code> otherwise
     */
    public boolean hasAchieved(String achievement) {
        Boolean accomplished = achieved.get(achievement);
        return accomplished != null && accomplished;
    }
}
