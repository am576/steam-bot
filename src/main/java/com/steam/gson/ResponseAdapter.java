package com.steam.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * ResponseAdapter
 * <p>
 * An object which reads JSON into a new Response object and also writes a Java object to JSON.
 *
 * @author Ian
 * @version 1.0
 */
public class ResponseAdapter extends TypeAdapter<Boolean> {
    /**
     * Writes the current state of a response code to JSON.
     *
     * @param jsonWriter the writer to be used to output JSON
     * @param response   the object to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, Boolean response) throws IOException {

    }

    /**
     * Reads JSON into a new response code output.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the Response object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the Response object
     */
    @Override
    public Boolean read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        //TODO:Figure out if there ever is a response message
        jsonReader.endObject();
        jsonReader.endObject();
        return true;
    }
}
