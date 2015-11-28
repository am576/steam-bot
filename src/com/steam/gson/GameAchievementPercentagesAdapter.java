package com.steam.gson;

import com.steam.GameAchievementPercentages;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * GameAchievementPercentagesAdapter
 * <p>
 * An object which reads JSON into a new GameAchievementPercentages object and also writes a Java object to JSON.
 *
 * @author Ian
 * @version 1.0
 */
public class GameAchievementPercentagesAdapter extends TypeAdapter<GameAchievementPercentages> {
    /**
     * Writes the current state of a GameAchievementPercentages to JSON.
     *
     * @param jsonWriter  the writer to be used to output JSON
     * @param percentages the object to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, GameAchievementPercentages percentages) throws IOException {

    }

    /**
     * Reads JSON into a new GameAchievementPercentages object.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the GameAchievementPercentages object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the GameAchievementPercentages object
     */
    @Override
    public GameAchievementPercentages read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginArray();
        Map<String, Double> percentages = new HashMap<>();
        while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject();
            jsonReader.nextName();
            String name = jsonReader.nextString();
            jsonReader.nextName();
            double percentage = jsonReader.nextDouble();
            percentages.put(name, percentage);
            jsonReader.endObject();
        }
        jsonReader.endArray();
        jsonReader.endObject();
        jsonReader.endObject();
        return new GameAchievementPercentages(percentages);
    }
}
