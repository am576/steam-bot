package com.steam.gson;

import com.steam.GameNews;
import com.steam.NewsItem;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * GameNewsAdapter
 * <p>
 * An object which reads JSON into a new GameNews object and also writes a Java object to JSON.
 *
 * @author Ian
 * @version 1.0
 */
public class GameNewsAdapter extends TypeAdapter<GameNews> {
    /**
     * Writes the current state of a GameNews to JSON.
     *
     * @param jsonWriter the writer to be used to output JSON
     * @param gameNews   the object to be used for the mapping
     * @throws IOException if an error occurs while outputting the file data
     */
    @Override
    public void write(JsonWriter jsonWriter, GameNews gameNews) throws IOException {

    }

    /**
     * Reads JSON into a new GameNews object.
     *
     * @param jsonReader the reader which contains the JSON data
     * @return the GameNews object which maps to the JSON data
     * @throws IOException if an error occurs while inputting the file data into the GameNews object
     */
    @Override
    public GameNews read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        jsonReader.beginObject();
        jsonReader.nextName();
        int gameId = jsonReader.nextInt();
        jsonReader.nextName();
        jsonReader.beginArray();
        List<NewsItem> newsItems = new ArrayList<>();
        while (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject();
            jsonReader.nextName();
            long id = jsonReader.nextLong();
            jsonReader.nextName();
            String title = jsonReader.nextString();
            jsonReader.nextName();
            String url = jsonReader.nextString();
            jsonReader.nextName();
            jsonReader.nextBoolean();
            jsonReader.nextName();
            String author = jsonReader.nextString();
            jsonReader.nextName();
            String contents = jsonReader.nextString();
            jsonReader.nextName();
            String feedLabel = jsonReader.nextString();
            jsonReader.nextName();
            long date = jsonReader.nextLong();
            jsonReader.nextName();
            String feedName = jsonReader.nextString();
            newsItems.add(new NewsItem(id, title, url, author, feedLabel, feedName, contents, date));
            jsonReader.endObject();
        }
        jsonReader.endArray();
        jsonReader.endObject();
        jsonReader.endObject();
        return new GameNews(gameId, newsItems.toArray(new NewsItem[newsItems.size()]));
    }
}
