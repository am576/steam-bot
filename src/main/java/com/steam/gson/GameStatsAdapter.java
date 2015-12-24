package com.steam.gson;

import com.steam.GameStats;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * GameStatsAdapter
 * <p>
 * An object which reads JSON into a new GameStats object and also writes a Java object to JSON.
 *
 * @author Ian
 * @version 1.0
 */
public class GameStatsAdapter extends TypeAdapter<GameStats> {
    /**
     * Writes the current state of a GameStats to JSON.
     *
     * @param jsonWriter the writer to be used to output JSON
     * @param gameStats  the object to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, GameStats gameStats) throws IOException {

    }

    /**
     * Reads JSON into a new GameStats object.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the GameStats object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the GameStats object
     */
    @Override
    public GameStats read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        Map<String, Long> stats = new HashMap<>();
        while (jsonReader.peek() == JsonToken.NAME) {
            String achievement = jsonReader.nextName();
            jsonReader.beginObject();
            jsonReader.nextName();
            long number = jsonReader.nextLong();
            stats.put(achievement, number);
            jsonReader.endObject();
        }
        jsonReader.endObject();
        jsonReader.nextName();
        jsonReader.nextInt();
        jsonReader.endObject();
        jsonReader.endObject();
        return new GameStats(stats);
    }
}
