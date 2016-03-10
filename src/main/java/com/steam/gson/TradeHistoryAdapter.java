package com.steam.gson;

import com.steam.trade.Item;
import com.steam.trade.ItemDescription;
import com.steam.trade.Offer;
import com.steam.trade.TradeHistory;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TradeHistoryAdapter
 * <p>
 * An object which reads JSON into a new TradeHistory object and also writes a Java object to JSON.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class TradeHistoryAdapter extends TypeAdapter<TradeHistory> {
    /**
     * Writes the current state of a TradeHistory to JSON.
     *
     * @param jsonWriter the writer to be used to output JSON
     * @param gameStats  the object to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, TradeHistory gameStats) throws IOException {

    }

    /**
     * Reads JSON into a new TradeHistory object.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the TradeHistory object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the TradeHistory object
     */
    @Override
    public TradeHistory read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        List<Offer> sentOffers = new ArrayList<>();
        List<Offer> receivedOffers = new ArrayList<>();
        List<ItemDescription> descriptions = new ArrayList<>();
        Map<Integer, List<Item>> itemsById = new HashMap<>();
        while (jsonReader.peek() == JsonToken.NAME) {
            String category = jsonReader.nextName();
            jsonReader.beginArray();
            switch (category) {
                case "trade_offers_sent":
                    while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
                        jsonReader.beginObject();
                        Map<String, Object> items = new HashMap<>();
                        while (jsonReader.peek() == JsonToken.NAME) {
                            String key = jsonReader.nextName();
                            Object value = null;
                            JsonToken token = jsonReader.peek();
                            switch (token) {
                                case BOOLEAN:
                                    value = jsonReader.nextBoolean();
                                    break;
                                case NUMBER:
                                    value = jsonReader.nextLong();
                                    break;
                                case STRING:
                                    value = jsonReader.nextString();
                                    break;
                                case BEGIN_ARRAY:
                                    jsonReader.beginArray();
                                    List<Item> i = new ArrayList<>();
                                    while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
                                        jsonReader.beginObject();
                                        jsonReader.nextName();
                                        int gameId = Integer.parseInt(jsonReader.nextString());
                                        jsonReader.nextName();
                                        int contextId = Integer.parseInt(jsonReader.nextString());
                                        jsonReader.nextName();
                                        long assetId = Long.parseLong(jsonReader.nextString());
                                        jsonReader.nextName();
                                        int classId = Integer.parseInt(jsonReader.nextString());
                                        jsonReader.nextName();
                                        int instanceId = Integer.parseInt(jsonReader.nextString());
                                        jsonReader.nextName();
                                        int amount = Integer.parseInt(jsonReader.nextString());
                                        jsonReader.nextName();
                                        boolean missing = jsonReader.nextBoolean();
                                        Item item = new Item(gameId, contextId, assetId, -1, classId, instanceId, amount, missing, -1, null);
                                        List<Item> itemList = itemsById.get(classId);
                                        if (itemList == null) {
                                            itemList = new ArrayList<>();
                                            itemsById.put(classId, itemList);
                                        }
                                        itemList.add(item);
                                        i.add(item);
                                        value = i.toArray(new Item[i.size()]);
                                        jsonReader.endObject();
                                    }
                                    jsonReader.endArray();
                                    break;
                            }
                            items.put(key, value);
                        }
                        Object idLookup = items.get("tradeofferid");
                        Object partnerIdLookup = items.get("accountid_other");
                        Object messageLookup = items.get("message");
                        Object expirationLookup = items.get("expiration_time");
                        Object stateLookup = items.get("trade_offer_state");
                        Object itemsToGiveLookup = items.get("items_to_give");
                        Object itemsToReceiveLookup = items.get("items_to_receive");
                        Object ownerLookup = items.get("is_our_offer");
                        Object timeCreatedLookup = items.get("time_created");
                        Object timeUpdatedLookup = items.get("time_updated");
                        Object realTimeTradeLookup = items.get("from_real_time_trade");
                        sentOffers.add(new Offer(idLookup == null ? -1 : Integer.parseInt((String) idLookup),
                                partnerIdLookup == null ? -1 : (Long) partnerIdLookup,
                                messageLookup == null ? null : (String) messageLookup,
                                expirationLookup == null ? -1 : (Long) expirationLookup,
                                stateLookup == null ? -1 : ((Long) stateLookup).intValue(),
                                itemsToGiveLookup == null ? null : (Item[]) itemsToGiveLookup,
                                itemsToReceiveLookup == null ? null : (Item[]) itemsToReceiveLookup,
                                ownerLookup != null && (Boolean) ownerLookup,
                                timeCreatedLookup == null ? -1 : (Long) timeCreatedLookup,
                                timeUpdatedLookup == null ? -1 : (Long) timeCreatedLookup,
                                realTimeTradeLookup != null && (Boolean) realTimeTradeLookup));
                        jsonReader.endObject();
                    }
                    break;
                case "trade_offers_received":
                    while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
                        jsonReader.beginObject();
                        Map<String, Object> items = new HashMap<>();
                        while (jsonReader.peek() == JsonToken.NAME) {
                            String key = jsonReader.nextName();
                            Object value = null;
                            JsonToken token = jsonReader.peek();
                            switch (token) {
                                case BOOLEAN:
                                    value = jsonReader.nextBoolean();
                                    break;
                                case NUMBER:
                                    value = jsonReader.nextLong();
                                    break;
                                case STRING:
                                    value = jsonReader.nextString();
                                    break;
                                case BEGIN_ARRAY:
                                    jsonReader.beginArray();
                                    List<Item> i = new ArrayList<>();
                                    while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
                                        jsonReader.beginObject();
                                        jsonReader.nextName();
                                        int gameId = Integer.parseInt(jsonReader.nextString());
                                        jsonReader.nextName();
                                        int contextId = Integer.parseInt(jsonReader.nextString());
                                        jsonReader.nextName();
                                        long assetId = Long.parseLong(jsonReader.nextString());
                                        jsonReader.nextName();
                                        int classId = Integer.parseInt(jsonReader.nextString());
                                        jsonReader.nextName();
                                        int instanceId = Integer.parseInt(jsonReader.nextString());
                                        jsonReader.nextName();
                                        int amount = Integer.parseInt(jsonReader.nextString());
                                        jsonReader.nextName();
                                        boolean missing = jsonReader.nextBoolean();
                                        Item item = new Item(gameId, contextId, assetId, -1, classId, instanceId, amount, missing, -1, null);
                                        List<Item> itemList = itemsById.get(classId);
                                        if (itemList == null) {
                                            itemList = new ArrayList<>();
                                            itemsById.put(classId, itemList);
                                        }
                                        itemList.add(item);
                                        i.add(item);
                                        value = i.toArray(new Item[i.size()]);
                                        jsonReader.endObject();
                                    }
                                    jsonReader.endArray();
                                    break;
                            }
                            items.put(key, value);
                        }
                        Object idLookup = items.get("tradeofferid");
                        Object partnerIdLookup = items.get("accountid_other");
                        Object messageLookup = items.get("message");
                        Object expirationLookup = items.get("expiration_time");
                        Object stateLookup = items.get("trade_offer_state");
                        Object itemsToGiveLookup = items.get("items_to_give");
                        Object itemsToReceiveLookup = items.get("items_to_receive");
                        Object ownerLookup = items.get("is_our_offer");
                        Object timeCreatedLookup = items.get("time_created");
                        Object timeUpdatedLookup = items.get("time_updated");
                        Object realTimeTradeLookup = items.get("from_real_time_trade");
                        receivedOffers.add(new Offer(idLookup == null ? -1 : Integer.parseInt((String) idLookup),
                                partnerIdLookup == null ? -1 : (Long) partnerIdLookup,
                                messageLookup == null ? null : (String) messageLookup,
                                expirationLookup == null ? -1 : (Long) expirationLookup,
                                stateLookup == null ? -1 : ((Long) stateLookup).intValue(),
                                itemsToGiveLookup == null ? null : (Item[]) itemsToGiveLookup,
                                itemsToReceiveLookup == null ? null : (Item[]) itemsToReceiveLookup,
                                ownerLookup != null && (Boolean) ownerLookup,
                                timeCreatedLookup == null ? -1 : (Long) timeCreatedLookup,
                                timeUpdatedLookup == null ? -1 : (Long) timeCreatedLookup,
                                realTimeTradeLookup != null && (Boolean) realTimeTradeLookup));
                        jsonReader.endObject();
                    }
                    break;
                case "descriptions":
                    while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
                        jsonReader.beginObject();
                        Map<String, Object> items = new HashMap<>();
                        while (jsonReader.peek() == JsonToken.NAME) {
                            String key = jsonReader.nextName();
                            Object value = null;
                            JsonToken token = jsonReader.peek();
                            switch (token) {
                                case BOOLEAN:
                                    value = jsonReader.nextBoolean();
                                    break;
                                case NUMBER:
                                    value = jsonReader.nextLong();
                                    break;
                                case STRING:
                                    value = jsonReader.nextString();
                                    break;
                                case BEGIN_ARRAY:
                                    jsonReader.beginArray();
                                    List<Map<String, String>> descriptionList = new ArrayList<>();
                                    while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
                                        jsonReader.beginObject();
                                        Map<String, String> d = new HashMap<>();
                                        while (jsonReader.peek() == JsonToken.NAME)
                                            d.put(jsonReader.nextName(), jsonReader.nextString());
                                        descriptionList.add(d);
                                        jsonReader.endObject();
                                    }
                                    value = descriptionList.toArray(new HashMap[descriptionList.size()]);
                                    jsonReader.endArray();
                                    break;
                            }
                            items.put(key, value);
                        }
                        Object gameIdLookup = items.get("appid");
                        Object classIdLookup = items.get("classid");
                        Object instanceIdLookup = items.get("instanceid");
                        Object currencyLookup = items.get("currency");
                        Object backgroundColorLookup = items.get("background_color");
                        Object iconUrlLookup = items.get("icon_url");
                        Object iconUrlLargeLookup = items.get("icon_url_large");
                        Object descriptionLookup = items.get("descriptions");
                        Object tradableLookup = items.get("tradable");
                        Object ownerActionsLookup = items.get("owner_actions");
                        Object nameLookup = items.get("name");
                        Object nameColorLookup = items.get("name_color");
                        Object typeLookup = items.get("type");
                        Object marketNameLookup = items.get("market_name");
                        Object marketHashNameLookup = items.get("market_hash_name");
                        Object commodityLookup = items.get("commodity");
                        Object marketTradableRestrictionsLookup = items.get("market_tradable_restriction");
                        Object marketMarketableRestrictionsLookup = items.get("market_marketable_restriction");
                        ItemDescription description = new ItemDescription(gameIdLookup == null ? -1 : (Long) gameIdLookup,
                                classIdLookup == null ? -1 : Integer.parseInt((String) classIdLookup),
                                instanceIdLookup == null ? -1 : Integer.parseInt((String) instanceIdLookup),
                                currencyLookup != null && (Boolean) currencyLookup,
                                backgroundColorLookup == null ? null : (String) backgroundColorLookup,
                                iconUrlLookup == null || "".equals(iconUrlLookup) ? null : "http://steamcommunity-a.akamaihd.net/economy/image/" + iconUrlLookup,
                                iconUrlLargeLookup == null || "".equals(iconUrlLargeLookup) ? null : "http://steamcommunity-a.akamaihd.net/economy/image/" + iconUrlLargeLookup,
                                descriptionLookup == null ? null : (Map<String, String>[]) descriptionLookup,
                                tradableLookup != null && (Boolean) tradableLookup,
                                ownerActionsLookup == null ? null : (Map<String, String>[]) ownerActionsLookup,
                                null,
                                null,
                                nameLookup == null ? null : (String) nameLookup,
                                nameColorLookup == null ? null : (String) nameColorLookup,
                                typeLookup == null ? null : (String) typeLookup,
                                marketNameLookup == null ? null : (String) marketNameLookup,
                                marketHashNameLookup == null ? null : (String) marketHashNameLookup,
                                commodityLookup != null && (Boolean) commodityLookup,
                                marketTradableRestrictionsLookup == null ? 0 : ((Long) marketTradableRestrictionsLookup).intValue(),
                                marketMarketableRestrictionsLookup == null ? 0 : ((Long) marketMarketableRestrictionsLookup).intValue());
                        descriptions.add(description);
                        List<Item> itemList = itemsById.get(description.getClassId());
                        if (itemList != null) {
                            for (Item item : itemList)
                                item.setDescription(description);
                        }
                        jsonReader.endObject();
                    }
                    break;
            }
            jsonReader.endArray();
        }
        jsonReader.endObject();
        jsonReader.endObject();
        return new TradeHistory(sentOffers.toArray(new Offer[sentOffers.size()]), receivedOffers.toArray(new Offer[receivedOffers.size()]), descriptions.toArray(new ItemDescription[descriptions.size()]));
    }
}
