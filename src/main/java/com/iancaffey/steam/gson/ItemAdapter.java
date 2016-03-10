package com.iancaffey.steam.gson;

import com.iancaffey.steam.trade.Item;
import com.iancaffey.steam.trade.ItemDescription;
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
 * ItemAdapter
 * <p>
 * An object which reads JSON into a new Item object and also writes a Java object to JSON.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class ItemAdapter extends TypeAdapter<Item[]> {
    /**
     * Writes the current state of Items to JSON.
     *
     * @param jsonWriter the writer to be used to output JSON
     * @param items      the objects to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, Item[] items) throws IOException {

    }

    /**
     * Reads JSON into new Item objects.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the Item object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the Item object
     */
    @Override
    public Item[] read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        boolean valid = jsonReader.nextBoolean();
        if (!valid) {
            jsonReader.close();
            return null;
        }
        jsonReader.nextName();
        jsonReader.beginObject();
        List<Item> items = new ArrayList<>();
        while (jsonReader.peek() == JsonToken.NAME) {
            jsonReader.nextName();
            jsonReader.beginObject();
            jsonReader.nextName();
            long id = Long.parseLong(jsonReader.nextString());
            jsonReader.nextName();
            int classId = Integer.parseInt(jsonReader.nextString());
            jsonReader.nextName();
            int instanceId = Integer.parseInt(jsonReader.nextString());
            jsonReader.nextName();
            int amount = Integer.parseInt(jsonReader.nextString());
            jsonReader.nextName();
            int slot = Integer.parseInt(jsonReader.nextString());
            items.add(new Item(-1, -1, id, -1, classId, instanceId, amount, slot != 0, slot, null));
            jsonReader.endObject();
        }
        jsonReader.endObject();
        jsonReader.nextName();
        jsonReader.beginArray();
        while (jsonReader.peek() != JsonToken.END_ARRAY)
            jsonReader.skipValue();
        jsonReader.endArray();
        jsonReader.nextName();
        jsonReader.beginObject();
        while (jsonReader.peek() == JsonToken.NAME) {
            jsonReader.nextName();
            jsonReader.beginObject();
            jsonReader.nextName();
            int gameId = Integer.parseInt(jsonReader.nextString());
            jsonReader.nextName();
            int classId = Integer.parseInt(jsonReader.nextString());
            jsonReader.nextName();
            int instanceId = Integer.parseInt(jsonReader.nextString());
            jsonReader.nextName();
            String iconUrl = jsonReader.nextString();
            String dragUrlLink = jsonReader.nextName();
            String iconUrlLarge = null;
            if ("icon_url_large".equals(dragUrlLink)) {
                iconUrlLarge = jsonReader.nextString();
                jsonReader.nextName();
            }
            String iconDragUrl = jsonReader.nextString();
            jsonReader.nextName();
            String name = jsonReader.nextString();
            jsonReader.nextName();
            String marketHashName = jsonReader.nextString();
            jsonReader.nextName();
            String marketName = jsonReader.nextString();
            jsonReader.nextName();
            String nameColor = jsonReader.nextString();
            jsonReader.nextName();
            String backgroundColor = jsonReader.nextString();
            jsonReader.nextName();
            String type = jsonReader.nextString();
            String n = jsonReader.nextName();
            boolean tradable = jsonReader.nextInt() == 1;
            jsonReader.nextName();
            int marketMarketabilityRestrictions = jsonReader.nextInt();
            jsonReader.nextName();
            boolean commodity = jsonReader.nextInt() == 1;
            jsonReader.nextName();
            int marketTradabilityRestrictions = Integer.parseInt(jsonReader.nextString());
            String descOrCache = jsonReader.nextName();
            if ("cache_expiration".equals(descOrCache)) {
                jsonReader.nextString();
                jsonReader.nextName();
            }
            jsonReader.beginArray();
            List<Map<String, String>> descriptions = new ArrayList<>();
            while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
                jsonReader.beginObject();
                Map<String, String> d = new HashMap<>();
                while (jsonReader.peek() == JsonToken.NAME) {
                    String key = jsonReader.nextName();
                    //TODO:See if app_data is useful in pulling item descriptions
                    if (jsonReader.peek() != JsonToken.STRING) {
                        jsonReader.skipValue();
                        continue;
                    }
                    d.put(key, jsonReader.nextString());
                }
                descriptions.add(d);
                jsonReader.endObject();
            }
            jsonReader.endArray();
            Map<String, String> actions = null;
            Map<String, String> marketActions = null;
            if ("actions".equals(jsonReader.nextName())) {
                jsonReader.beginArray();
                jsonReader.beginObject();
                actions = new HashMap<>();
                while (jsonReader.peek() == JsonToken.NAME)
                    actions.put(jsonReader.nextName(), jsonReader.nextString());
                jsonReader.endObject();
                jsonReader.endArray();
                jsonReader.nextName();
                jsonReader.beginArray();
                jsonReader.beginObject();
                marketActions = new HashMap<>();
                while (jsonReader.peek() == JsonToken.NAME)
                    marketActions.put(jsonReader.nextName(), jsonReader.nextString());
                jsonReader.endObject();
                jsonReader.endArray();
                jsonReader.nextName();
            }
            jsonReader.beginArray();
            while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
                jsonReader.beginObject();
                while (jsonReader.peek() != JsonToken.END_OBJECT)
                    jsonReader.skipValue();
                jsonReader.endObject();
            }
            jsonReader.endArray();
            ItemDescription description = new ItemDescription(gameId, classId, instanceId, false, backgroundColor, iconUrl == null ? null : "http://steamcommunity-a.akamaihd.net/economy/image/" + iconUrl, iconUrlLarge == null ? null : "http://steamcommunity-a.akamaihd.net/economy/image/" + iconUrlLarge, descriptions.toArray(new HashMap[descriptions.size()]), tradable, null, actions, marketActions, name, nameColor, type, marketName, marketHashName, commodity, marketTradabilityRestrictions, marketMarketabilityRestrictions);
            for (Item item : items)
                if (item.getClassId() == description.getClassId())
                    item.setDescription(description);
            jsonReader.endObject();
        }
        jsonReader.endObject();
        while (jsonReader.peek() != JsonToken.END_OBJECT)
            jsonReader.skipValue();
        jsonReader.endObject();
        return items.toArray(new Item[items.size()]);
    }
}
