package iitc.steam;

import java.util.Arrays;

/**
 * GameNews
 * <p>
 * An object holding various news items for a certain game.
 *
 * @author Ian
 * @version 1.0
 */
public class GameNews {
    private final int gameId;
    private final NewsItem[] newsItems;

    /**
     * Creates a new game news with the specified characteristics.
     *
     * @param gameId    the identifier for the game the news is for
     * @param newsItems the news items for the game
     */
    public GameNews(int gameId, NewsItem... newsItems) {
        this.gameId = gameId;
        this.newsItems = newsItems;
    }

    /**
     * The identifier for the game the news is for.
     *
     * @return the game identifier
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * The news items for the game.
     *
     * @return the game news items
     */
    public NewsItem[] getNewsItems() {
        return newsItems == null ? null : Arrays.copyOf(newsItems, newsItems.length);
    }
}
