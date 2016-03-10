package com.steam;

import com.steam.gson.*;
import com.steam.trade.Item;
import com.steam.trade.Offer;
import com.steam.trade.PriceHistory;
import com.steam.trade.TradeHistory;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * DataParser
 * <p>
 * An object which converts file formats into JSON and returns the appropriate object which is mapped by type adapters.
 *
 * @author Ian Caffey
 * @since 1.0
 */
//TODO:Go through all adapters and mimick the steam JSON format to ensure parsing object back and forth will return consistent data
public class DataParser {
    private final GsonBuilder builder;

    /**
     * Creates a new data parser with preset type adapters.
     */
    public DataParser() {
        this(new GsonBuilder()
                .registerTypeAdapter(BanHistory[].class, new BanHistoryAdapter())
                .registerTypeAdapter(Boolean.class, new ResponseAdapter())
                .registerTypeAdapter(Friend[].class, new FriendAdapter())
                .registerTypeAdapter(GameAchievementPercentages.class, new GameAchievementPercentagesAdapter())
                .registerTypeAdapter(Game[].class, new GameAdapter())
                .registerTypeAdapter(GameNews.class, new GameNewsAdapter())
                .registerTypeAdapter(GameSchema.class, new GameSchemaAdapter())
                .registerTypeAdapter(GameStats.class, new GameStatsAdapter())
                .registerTypeAdapter(Item[].class, new ItemAdapter())
                .registerTypeAdapter(Long.class, new SharedGameOwnerAdapter())
                .registerTypeAdapter(Offer.class, new OfferAdapter())
                .registerTypeAdapter(PriceHistory.class, new PriceHistoryAdapter())
                .registerTypeAdapter(TradeHistory.class, new TradeHistoryAdapter())
                .registerTypeAdapter(UserAchievements.class, new UserAchievementsAdapter())
                .registerTypeAdapter(UserGameStats.class, new UserGameStatsAdapter())
                .registerTypeAdapter(UserProfile[].class, new UserProfileAdapter()));
    }

    /**
     * Creates a new data parser with a preset JSON object builder
     *
     * @param builder the JSON object builder
     * @throws IllegalArgumentException if <code>builder == null</code>
     */
    public DataParser(GsonBuilder builder) {
        if (builder == null)
            throw new IllegalArgumentException();
        this.builder = builder;
    }

    /**
     * Parses a block of text into a JSON format.
     *
     * @param data   the block of text to parse
     * @param format the original data format the text represents
     * @return a string representing a JSON object
     */
    public String parse(String data, DataFormat format) {
        if (format == null || format == DataFormat.JSON)
            return data;
        //TODO:Parse other formats into JSON
        throw new IllegalArgumentException(format + " is not currently supported.");
    }

    /**
     * Builds a new object from a block of text using registered type adapters.
     * This method assumes the data is in JSON format and will not do any additional parsing.
     *
     * @param model the model object class
     * @param data  the block of text which contains the values of the object
     * @param <T>   the type of object being built
     * @return a new instance of the model class with the values from the block of text
     */
    public <T> T build(Class<T> model, String data) {
        return build(model, data, DataFormat.JSON);
    }

    /**
     * Builds a new object from a block of text using registered type adapters.
     * If the original format is not in JSON, the block of text will be parsed into JSON then interpreted.
     *
     * @param model  the model object class
     * @param data   the block of text which contains the values of the object
     * @param format the original data format the text represents
     * @param <T>    the type of object being built
     * @return a new instance of the model class with the values from the block of text
     */
    public <T> T build(Class<T> model, String data, DataFormat format) {
        if (model == null || model == Void.class)
            return null;
        if (data == null) {
            if (!model.isPrimitive())
                return null;
            return Number.class.isAssignableFrom(model) ? (T) Integer.valueOf(-1) : (T) Boolean.valueOf(false);
        }
        T instance = null;
        try {
            instance = builder.create().fromJson(parse(data, format), model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (instance != null || !model.isPrimitive())
            return instance;
        return Number.class.isAssignableFrom(model) ? (T) Integer.valueOf(-1) : (T) Boolean.valueOf(false);
    }

    /**
     * Parses an object using registered type adapters to write pertinent object info into JSON.
     *
     * @param item the object to parse
     * @return the objects pertinent information in a JSON format
     */
    public String parse(Object item) {
        return builder.create().toJson(item);
    }

    /**
     * Registers a type adapter for a specific model class.
     *
     * @param type   the model class
     * @param object an instance of the type adapter
     * @return this
     */
    public DataParser register(Type type, Object object) {
        builder.registerTypeAdapter(type, object);
        return this;
    }
}
