package com.iancaffey.steam.trade;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ItemDescription
 * <p>
 * An object representing information pertinent to displaying items.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class ItemDescription {
    private final long gameId;
    private final int classId;
    private final int instanceId;
    private final boolean currency;
    private final String backgroundColor;
    private final String iconUrl;
    private final String iconLargeUrl;
    private final Map<String, String>[] descriptions;
    private final Map<String, String>[] ownerActions;
    private final Map<String, String> actions;
    private final Map<String, String> marketActions;
    private final String name;
    private final String nameColor;
    private final String type;
    private final String marketName;
    private final String marketHashName;
    private boolean tradable;
    private boolean commodity;
    private int marketTradableRestriction;
    private int marketMarketableRestriction;

    /**
     * Creates an item description with the following characteristics.
     *
     * @param gameId                      the identifier for the game the item belongs to
     * @param classId                     the identifier of the class the item belongs to
     * @param instanceId                  the unique identifier for the item
     * @param currency                    whether or not the item is currency
     * @param backgroundColor             the background color of the item icon
     * @param iconUrl                     the location of the item icon
     * @param iconLargeUrl                the location of the large item icon
     * @param descriptions                the display descriptions
     * @param tradable                    whether or not the item is tradable
     * @param ownerActions                the actions associated with the item that the owner can make use of
     * @param actions                     the actions associated with the item while in inventory
     * @param marketActions               the actions associated with the item for everyone while in inventory
     * @param name                        the name of the item
     * @param nameColor                   the color of the name of the item
     * @param type                        the type of the item
     * @param marketName                  the market name of the item
     * @param marketHashName              the hashed market name of the item
     * @param commodity                   whether or not the item is a commodity
     * @param marketTradableRestriction   the level of market trade restriction
     * @param marketMarketableRestriction the level of market marketability restriction
     */
    public ItemDescription(long gameId, int classId, int instanceId, boolean currency, String backgroundColor, String iconUrl, String iconLargeUrl, Map<String, String>[] descriptions, boolean tradable, Map<String, String>[] ownerActions, Map<String, String> actions, Map<String, String> marketActions, String name, String nameColor, String type, String marketName, String marketHashName, boolean commodity, int marketTradableRestriction, int marketMarketableRestriction) {
        this.gameId = gameId;
        this.classId = classId;
        this.instanceId = instanceId;
        this.currency = currency;
        this.backgroundColor = backgroundColor;
        this.iconUrl = iconUrl;
        this.iconLargeUrl = iconLargeUrl;
        this.descriptions = descriptions;
        this.tradable = tradable;
        this.ownerActions = ownerActions;
        this.actions = actions;
        this.marketActions = marketActions;
        this.name = name;
        this.nameColor = nameColor;
        this.type = type;
        this.marketName = marketName;
        this.marketHashName = marketHashName;
        this.commodity = commodity;
        this.marketTradableRestriction = marketTradableRestriction;
        this.marketMarketableRestriction = marketMarketableRestriction;
    }

    /**
     * The identifier of the game or application the item belongs to.
     *
     * @return the item game identifier
     */
    public long getGameId() {
        return gameId;
    }

    /**
     * The identifier of the class the item belongs to.
     *
     * @return the item class identifier
     */
    public int getClassId() {
        return classId;
    }

    /**
     * The unique identifier for the item.
     *
     * @return the uniquer item identifier
     */
    public int getInstanceId() {
        return instanceId;
    }

    /**
     * Whether or not the item is currency.
     *
     * @return <code>true</code> if the item is currency, <code>false</code> otherwise
     */
    public boolean isCurrency() {
        return currency;
    }

    /**
     * The color of the background of the display icon of the item.
     *
     * @return the item icon display background color
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * The location of the icon of the item with a specified dimensions.
     * The icon maintains the original aspect ratio so the dimension are not guaranteed, just simply bounds.
     *
     * @param dimension the desired dimension of the icon
     * @return the custom item icon location
     */
    public String getIconUrl(Dimension dimension) {
        return dimension == null ? getIconUrl(0, 0) : getIconUrl(dimension.width, dimension.height);
    }

    /**
     * The location of the icon of the item with a specified height and width.
     * The icon maintains the original aspect ratio so the dimension are not guaranteed, just simply bounds.
     *
     * @param width  the desired width of the icon
     * @param height the desired height of the icon
     * @return the custom item icon location
     */
    public String getIconUrl(int width, int height) {
        String icon = getIconLargeUrl();
        if (icon == null || "".equals(icon))
            icon = getIconUrl();
        if (icon == null || "".equals(icon))
            return null;
        return width <= 0 || height <= 0 ? icon : String.format("%s/%sx%s", icon, width, height);
    }

    /**
     * The location of the icon of the item.
     *
     * @return the item icon location
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * The location of the large icon of the item.
     *
     * @return the large item icon location
     */
    public String getIconLargeUrl() {
        return iconLargeUrl;
    }

    /**
     * The display descriptions for the item.
     *
     * @return the item display descriptions
     */
    public Map<String, String>[] getDescriptions() {
        return descriptions == null ? null : Arrays.copyOf(descriptions, descriptions.length);
    }

    /**
     * Whether or not the item is tradable on the market.
     *
     * @return <code>true</code> if the item is tradable on the market, <code>false</code> otherwise
     */
    public boolean isTradable() {
        return tradable;
    }


    /**
     * The actions associated with the item that the owner can make use of.
     *
     * @return the item owner actions
     */
    public Map<String, String>[] getOwnerActions() {
        return ownerActions == null ? null : Arrays.copyOf(ownerActions, ownerActions.length);
    }

    /**
     * The actions associated with the item while in the inventory.
     *
     * @return the item owner actions
     */
    public Map<String, String> getActions() {
        return actions == null ? null : new HashMap<>(actions);
    }

    /**
     * The actions associated with the item for everyone while in the inventory.
     *
     * @return the item owner actions
     */
    public Map<String, String> getMarketActions() {
        return marketActions == null ? null : new HashMap<>(marketActions);
    }

    /**
     * The name of the item.
     *
     * @return the item name
     */
    public String getName() {
        return name;
    }

    /**
     * The color of the name of the item.
     *
     * @return the item name color
     */
    public String getNameColor() {
        return nameColor;
    }

    /**
     * The type or general name of the item.
     *
     * @return the item type/general name
     */
    public String getType() {
        return type;
    }

    /**
     * The general name for the item on the market.
     *
     * @return the general item market name
     */
    public String getMarketName() {
        return marketName;
    }

    /**
     * The hashed name for the item on the market.
     *
     * @return the hashed item market name
     */
    public String getMarketHashName() {
        return marketHashName;
    }

    /**
     * Whether or not the item is a commodity.
     *
     * @return <code>true</code> if an item is a commodity, <code>false</code> otherwise
     */
    public boolean isCommodity() {
        return commodity;
    }

    /**
     * The level of market trade restriction for the item.
     *
     * @return the item market trade restriction level
     */
    public int getMarketTradableRestriction() {
        return marketTradableRestriction;
    }

    /**
     * The level of market marketability restriction for the item.
     *
     * @return the item market marketability level
     */
    public int getMarketMarketableRestriction() {
        return marketMarketableRestriction;
    }
}
