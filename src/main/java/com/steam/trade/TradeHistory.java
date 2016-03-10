package com.steam.trade;

/**
 * TradeHistory
 * <p>
 * An object representing recent trade offers (up to a maximum of 500 sent or 1000 received regardless of time_historical_cutoff) for the account associated with the WebAPI key.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class TradeHistory {
    private final Offer[] sentOffers;
    private final Offer[] receivedOffers;
    private final ItemDescription[] descriptions;

    /**
     * Creates a new trade history with the specified offers and descriptions.
     *
     * @param sentOffers     offers that have been sent by the current account associated with the WebAPI key
     * @param receivedOffers offers that have been received by the current account associated with the WebAPI key
     * @param descriptions   item descriptions for the sent and/or received offers
     */
    public TradeHistory(Offer[] sentOffers, Offer[] receivedOffers, ItemDescription[] descriptions) {
        this.sentOffers = sentOffers;
        this.receivedOffers = receivedOffers;
        this.descriptions = descriptions;
    }

    /**
     * The recent offers that the current account associated with the WebAPI key have sent.
     *
     * @return the recent sent trade offers
     */
    public Offer[] getSentOffers() {
        return sentOffers;
    }

    /**
     * The recent offers that the current account associated with the WebAPI key have received.
     *
     * @return the recent received trade offers
     */
    public Offer[] getReceivedOffers() {
        return receivedOffers;
    }

    /**
     * The item descriptions for the sent and/or received offers.
     *
     * @return the item descriptions
     */
    public ItemDescription[] getDescriptions() {
        return descriptions;
    }
}
