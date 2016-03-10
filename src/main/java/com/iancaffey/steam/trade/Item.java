package com.iancaffey.steam.trade;

/**
 * Item
 * <p>
 * An object representing an item involved in a Steam trade.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class Item {
    private long gameId;
    private int contextId;
    private long assetId;
    private int currencyId;
    private int classId;
    private int instanceId;
    private int amount;
    private boolean missing;
    private int slot;
    private ItemDescription description;

    /**
     * Creates a new trade item with the specified characteristics.
     *
     * @param gameId      the identifier for the game the item belongs to
     * @param contextId   the identifier for the inventory
     * @param assetId     the trade identifier for the item
     * @param currencyId  the currency identifier for the item
     * @param classId     the identifier of the class the item belongs to
     * @param instanceId  the unique identifier for the item
     * @param amount      the amount offered in the trade, for stackable items and currency
     * @param missing     whether the item is no longer present in the user's inventory
     * @param slot        the slot index of the item in the inventory
     * @param description the pertinent information about this item
     */
    public Item(long gameId, int contextId, long assetId, int currencyId, int classId, int instanceId, int amount, boolean missing, int slot, ItemDescription description) {
        this.gameId = gameId == 0 ? -1 : gameId;
        this.contextId = contextId == 0 ? -1 : contextId;
        this.assetId = assetId == 0 ? -1 : assetId;
        this.currencyId = currencyId == 0 ? -1 : currencyId;
        this.classId = classId == 0 ? -1 : classId;
        this.instanceId = instanceId == 0 ? -1 : instanceId;
        this.amount = amount;
        this.missing = missing;
        this.slot = slot == 0 ? -1 : slot;
        this.description = description;
    }

    /**
     * The identifier for the game the item belongs to.
     *
     * @return the current game identifier
     */
    public long getGameId() {
        return gameId;
    }

    /**
     * Updates the identifier for the game that the item belongs to.
     *
     * @param gameId the new current game identifier
     */
    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    /**
     * The identifier for the user's inventory
     *
     * @return the inventory identifier
     */
    public int getContextId() {
        return contextId;
    }

    /**
     * Updates the identifier for the user's inventory
     *
     * @param contextId the new inventory identifier
     */
    public void setContextId(int contextId) {
        this.contextId = contextId;
    }

    /**
     * The current trade identifier for the item.
     *
     * @return the current trade identifier
     */
    public long getAssetId() {
        return assetId;
    }

    /**
     * Updates the current trade identifier for the item.
     *
     * @param assetId the new trade identifier
     */
    public void setAssetId(long assetId) {
        this.assetId = assetId;
    }

    /**
     * The currency identifier for the item.
     *
     * @return the currency identifier
     */
    public int getCurrencyId() {
        return currencyId;
    }

    /**
     * Updates the currency identifier for the item.
     *
     * @param currencyId the new currency identifier
     */
    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * The class identifier for which the item belongs to.
     *
     * @return the item class identifier
     */
    public int getClassId() {
        return classId;
    }

    /**
     * Updates the class identifier in which the item belongs to.
     *
     * @param classId the new class identifier
     */
    public void setClassId(int classId) {
        this.classId = classId;
    }

    /**
     * The unique identifier for the item.
     *
     * @return the unique item identifier
     */
    public int getInstanceId() {
        return instanceId;
    }

    /**
     * Updates the unique identifier for the item.
     *
     * @param instanceId the new identifier
     */
    public void setInstanceId(int instanceId) {
        this.instanceId = instanceId;
    }

    /**
     * The amount offered in the trade, for stackable items and currency.
     *
     * @return the offered amount in the trade
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Updates the amount offered in the trade, for stackable items and currency.
     *
     * @param amount the new amount offered in the trade
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Whether or not the item is no longer present in the user's inventory.
     *
     * @return <code>true</code> if the item is no longer present in the user's inventory, <code>false</code> otherwise
     */
    public boolean isMissing() {
        return missing;
    }

    /**
     * Updates the flag specifying whether or not the item is present in the user's inventory.
     *
     * @param missing the new presence of the item in the user's inventory
     */
    public void setMissing(boolean missing) {
        this.missing = missing;
    }

    /**
     * The slot index of the item in the inventory.
     *
     * @return the inventory slot index of the item
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Updates the slot index of the item in the inventory.
     *
     * @param slot the new inventory slot index
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * The pertinent information about this item.
     *
     * @return the pertinent information about this item
     */
    public ItemDescription getDescription() {
        return description;
    }

    /**
     * Updates the pertinent information about this item.
     *
     * @param description the new information about this item
     */
    public void setDescription(ItemDescription description) {
        this.description = description;
    }

    /**
     * Prints out a nice string which displays the item's name, if available.
     *
     * @return the item descriptor
     */
    @Override
    public String toString() {
        ItemDescription description = getDescription();
        if (description == null) {
            return super.toString();
        }
        return String.format("%s[name=\"%s\"]", getClass().getCanonicalName(), description.getName());
    }
}
