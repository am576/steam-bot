package com.iancaffey.steam.gson;

import com.iancaffey.steam.trade.Item;
import com.iancaffey.steam.trade.ItemDescription;
import com.iancaffey.steam.trade.Offer;
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
 * OfferAdapter
 * <p>
 * An object which reads JSON into a new Offer object and also writes a Java object to JSON.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class OfferAdapter extends TypeAdapter<Offer> {
    /**
     * Writes the current state of a Offer to JSON.
     *
     * @param jsonWriter the writer to be used to output JSON
     * @param gameStats  the object to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, Offer gameStats) throws IOException {

    }

    /**
     * Reads JSON into a new Offer object.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the Offer object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the Offer object
     */
    @Override
    public Offer read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        Map<Integer, List<Item>> itemsById = new HashMap<>();
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
                        int assetId = Integer.parseInt(jsonReader.nextString());
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
        Offer offer = new Offer(idLookup == null ? -1 : Integer.parseInt((String) idLookup),
                partnerIdLookup == null ? -1 : (Long) partnerIdLookup,
                messageLookup == null ? null : (String) messageLookup,
                expirationLookup == null ? -1 : (Long) expirationLookup,
                stateLookup == null ? -1 : ((Long) stateLookup).intValue(),
                itemsToGiveLookup == null ? null : (Item[]) itemsToGiveLookup,
                itemsToReceiveLookup == null ? null : (Item[]) itemsToReceiveLookup,
                ownerLookup != null && (Boolean) ownerLookup,
                timeCreatedLookup == null ? -1 : (Long) timeCreatedLookup,
                timeUpdatedLookup == null ? -1 : (Long) timeCreatedLookup,
                realTimeTradeLookup != null && (Boolean) realTimeTradeLookup);
        jsonReader.endObject();
        jsonReader.nextName();
        jsonReader.beginArray();
        while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject();
            jsonReader.nextName();
            int gameId = jsonReader.nextInt();
            jsonReader.nextName();
            int classId = Integer.parseInt(jsonReader.nextString());
            jsonReader.nextName();
            int instanceId = Integer.parseInt(jsonReader.nextString());
            jsonReader.nextName();
            boolean currency = jsonReader.nextBoolean();
            jsonReader.nextName();
            String backgroundColor = jsonReader.nextString();
            jsonReader.nextName();
            String iconUrl = jsonReader.nextString();
            jsonReader.nextName();
            String iconLargeUrl = jsonReader.nextString();
            jsonReader.nextName();
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
            jsonReader.endArray();
            jsonReader.nextName();
            boolean tradable = jsonReader.nextBoolean();
            jsonReader.nextName();
            jsonReader.beginArray();
            List<Map<String, String>> actionList = new ArrayList<>();
            while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
                jsonReader.beginObject();
                Map<String, String> a = new HashMap<>();
                while (jsonReader.peek() == JsonToken.NAME)
                    a.put(jsonReader.nextName(), jsonReader.nextString());
                actionList.add(a);
                jsonReader.endObject();
            }
            jsonReader.endArray();
            jsonReader.nextName();
            String name = jsonReader.nextString();
            jsonReader.nextName();
            String nameColor = jsonReader.nextString();
            jsonReader.nextName();
            String type = jsonReader.nextString();
            jsonReader.nextName();
            String marketName = jsonReader.nextString();
            jsonReader.nextName();
            String marketHashName = jsonReader.nextString();
            jsonReader.nextName();
            boolean commodity = jsonReader.nextBoolean();
            jsonReader.nextName();
            int tradableRestrictions = jsonReader.nextInt();
            jsonReader.nextName();
            int marketableRestrictions = jsonReader.nextInt();
            ItemDescription description = new ItemDescription(gameId, classId, instanceId, currency, backgroundColor, iconUrl == null || "".equals(iconUrl) ? null : "http://steamcommunity-a.akamaihd.net/economy/image/" + iconUrl, iconLargeUrl == null || "".equals(iconLargeUrl) ? null : "http://steamcommunity-a.akamaihd.net/economy/image/" + iconLargeUrl, descriptionList.toArray(new HashMap[descriptionList.size()]), tradable, actionList.toArray(new HashMap[actionList.size()]), null, null, name, nameColor, type, marketName, marketHashName, commodity, tradableRestrictions, marketableRestrictions);
            List<Item> itemList = itemsById.get(description.getClassId());
            if (itemList != null) {
                for (Item item : itemList)
                    item.setDescription(description);
            }
            jsonReader.endObject();
        }
        jsonReader.endArray();
        jsonReader.endObject();
        jsonReader.endObject();
        return offer;
    }
}
