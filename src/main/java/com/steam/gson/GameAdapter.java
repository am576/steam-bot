package com.steam.gson;

import com.steam.Game;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * GameAdapter
 * <p>
 * An object which reads JSON into a new Game object and also writes a Java object to JSON.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class GameAdapter extends TypeAdapter<Game[]> {
    /**
     * Writes the current state of Games to JSON.
     *
     * @param jsonWriter the writer to be used to output JSON
     * @param games      the objects to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, Game[] games) throws IOException {

    }

    /**
     * Reads JSON into new Game objects.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the Game object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the Game object
     */
    @Override
    public Game[] read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        jsonReader.nextName();
        int count = jsonReader.nextInt();
        Game[] games = new Game[count];
        if (count != 0) {
            jsonReader.nextName();
            jsonReader.beginArray();
            int i = 0;
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
                    }
                    items.put(key, value);
                }
                Object idLookup = items.get("appid");
                Object nameLookup = items.get("name");
                Object playtime2WeeksLookup = items.get("playtime_2weeks");
                Object playtimeForeverLookup = items.get("playtime_forever");
                Object iconUrlLookup = items.get("img_icon_url");
                Object logoUrlLookup = items.get("img_logo_url");
                Object statsLookup = items.get("has_community_visible_stats");
                games[i++] = new Game(idLookup == null ? -1 : (Long) idLookup, nameLookup == null ? null : (String) nameLookup, playtime2WeeksLookup == null ? -1 : (Long) playtime2WeeksLookup, playtimeForeverLookup == null ? -1 : (Long) playtimeForeverLookup, iconUrlLookup == null ? null : (String) iconUrlLookup, logoUrlLookup == null ? null : (String) logoUrlLookup, statsLookup != null && (Boolean) statsLookup);
                jsonReader.endObject();
            }
            jsonReader.endArray();
        }
        jsonReader.endObject();
        jsonReader.endObject();
        return games;
    }
}
