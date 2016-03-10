package com.steam.gson;

import com.steam.trade.PriceHistory;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * PriceHistoryAdapter
 * <p>
 * An object which reads JSON into a new PriceHistory object and also writes a Java object to JSON.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class PriceHistoryAdapter extends TypeAdapter<PriceHistory> {
    /**
     * Writes the current state of PriceHistory to JSON.
     *
     * @param jsonWriter the writer to be used to output JSON
     * @param item       the object to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, PriceHistory item) throws IOException {

    }

    /**
     * Reads JSON into a new PriceHistory object.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the PriceHistory object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the PriceHistory object
     */
    @Override
    public PriceHistory read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        boolean success = jsonReader.nextBoolean();
        if (!success) {
            jsonReader.close();
            return null;
        }
        jsonReader.nextName();
        String lowestPriceString = jsonReader.nextString();
        jsonReader.nextName();
        int volume = Integer.parseInt(jsonReader.nextString().replaceAll(",", ""));
        jsonReader.nextName();
        String medianPriceString = jsonReader.nextString();
        jsonReader.endObject();
        return new PriceHistory(lowestPriceString, volume, medianPriceString);
    }
}
