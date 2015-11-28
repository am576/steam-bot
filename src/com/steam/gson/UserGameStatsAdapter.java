package com.steam.gson;

import com.steam.UserGameStats;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * UserUserGameStatsAdapter
 * <p>
 * An object which reads JSON into a new UserUserGameStats object and also writes a Java object to JSON.
 *
 * @author Ian
 * @version 1.0
 */
public class UserGameStatsAdapter extends TypeAdapter<UserGameStats> {
    /**
     * Writes the current state of a UserGameStats to JSON.
     *
     * @param jsonWriter the writer to be used to output JSON
     * @param gameStats  the object to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, UserGameStats gameStats) throws IOException {

    }

    /**
     * Reads JSON into a new UserGameStats object.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the UserGameStats object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the UserGameStats object
     */
    @Override
    public UserGameStats read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        jsonReader.nextName();
        long id = jsonReader.nextLong();
        jsonReader.nextName();
        String gameName = jsonReader.nextString();
        jsonReader.nextName();
        jsonReader.beginArray();
        Map<String, Long> stats = new HashMap<>();
        while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject();
            jsonReader.nextName();
            String key = jsonReader.nextString();
            jsonReader.nextName();
            long value = jsonReader.nextLong();
            stats.put(key, value);
            jsonReader.endObject();
        }
        jsonReader.endArray();
        jsonReader.nextName();
        jsonReader.beginArray();
        Map<String, Boolean> achievements = new HashMap<>();
        while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject();
            jsonReader.nextName();
            String key = jsonReader.nextString();
            jsonReader.nextName();
            boolean value = jsonReader.nextInt() == 1;
            achievements.put(key, value);
            jsonReader.endObject();
        }
        jsonReader.endArray();
        jsonReader.endObject();
        jsonReader.endObject();
        return new UserGameStats(id, gameName, stats, achievements);
    }
}
