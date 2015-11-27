package iitc.steam.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * SharedGameOwnerAdapter
 * <p>
 * An object which reads JSON into a new SharedGameOwner object and also writes a Java object to JSON.
 *
 * @author Ian
 * @version 1.0
 */
public class SharedGameOwnerAdapter extends TypeAdapter<Long> {
    /**
     * Writes the current state of a SharedGameOwner to JSON.
     *
     * @param jsonWriter the writer to be used to output JSON
     * @param owner      the object to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, Long owner) throws IOException {

    }

    /**
     * Reads JSON into a new Long object.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the Long object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the Long object
     */
    @Override
    public Long read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        jsonReader.nextName();
        String lender = jsonReader.nextString();
        jsonReader.endObject();
        jsonReader.endObject();
        if (lender == null)
            return null;
        long id = Long.parseLong(lender.replaceAll("[^0-9]", ""));
        return id == 0 ? -1 : id;
    }
}
