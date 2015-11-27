package iitc.steam;

/**
 * OfferType
 * <p>
 * An enum representing the types of offers to return when retrieving trade histories.
 *
 * @author Ian
 * @version 1.0
 */
public enum OfferType {
    /**
     * Trade history queries retrieve only the sent offers.
     */
    SENT,
    /**
     * Trade history queries retrieve only the received offers.
     */
    RECEIVED,
    /**
     * Trade history queries retrieve both sent and received offers.
     */
    ALL
}
