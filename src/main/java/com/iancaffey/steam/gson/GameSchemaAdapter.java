package com.iancaffey.steam.gson;

import com.iancaffey.steam.GameSchema;
import com.iancaffey.steam.GameStat;
import com.iancaffey.steam.GameAchievement;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * GameSchemaAdapter
 * <p>
 * An object which reads JSON into a new GameSchema object and also writes a Java object to JSON.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class GameSchemaAdapter extends TypeAdapter<GameSchema> {
    /**
     * Writes the current state of a GameSchema to JSON.
     *
     * @param jsonWriter the writer to be used to output JSON
     * @param gameSchema the object to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, GameSchema gameSchema) throws IOException {

    }

    /**
     * Reads JSON into a new GameSchema object.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the GameSchema object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the GameSchema object
     */
    @Override
    public GameSchema read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        jsonReader.nextName();
        String name = jsonReader.nextString();
        jsonReader.nextName();
        int version = jsonReader.nextInt();
        jsonReader.nextName();
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginArray();
        List<GameStat> stats = new ArrayList<>();
        while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject();
            jsonReader.nextName();
            String statName = jsonReader.nextString();
            jsonReader.nextName();
            long defaultValue = jsonReader.nextLong();
            jsonReader.nextName();
            String displayName = jsonReader.nextString();
            stats.add(new GameStat(statName, defaultValue, displayName));
            jsonReader.endObject();
        }
        jsonReader.endArray();
        jsonReader.nextName();
        jsonReader.beginArray();
        List<GameAchievement> achievements = new ArrayList<>();
        while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject();
            jsonReader.nextName();
            String achievementName = jsonReader.nextString();
            jsonReader.nextName();
            long defaultValue = jsonReader.nextLong();
            jsonReader.nextName();
            String displayName = jsonReader.nextString();
            jsonReader.nextName();
            boolean hidden = jsonReader.nextInt() == 1;
            jsonReader.nextName();
            String description = jsonReader.nextString();
            jsonReader.nextName();
            String iconUrl = jsonReader.nextString();
            jsonReader.nextName();
            String iconGreyUrl = jsonReader.nextString();
            achievements.add(new GameAchievement(achievementName, defaultValue, displayName, hidden, description, iconUrl, iconGreyUrl));
            jsonReader.endObject();
        }
        jsonReader.endArray();
        jsonReader.endObject();
        jsonReader.endObject();
        jsonReader.endObject();
        return new GameSchema(name, version, achievements.toArray(new GameAchievement[achievements.size()]), stats.toArray(new GameStat[stats.size()]));
    }
}
