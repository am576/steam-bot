package com.iancaffey.steam.trade;

import com.iancaffey.steam.*;

/**
 * TradeService
 * <p>
 * An object which provides methods related to inventory and trading.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class TradeService {
    private static final String INVENTORY_PATTERN = "http://steamcommunity.com/profiles/%s/inventory/json/%s/%s";
    protected final Steam steam;

    public TradeService(Steam steam) {
        if (steam == null)
            throw new IllegalArgumentException();
        this.steam = steam;
    }

    /**
     * Attempts to cancel a trade offer that has been sent.
     * The trade offer must have been sent by the account associated with the WebAPI key.
     * You cannot call this API for accounts other than that.
     *
     * @param offer the trade offer to cancel
     * @return <code>true</code> if the offer is associated with the account of the WebAPI key and the offer has been canceled,
     * <code>false</code> otherwise.
     */
    public boolean cancelOffer(Offer offer) {
        if (offer == null)
            return false;
        int state = offer.getState();
        if (state == Offer.ACCEPTED || state == Offer.CANCELED || state == Offer.EMAIL_CANCELED || state == Offer.COUNTERED)
            return false;
        boolean cancelled = steam.getDataParser().build(Boolean.class, steam.getCommunicator().retrieve(Method.CANCEL_TRADE_OFFER, offer.getOfferId()));
        if (cancelled)
            offer.setState(Offer.CANCELED);
        return cancelled;
    }

    /**
     * Attempts to decline a trade offer that has been received.
     * The trade offer must have been sent to the account associated with the WebAPI key.
     * You cannot call this API for accounts other than that.
     *
     * @param offer the trade offer to be declined
     * @return <code>true</code> if the offer is associated with the account of the WebAPI key and the offer has been declined,
     * <code>false</code> otherwise.
     */
    public boolean declineOffer(Offer offer) {
        final String declineOfferURL = "https://steamcommunity.com/tradeoffer/" + offer.getOfferId() + "/decline";
        if (offer == null)
            return false;
        int state = offer.getState();
        if (state == Offer.ACCEPTED || state == Offer.CANCELED || state == Offer.EMAIL_CANCELED || state == Offer.COUNTERED)
            return false;
        boolean cancelled = steam.getDataParser().build(Boolean.class, steam.getCommunicator().retrieve(Method.DECLINE_TRADE_OFFER, offer.getOfferId()));
        if (cancelled)
            offer.setState(Offer.DECLINED);
        return cancelled;
        //TODO: Implement method

        //return false;
    }

    /**
     * Attempts to accept a trade offer that has been received.
     * The trade offer must have been sent to the account associated with the WebAPI key.
     * You cannot call this API for accounts other than that.
     *
     * @param offer the trade offer to be accepted
     * @return <code>true</code> if the offer is associated with the account of the WebAPI key and the offer has been accepted,
     * <code>false</code> otherwise.
     */
    public boolean acceptOffer(Offer offer) {
        if (offer == null)
            return false;
        int state = offer.getState();
        if (state == Offer.ACCEPTED || state == Offer.CANCELED || state == Offer.EMAIL_CANCELED || state == Offer.COUNTERED)
            return false;
        //TODO: Work out why this gets a http 411 response
        boolean accepted = steam.getDataParser().build(Boolean.class, steam.getCommunicator().retrieve("http://steamcommunity.com/tradeoffer/" + offer.getOfferId() + "/accept", RequestMethod.POST));
        if (accepted)
            offer.setState(Offer.ACCEPTED);
        return accepted;
    }

    /**
     * Attempts to begin a trade offer that has been received.
     *
     * @param userId the identifier of the user to begin the trade with
     * @return <code>null</code> if the <code>userId</code> doesn't apply to a user who accepts trades or if communications with the WebAPI failed,
     * a new Offer otherwise.
     */
    public Offer beginOffer(long userId) {
        if (userId == -1)
            return null;
        //TODO: Implement method
        // http://api.steampowered.com/IEconService/GetTradeOffers/v1/?key=&get_sent_offers=1&time_historical_cutoff=100
        return null;
    }

    /**
     * Retrieves the current inventory for the account associated with the WebAPI key.
     * The trade offer must have been sent by the account associated with the WebAPI key.
     * You cannot call this API for accounts other than that.
     *
     * @param gameId    the identifier of the game to get items for
     * @param contextId the identifier for the inventory
     * @return the user's inventory, <code>null</code> if the user's profile is private or if web communications failed
     */
    public Item[] getInventory(long gameId, int contextId) {
        return getInventory(steam.getUserId(), gameId, contextId);
    }

    /**
     * Retrieves the current inventory for any public steam account.
     *
     * @param userId    the identifier of the user to get the inventory of
     * @param gameId    the identifier of the game to get items for
     * @param contextId the identifier for the inventory
     * @return the user's inventory, <code>null</code> if the user's profile is private or if web communications failed
     */
    public Item[] getInventory(long userId, long gameId, int contextId) {
        if (userId == -1 || gameId == -1 || contextId == -1)
            return null;
        Item[] items = steam.getDataParser().build(Item[].class, steam.getCommunicator().retrieve(String.format(INVENTORY_PATTERN, userId, gameId, contextId), RequestMethod.GET), DataFormat.JSON);
        if (items == null)
            return null;
        for (Item item : items) {
            item.setGameId(gameId);
            item.setContextId(contextId);
        }
        return items;
    }

    /**
     * Retrieves details about a single trade offer in English using JSON.
     * The trade offer must have been sent to or from the account associated with the WebAPI key.
     *
     * @param offerId the trade offer identifier
     * @return an offer linked to the <code>offerId</code>, <code>null</code> if no offer is found by the <code>offerId</code>
     * or the offer is not within the 500 previous sent trades or previous 1000 received trades.
     * @see Offer
     */
    public Offer getOffer(int offerId) {
        return getOffer(offerId, DataFormat.JSON);
    }

    /**
     * Retrieves details about a single trade offer in English.
     * The trade offer must have been sent to or from the account associated with the WebAPI key.
     *
     * @param offerId the trade offer identifier
     * @param format  the format in which to retrieve the data in
     * @return an offer linked to the <code>offerId</code>, <code>null</code> if no offer is found by the <code>offerId</code>
     * or the offer is not within the 500 previous sent trades or previous 1000 received trades.
     * @see Offer
     */
    public Offer getOffer(int offerId, DataFormat format) {
        return getOffer(offerId, Language.ENGLISH, format);
    }

    /**
     * Retrieves details about a single trade offer using JSON.
     * The trade offer must have been sent to or from the account associated with the WebAPI key.
     *
     * @param offerId             the trade offer identifier
     * @param descriptionLanguage the language to use for the item description
     * @return an offer linked to the <code>offerId</code>, <code>null</code> if no offer is found by the <code>offerId</code>
     * or the offer is not within the 500 previous sent trades or previous 1000 received trades.
     * @see Offer
     */
    public Offer getOffer(int offerId, Language descriptionLanguage) {
        return getOffer(offerId, descriptionLanguage, DataFormat.JSON);
    }

    /**
     * Retrieves details about a single trade offer.
     * The trade offer must have been sent to or from the account associated with the WebAPI key.
     *
     * @param offerId             the trade offer identifier
     * @param descriptionLanguage the language to use for the item description
     * @param format              the format in which to retrieve the data in
     * @return an offer linked to the <code>offerId</code>, <code>null</code> if no offer is found by the <code>offerId</code>
     * or the offer is not within the 500 previous sent trades or previous 1000 received trades.
     * @see Offer
     */
    public Offer getOffer(int offerId, Language descriptionLanguage, DataFormat format) {
        return offerId == -1 ? null : steam.getDataParser().build(Offer.class, steam.getCommunicator().retrieve(Method.GET_TRADE_OFFER, offerId, (descriptionLanguage == null ? Language.ENGLISH : descriptionLanguage).getToken()), format);
    }

    /**
     * Retrieves all the trade history (up to a maximum of 500 sent and 1000 received) with descriptions for the account associated with the WebAPI key in English using JSON.
     *
     * @return a history of trade offers (up to a maximum of 500 sent and 1000 received) for the account associated with the WebAPI key.
     */
    public TradeHistory getHistory() {
        return getHistory(OfferType.ALL);
    }

    /**
     * Retrieves all the trade history (up to a maximum of 500 sent or 1000 received) with descriptions for the account associated with the WebAPI key in English using JSON.
     *
     * @param offerType the type of offers to retrieve
     * @return a history of trade offers (up to a maximum of 500 sent or 1000 received) for the account associated with the WebAPI key.
     */
    public TradeHistory getHistory(OfferType offerType) {
        return getHistory(offerType, DescriptorDepth.THOROUGH, OfferDepth.ALL);
    }

    /**
     * Retrieves the trade history (up to a maximum of 500 sent or 1000 received) for the account associated with the WebAPI key in English using JSON.
     *
     * @param offerType  the type of offers to retrieve
     * @param depth      the depth to retrieve item information
     * @param offerDepth the type of history to retrieve, current or cached
     * @return a history of trade offers (up to a maximum of 500 sent or 1000 received) for the account associated with the WebAPI key.
     */
    public TradeHistory getHistory(OfferType offerType, DescriptorDepth depth, OfferDepth offerDepth) {
        return getHistory(offerType, depth, offerDepth, Language.ENGLISH);
    }

    /**
     * Retrieves the trade history (up to a maximum of 500 sent or 1000 received) for the account associated with the WebAPI key using JSON.
     *
     * @param offerType           the type of offers to retrieve
     * @param depth               the depth to retrieve item information
     * @param offerDepth          the type of history to retrieve, current or cached
     * @param descriptionLanguage language of the descriptions, if requested
     * @return a history of trade offers (up to a maximum of 500 sent or 1000 received) for the account associated with the WebAPI key.
     */
    public TradeHistory getHistory(OfferType offerType, DescriptorDepth depth, OfferDepth offerDepth, Language descriptionLanguage) {
        return getHistory(offerType, depth, offerDepth, -1, descriptionLanguage, DataFormat.JSON);
    }

    /**
     * Retrieves the trade history (up to a maximum of 500 sent or 1000 received regardless of <code>historicalCutoff</code>) for the account associated with the WebAPI key in English using JSON.
     *
     * @param offerType  the type of offers to retrieve
     * @param depth      the depth to retrieve item information
     * @param offerDepth the type of history to retrieve, current or cached
     * @param timestamp  the earliest time an active offer has been created or an outdated offer has last been updated
     * @return a history of trade offers (up to a maximum of 500 sent or 1000 received regardless of <code>historicalCutoff</code>) for the account associated with the WebAPI key.
     */
    public TradeHistory getHistory(OfferType offerType, DescriptorDepth depth, OfferDepth offerDepth, long timestamp) {
        return getHistory(offerType, depth, offerDepth, timestamp, Language.ENGLISH);
    }

    /**
     * Retrieves the trade history (up to a maximum of 500 sent or 1000 received regardless of <code>historicalCutoff</code>) for the account associated with the WebAPI key using JSON.
     *
     * @param offerType           the type of offers to retrieve
     * @param depth               the depth to retrieve item information
     * @param offerDepth          the type of history to retrieve, current or cached
     * @param timestamp           the earliest time an active offer has been created or an outdated offer has last been updated
     * @param descriptionLanguage language of the descriptions, if requested
     * @return a history of trade offers (up to a maximum of 500 sent or 1000 received regardless of <code>historicalCutoff</code>) for the account associated with the WebAPI key.
     */
    public TradeHistory getHistory(OfferType offerType, DescriptorDepth depth, OfferDepth offerDepth, long timestamp, Language descriptionLanguage) {
        return getHistory(offerType, depth, offerDepth, timestamp, descriptionLanguage, DataFormat.JSON);
    }

    /**
     * Retrieves the trade history (up to a maximum of 500 sent or 1000 received regardless of <code>historicalCutoff</code>) for the account associated with the WebAPI key in English.
     *
     * @param offerType  the type of offers to retrieve
     * @param depth      the depth to retrieve item information
     * @param offerDepth the type of history to retrieve, current or cached
     * @param timestamp  the earliest time an active offer has been created or an outdated offer has last been updated
     * @param format     the format in which to retrieve the data in
     * @return a history of trade offers (up to a maximum of 500 sent or 1000 received regardless of <code>historicalCutoff</code>) for the account associated with the WebAPI key.
     */
    public TradeHistory getHistory(OfferType offerType, DescriptorDepth depth, OfferDepth offerDepth, long timestamp, DataFormat format) {
        return getHistory(offerType, depth, offerDepth, timestamp, Language.ENGLISH, format);
    }

    /**
     * Retrieves the trade history (up to a maximum of 500 sent or 1000 received regardless of <code>historicalCutoff</code>) for the account associated with the WebAPI key.
     *
     * @param offerType           the type of offers to retrieve
     * @param depth               the depth to retrieve item information
     * @param offerDepth          the type of history to retrieve, current or cached
     * @param timestamp           the earliest time an active offer has been created or an outdated offer has last been updated
     * @param descriptionLanguage language of the descriptions, if requested
     * @param format              the format in which to retrieve the data in
     * @return a history of trade offers (up to a maximum of 500 sent or 1000 received regardless of <code>historicalCutoff</code>) for the account associated with the WebAPI key.
     */
    public TradeHistory getHistory(OfferType offerType, DescriptorDepth depth, OfferDepth offerDepth, long timestamp, Language descriptionLanguage, DataFormat format) {
        if (offerType == null)
            offerType = OfferType.ALL;
        if (depth == null)
            depth = DescriptorDepth.THOROUGH;
        if (offerDepth == null)
            offerDepth = OfferDepth.HISTORICAL;
        if (descriptionLanguage == null)
            descriptionLanguage = Language.ENGLISH;
        return steam.getDataParser().build(TradeHistory.class, steam.getCommunicator().retrieve(Method.GET_TRADE_HISTORY, offerType != OfferType.RECEIVED, offerType != OfferType.SENT, depth == DescriptorDepth.THOROUGH, descriptionLanguage.getToken(), offerDepth == OfferDepth.CURRENT, offerDepth == OfferDepth.HISTORICAL, timestamp), format);
    }
}
