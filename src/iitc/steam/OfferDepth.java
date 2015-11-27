package iitc.steam;

/**
 * OfferDepth
 * <p>
 * An object representing the depth to which return offers in trade history lookups.
 *
 * @author Ian
 * @version 1.0
 */
public enum OfferDepth {
    /**
     * Trade history queries will only retrieve active offers.
     */
    CURRENT,
    /**
     * Trade history queries will retrieve only the cached offer information.
     */
    HISTORICAL,
    /**
     * Trade history queries will retrieve both active and cached offer information.
     */
    ALL
}
