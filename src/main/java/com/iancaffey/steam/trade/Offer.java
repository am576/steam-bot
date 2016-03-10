package com.iancaffey.steam.trade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Offer
 * <p>
 * An object representing a Steam trade offer between two users.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class Offer {
    /**
     * The state of an invalid offer
     */
    public static final int INVALID = 1;
    /**
     * The trade offer has been sent, neither party has acted on it yet.
     */
    public static final int ACTIVE = 2;
    /**
     * The trade offer was accepted by the recipient and items were exchanged.
     */
    public static final int ACCEPTED = 3;
    /**
     * The recipient made a counter offer.
     */
    public static final int COUNTERED = 4;
    /**
     * The trade offer was not accepted before the expiration date.
     */
    public static final int EXPIRED = 5;
    /**
     * The sender cancelled the offer.
     */
    public static final int CANCELED = 6;
    /**
     * The recipient declined the offer.
     */
    public static final int DECLINED = 7;
    /**
     * Some of the items in the offer are no longer available (indicated by Item#isMissing()).
     */
    public static final int INVALID_ITEMS = 8;
    /**
     * The offer hasn't been sent yet and is awaiting email confirmation.
     */
    public static final int EMAIL_PENDING = 9;
    /**
     * The receiver cancelled the offer via email.
     */
    public static final int EMAIL_CANCELED = 10;
    private int offerId;
    private long traderId;
    private String message;
    private long expirationTime;
    private int state;
    private List<Item> givenItems;
    private List<Item> receivedItems;
    private boolean owned;
    private long timeCreated;
    private long timeUpdated;
    private boolean fromRealTimeTrade;

    /**
     * Creates a new trade offer of the specified characteristics.
     *
     * @param offerId           the unique identifier for the trade offer
     * @param traderId          the partner in the trade offer
     * @param message           the message included by the creator of the trade offer
     * @param expirationTime    unix time when the offer will expire (or expired, if it is in the past)
     * @param state             the current state of the offer
     * @param givenItems        items given/to be given in the trade
     * @param receivedItems     items received/to receive in the trade
     * @param owned             the flag indicating if the current user created the offer
     * @param timeCreated       the timestamp of when the offer was sent
     * @param timeUpdated       the timestamp of when the offer was last changed
     * @param fromRealTimeTrade the flag indicating that the offer is automatically created from a real-time trade
     */
    public Offer(int offerId, long traderId, String message, long expirationTime, int state, Item[] givenItems, Item[] receivedItems, boolean owned, long timeCreated, long timeUpdated, boolean fromRealTimeTrade) {
        this.offerId = offerId;
        this.traderId = traderId;
        this.message = message;
        this.expirationTime = expirationTime;
        this.state = state;
        this.givenItems = new ArrayList<>();
        if (givenItems != null)
            Collections.addAll(this.givenItems, givenItems);
        this.receivedItems = new ArrayList<>();
        if (receivedItems != null)
            Collections.addAll(this.receivedItems, receivedItems);
        this.owned = owned;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.fromRealTimeTrade = fromRealTimeTrade;
    }

    /**
     * The unique identifier for this trade offer.
     *
     * @return the unique trade offer identifier
     * @see TradeService#getOffer(int)
     * @see TradeService#acceptOffer(Offer)
     * @see TradeService#cancelOffer(Offer)
     * @see TradeService#declineOffer(Offer)
     */
    public int getOfferId() {
        return offerId;
    }

    /**
     * Updates the unique trade offer.
     *
     * @param offerId the new unique trade offer identifier
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void setOfferId(int offerId) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expired. The offer may not be modified.");
        this.offerId = offerId;
    }

    /**
     * The other partner involved in the trade.
     *
     * @return the other partner involved in the trade
     * If <code>Offer#isOwned() == false</code>, then the current account identifier will be returned.
     */
    public long getTraderId() {
        return traderId;
    }

    /**
     * Updates the partner identifier associated with the trade offer.
     *
     * @param traderId the new partner identifier
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void setTraderId(long traderId) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expired. The offer may not be modified.");
        this.traderId = traderId;
    }

    /**
     * The original message supplied by the offer creator.
     *
     * @return the message associated with this trade offer, created by the trade owner
     */
    public String getMessage() {
        return message;
    }

    /**
     * Updates the original message of the trade offer
     *
     * @param message the new offer message
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void setMessage(String message) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expired. The offer may not be modified.");
        this.message = message;
    }

    /**
     * The time when the offer will expire and becomes invalid.
     * This time may have already passed.
     *
     * @return the expiration time
     */
    public long getExpirationTime() {
        return expirationTime;
    }

    /**
     * Updates the expiration time.
     *
     * @param expirationTime the new expiration time
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void setExpirationTime(long expirationTime) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expired. The offer may not be modified.");
        this.expirationTime = expirationTime;
    }

    /**
     * The current state of the offer.
     *
     * @return the current offer state
     */
    public int getState() {
        return state;
    }

    /**
     * Updates the current state of the trade offer
     *
     * @param state the new offer state
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * Items to be given up by the current account (regardless of offer creator) associated with the WebAPI key.
     *
     * @return the items to be given up
     */
    public Item[] getGivenItems() {
        return givenItems.toArray(new Item[givenItems.size()]);
    }

    /**
     * Updates the items to be given up by the current account associated with the WebAPI key.
     *
     * @param givenItems the new items to be given up
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void setGivenItems(Item[] givenItems) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expiredd. The item set may not be modified.");
        List<Item> items = new ArrayList<>();
        if (givenItems != null)
            Collections.addAll(items, givenItems);
        this.givenItems = items;
    }

    /**
     * Adds an item to be given up by the current account associated with the WebAPI key.
     *
     * @param item the new item to be given up
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void addGivenItem(Item item) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expiredd. The item set may not be modified.");
        givenItems.add(item);
    }

    /**
     * Removes an item to be given up by the current account associated with the WebAPI key.
     *
     * @param item the new item to not be given up
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void removeGivenItem(Item item) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expiredd. The item set may not be modified.");
        givenItems.remove(item);
    }

    /**
     * Items to be received by the current account (regardless of offer creator) associated with the WebAPI key.
     *
     * @return the items to be received
     */
    public Item[] getReceivedItems() {
        return receivedItems.toArray(new Item[receivedItems.size()]);
    }

    /**
     * Updates the items to be received by the current account associated with the WebAPI key.
     *
     * @param receivedItems the new items to be received
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void setReceivedItems(Item[] receivedItems) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expiredd. The item set may not be modified.");
        List<Item> items = new ArrayList<>();
        if (receivedItems != null)
            Collections.addAll(items, receivedItems);
        this.receivedItems = items;
    }

    /**
     * Adds an item to be received by the current account associated with the WebAPI key.
     *
     * @param item the new item to be received
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void addReceivedItem(Item item) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expiredd. The item set may not be modified.");
        receivedItems.add(item);
    }

    /**
     * Removes an item to be received by the current account associated with the WebAPI key.
     *
     * @param item the new item to not be received
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void removeReceivedItem(Item item) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expired. The offer may not be modified.");
        receivedItems.remove(item);
    }

    /**
     * Whether or not the trade offer was created by the account associated with the WebAPI key.
     *
     * @return <code>true</code> if the current account associated with the WebAPI key create the offer, <code>false</code> otherwise
     */
    public boolean isOwned() {
        return owned;
    }

    /**
     * Updates the ownership status of the trade offer.
     *
     * @param owned the status of ownership
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void setOwned(boolean owned) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expired. The offer may not be modified.");
        this.owned = owned;
    }

    /**
     * The time in which the offer was created and sent.
     *
     * @return the creation/send time
     */
    public long getTimeCreated() {
        return timeCreated;
    }

    /**
     * Updates the time in which the offer was created and sent
     *
     * @param timeCreated the new creation/send time
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void setTimeCreated(long timeCreated) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expired. The offer may not be modified.");
        this.timeCreated = timeCreated;
    }

    /**
     * The time in which the offer was last updated.
     *
     * @return the last update time
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public long getTimeUpdated() {
        return timeUpdated;
    }

    /**
     * Updates the time in which the offer was last updated
     *
     * @param timeUpdated the new last update time
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void setTimeUpdated(long timeUpdated) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expired. The offer may not be modified.");
        this.timeUpdated = timeUpdated;
    }

    /**
     * Whether or not the offer was created from a real-time trade
     *
     * @return <code>true</code> if the offer was created in a real-time trade, <code>false</code> otherwise
     */
    public boolean isFromRealTimeTrade() {
        return fromRealTimeTrade;
    }

    /**
     * Updates whether or not the offer was created from a real-time trade
     *
     * @param fromRealTimeTrade the new state of being created from a real-time trade
     * @throws IllegalStateException if the offer can been accepted or has expired
     */
    public void setFromRealTimeTrade(boolean fromRealTimeTrade) {
        int state = getState();
        if (state == ACCEPTED || state == CANCELED || state == EMAIL_CANCELED || state == COUNTERED)
            throw new IllegalStateException("Offer has already been accepted, cancelled, or has expired. The offer may not be modified.");
        this.fromRealTimeTrade = fromRealTimeTrade;
    }
}
