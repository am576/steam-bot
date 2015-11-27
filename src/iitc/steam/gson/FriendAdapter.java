package iitc.steam.gson;

import iitc.steam.Friend;
import iitc.steam.Relationship;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FriendAdapter
 * <p>
 * An object which reads JSON into a new Friend object and also writes a Java object to JSON.
 *
 * @author Ian
 * @version 1.0
 */
public class FriendAdapter extends TypeAdapter<Friend[]> {
    /**
     * Writes the current state of Friends to JSON.
     *
     * @param jsonWriter the writer to be used to output JSON
     * @param friends    the objects to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, Friend[] friends) throws IOException {

    }

    /**
     * Reads JSON into new Friend objects.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the Friend object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the Friend object
     */
    @Override
    public Friend[] read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginArray();
        List<Friend> friends = new ArrayList<>();
        while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject();
            jsonReader.nextName();
            long id = jsonReader.nextLong();
            jsonReader.nextName();
            String relationship = jsonReader.nextString();
            jsonReader.nextName();
            long friendshipDate = jsonReader.nextLong();
            friends.add(new Friend(id, "friend".equals(relationship) ? Relationship.FRIEND : Relationship.ALL, friendshipDate));
            jsonReader.endObject();
        }
        jsonReader.endArray();
        jsonReader.endObject();
        jsonReader.endObject();
        return friends.toArray(new Friend[friends.size()]);
    }
}
