package iitc.steam.trade;

/**
 * PriceHistory
 * <p>
 * An object representing the current market status of an item, including pricing and current amount of items being traded.
 *
 * @author Ian
 * @version 1.0
 */
public class PriceHistory {
    private float lowestPrice;
    private int volume;
    private float medianPrice;
    private String lowestPriceString;
    private String medianPriceString;
    private Currency currency;

    /**
     * Creates an incomplete price history for an item. The price strings have not been formatted by a currency yet.
     *
     * @param lowestPriceString the unformatted lowest price string
     * @param volume            the amount of the item being currently sold
     * @param medianPriceString the unformatted median price string
     */
    public PriceHistory(String lowestPriceString, int volume, String medianPriceString) {
        this.lowestPrice = -1;
        this.lowestPriceString = lowestPriceString;
        this.volume = volume;
        this.medianPrice = -1;
        this.medianPriceString = medianPriceString;
    }

    /**
     * The currency used for representing the prices of the item.
     *
     * @return the current item currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Updates the currency and the prices associated with the currency. If the currency has not been set yet, the lowest/median prices must be in the format of the currency.
     *
     * @param currency the currency used for representing the prices
     */
    public void setCurrency(Currency currency) {
        if (this.currency == null) {
            if (lowestPriceString != null)
                setLowestPrice(currency.parse(lowestPriceString));
            if (medianPriceString != null)
                setMedianPrice(currency.parse(medianPriceString));
        } else {
            setLowestPrice(this.currency.to(getLowestPrice(), currency));
            setMedianPrice(this.currency.to(getMedianPrice(), currency));
        }
        this.currency = currency;
    }

    /**
     * The current lowest price an item is being sold for.
     *
     * @return the item's lowest sale price
     */
    public float getLowestPrice() {
        return lowestPrice;
    }

    /**
     * Updates the current lowest sale price for the item.
     *
     * @param price the new lowest sale price for the item
     */
    public void setLowestPrice(float price) {
        this.lowestPrice = price;
    }

    /**
     * The amount of an item currently being traded.
     *
     * @return the current amount of the item in the market
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Updates the current amount of the item currently being sold in the market.
     *
     * @param volume the new amount of the item being sold
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * The current median price an item is being sold for.
     *
     * @return the item's median sale price
     */
    public float getMedianPrice() {
        return medianPrice;
    }

    /**
     * Updates the current median sale price for the item.
     *
     * @param price the new median sale price for the item
     */
    public void setMedianPrice(float price) {
        this.medianPrice = price;
    }
}
