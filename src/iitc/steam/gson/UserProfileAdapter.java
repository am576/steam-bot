package iitc.steam.gson;

import iitc.steam.UserProfile;
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
 * UserProfileAdapter
 * <p>
 * An object which reads JSON into a new UserProfile object and also writes a Java object to JSON.
 *
 * @author Ian
 * @version 1.0
 */
public class UserProfileAdapter extends TypeAdapter<UserProfile[]> {
    /**
     * Writes the current state of UserProfiles to JSON.
     *
     * @param jsonWriter   the writer to be used to output JSON
     * @param userProfiles the objects to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, UserProfile[] userProfiles) throws IOException {

    }

    /**
     * Reads JSON into new UserProfile objects.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the UserProfile object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the UserProfile object
     */
    @Override
    public UserProfile[] read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginArray();
        List<UserProfile> profiles = new ArrayList<>();
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
            Object idLookup = items.get("steamid");
            Object visibilityLookup = items.get("communityvisibilitystate");
            Object profileStateLookup = items.get("profilestate");
            Object nameLookup = items.get("personaname");
            Object lastLogoffLookup = items.get("lastlogoff");
            Object profileUrlLookup = items.get("profileurl");
            Object avatarLookup = items.get("avatar");
            Object avatarMediumLookup = items.get("avatarmedium");
            Object avatarFullLookup = items.get("avatarfull");
            Object stateLookup = items.get("personastate");
            Object realNameLookup = items.get("realname");
            Object primaryClanLookup = items.get("primaryclanid");
            Object timeCreatedLookup = items.get("timecreated");
            Object countryCodeLookup = items.get("loccountrycode");
            Object stateCodeLookup = items.get("locstatecode");
            Object cityCodeLookup = items.get("loccityid");
            profiles.add(new UserProfile(idLookup == null ? -1 : Long.parseLong((String) idLookup),
                    nameLookup == null ? null : (String) nameLookup,
                    profileUrlLookup == null ? null : (String) profileUrlLookup,
                    avatarLookup == null ? null : (String) avatarLookup,
                    avatarMediumLookup == null ? null : (String) avatarMediumLookup,
                    avatarFullLookup == null ? null : (String) avatarFullLookup,
                    stateLookup == null ? -1 : (Long) stateLookup,
                    visibilityLookup != null && (Long) visibilityLookup == 1,
                    profileStateLookup != null && (Long) profileStateLookup == 1,
                    lastLogoffLookup == null ? -1 : (Long) lastLogoffLookup,
                    realNameLookup == null ? null : (String) realNameLookup,
                    primaryClanLookup == null ? -1 : Long.parseLong((String) primaryClanLookup),
                    timeCreatedLookup == null ? -1 : (Long) timeCreatedLookup,
                    -1,
                    null,
                    null,
                    countryCodeLookup == null ? null : (String) countryCodeLookup,
                    stateCodeLookup == null ? null : (String) stateCodeLookup,
                    cityCodeLookup == null ? -1 : ((Long) cityCodeLookup).intValue()));
            jsonReader.endObject();
        }
        jsonReader.endArray();
        jsonReader.endObject();
        jsonReader.endObject();
        return profiles.toArray(new UserProfile[profiles.size()]);
    }
}
