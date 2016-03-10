package com.iancaffey.steam.gson;

import com.iancaffey.steam.BanHistory;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * BanHistoryAdapter
 * <p>
 * An object which reads JSON into a new BanHistory object and also writes a Java object to JSON.
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class BanHistoryAdapter extends TypeAdapter<BanHistory[]> {
    /**
     * Writes the current state of BanHistories to JSON.
     *
     * @param jsonWriter the writer to be used to output JSON
     * @param histories  the objects to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, BanHistory[] histories) throws IOException {

    }

    /**
     * Reads JSON into new BanHistory objects.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the BanHistory object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the BanHistory object
     */
    @Override
    public BanHistory[] read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginArray();
        List<BanHistory> histories = new ArrayList<>();
        while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject();
            jsonReader.nextName();
            long id = jsonReader.nextLong();
            jsonReader.nextName();
            boolean communityBanned = jsonReader.nextBoolean();
            jsonReader.nextName();
            boolean vacBanned = jsonReader.nextBoolean();
            jsonReader.nextName();
            int vacBanCount = jsonReader.nextInt();
            jsonReader.nextName();
            int daysSinceLastBan = jsonReader.nextInt();
            jsonReader.nextName();
            int gameBanCount = jsonReader.nextInt();
            jsonReader.nextName();
            String economyStatus = jsonReader.nextString();
            histories.add(new BanHistory(id, communityBanned, vacBanned, vacBanCount, daysSinceLastBan, gameBanCount, economyStatus));
            jsonReader.endObject();
        }
        jsonReader.endArray();
        jsonReader.endObject();
        return histories.toArray(new BanHistory[histories.size()]);
    }
}
