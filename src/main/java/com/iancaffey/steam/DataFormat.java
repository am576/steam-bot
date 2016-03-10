package com.iancaffey.steam;

/**
 * DataFormat
 * <p>
 * The type of file formatting used in queries of the Steam API.
 * The default format of the Steam API is JSON.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public enum DataFormat {
    /**
     * The API returns an object containing the named object with the result data.
     * Arrays are represented as an array with the name of the type of the objects in the array
     * (ie. an object named "items" containing an array of objects of type "item" would be represented as an object named "items" containing an array named "item" containing several objects following the "item" structure).
     * Null is represented as JSON's null.
     */
    JSON,
    /**
     * XML Attributes are not used.
     * Arrays are represented as a series of sub-elements in the containing element of the type of the array.
     * Null is represented by the word "null" between the element's tags.
     */
    XML,
    /**
     * Valve's internal data format, as seen in uses like TF2's "scripts" folder (available in "team fortress 2 client content.gcf").
     * TF2's GetSchema returns data similar to "items/items_game.txt" (although qualities are not expanded into objects with a "value" field).
     * Arrays in the data are represented as a VDF array with the name of the type of the objects in the array, with a VDF array being an object with each item being prefixed with its numeric key as a quoted string.
     * Null is represented as an empty string.
     */
    VDF,
    /**
     * A basic comma separated file which can be opened in excel or translated to JSON.
     */
    CSV;

    /**
     * The token to be added to the query string
     *
     * @return the query string token
     */
    public String getToken() {
        return name().toLowerCase();
    }
}
