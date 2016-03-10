package com.iancaffey.steam.trade;

import com.iancaffey.steam.RequestMethod;
import com.iancaffey.steam.Steam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * MarketService
 * <p>
 * An object which provides methods for retrieving market information of items.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class MarketService {
    private static final String INVENTORY_PATTERN = "http://steamcommunity.com/market/priceoverview/?currency=%s&appid=%s&market_hash_name=%s";
    private final Steam steam;

    /**
     * Creates a new market service for the Steam API.
     *
     * @param steam the Steam WebAPI wrapper
     */
    public MarketService(Steam steam) {
        if (steam == null)
            throw new IllegalArgumentException();
        this.steam = steam;
    }

    /**
     * Retrieves the recent price information for the specified item in USD.
     *
     * @param item the item to retrieve price information for
     * @return the recent price information for the specified item
     */
    public PriceHistory lookup(Item item) {
        return lookup(item, Currency.USD);
    }

    /**
     * Retrieves the recent price information for the specified item in the desired currency format.
     *
     * @param item     the item to retrieve price information for
     * @param currency the currency to return the prices in
     * @return the recent price information for the specified item
     */
    public PriceHistory lookup(Item item, Currency currency) {
        if (item == null)
            return null;
        long gameId = item.getGameId();
        if (gameId == -1)
            return null;
        ItemDescription description = item.getDescription();
        if (description == null)
            return null;
        if (!description.isTradable())
            return null;
        String marketHashName = description.getMarketHashName();
        if (marketHashName == null)
            return null;
        PriceHistory history;
        try {
            history = steam.getDataParser().build(PriceHistory.class, steam.getCommunicator().retrieve(String.format(INVENTORY_PATTERN, (currency == null ? Currency.USD : currency).getToken(), gameId, URLEncoder.encode(marketHashName, "UTF-8")), RequestMethod.GET));
            if (history == null)
                return null;
            history.setCurrency(currency);
            return history;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
