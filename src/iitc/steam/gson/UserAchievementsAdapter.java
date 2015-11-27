package iitc.steam.gson;

import iitc.steam.UserAchievements;
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
 * UserAchievementsAdapter
 * <p>
 * An object which reads JSON into a new UserAchievements object and also writes a Java object to JSON.
 *
 * @author Ian
 * @version 1.0
 */
public class UserAchievementsAdapter extends TypeAdapter<UserAchievements> {
    /**
     * Writes the current state of a UserAchievements to JSON.
     *
     * @param jsonWriter   the writer to be used to output JSON
     * @param achievements the object to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, UserAchievements achievements) throws IOException {

    }

    /**
     * Reads JSON into a new UserAchievements object.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the UserAchievements object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the UserAchievements object
     */
    @Override
    public UserAchievements read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        jsonReader.nextName();
        long id = jsonReader.nextLong();
        jsonReader.nextName();
        String gameName = jsonReader.nextString();
        jsonReader.nextName();
        jsonReader.beginArray();
        List<String> achievementNames = new ArrayList<>();
        Map<String, String> localizedNames = new HashMap<>();
        Map<String, String> localizedDescriptions = new HashMap<>();
        Map<String, Boolean> achieved = new HashMap<>();
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
            Object apiNameLookup = items.get("apiname");
            Object achievedLookup = items.get("achieved");
            Object nameLookup = items.get("name");
            Object descriptionLookup = items.get("description");
            if (apiNameLookup != null) {
                String apiName = (String) apiNameLookup;
                achievementNames.add(apiName);
                achieved.put(apiName, achievedLookup != null && (Long) achievedLookup == 1);
                if (nameLookup != null)
                    localizedNames.put(apiName, (String) nameLookup);
                if (descriptionLookup != null)
                    localizedDescriptions.put(apiName, (String) descriptionLookup);
            }
            jsonReader.endObject();
        }
        jsonReader.endArray();
        jsonReader.nextName();
        jsonReader.nextBoolean();
        jsonReader.endObject();
        jsonReader.endObject();
        return new UserAchievements(id, gameName, achievementNames.toArray(new String[achievementNames.size()]), localizedNames, localizedDescriptions, achieved);
    }
}
