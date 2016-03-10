package com.steam;

/**
 * NewsItem
 * <p>
 * An object holding relevant news data.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class NewsItem {
    private final long id;
    private final String title;
    private final String url;
    private final String author;
    private final String feedLabel;
    private final String feedName;
    private final String contents;
    private final long date;

    /**
     * Creates a new news item with the specified characteristics.
     *
     * @param id        the identifier for the news item
     * @param title     the title of the news item
     * @param url       the url for the news item
     * @param author    the author of the news item
     * @param feedLabel the label of the feed the news item originated
     * @param feedName  the name of the feed the news item originated
     * @param contents  the shortened contents of the news item
     * @param date      the date in which the news item was posted
     */
    public NewsItem(long id, String title, String url, String author, String feedLabel, String feedName, String contents, long date) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.author = author;
        this.feedLabel = feedLabel;
        this.feedName = feedName;
        this.contents = contents;
        this.date = date;
    }

    /**
     * The identifier for the news item
     *
     * @return the news item identifier
     */
    public long getId() {
        return id;
    }

    /**
     * The title of the news item.
     *
     * @return the news item title
     */
    public String getTitle() {
        return title;
    }

    /**
     * The url for the news item.
     *
     * @return the news item url
     */
    public String getUrl() {
        return url;
    }

    /**
     * The author of the news item.
     *
     * @return the news item author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * The label of the feed in which the news item originated.
     *
     * @return the news item feed label
     */
    public String getFeedLabel() {
        return feedLabel;
    }

    /**
     * The name of the feed in which the news item originated.
     *
     * @return the news item feed name
     */
    public String getFeedName() {
        return feedName;
    }

    /***
     * The shortened contents of the news item.
     *
     * @return the shortened news item contents
     */
    public String getContents() {
        return contents;
    }

    /**
     * The date in which the news item was posted.
     *
     * @return the news item post date
     */
    public long getDate() {
        return date;
    }
}
