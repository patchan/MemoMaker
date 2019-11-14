package network;

// An example of a simple JSON parser to parse a json file representing a library of books
// The implementation of WebParser was borrowed from the CPSC210 JsonParserExample
// WebParser also borrows from https://dzone.com/articles/how-to-parse-json-data-from-a-rest-api-using-simpl

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebParser {

    /**
     * Prints library parsed from JSON data to console
     * @param jsonObj  string containing JSON data
     * @throws JSONException when jsonData cannot be parsed as a JSONArray
     */

    public void parseChart(String jsonObj) throws JSONException {
        JSONObject chart = new JSONObject(jsonObj);
        JSONArray albums = chart.optJSONArray("data");
        System.out.println("The top 3 albums of the day are: \n");

        for (int i = 0; i < 3; i++) {
            JSONObject album = albums.getJSONObject(i);
            parseAlbum(album);
        }
    }

    public void parseAlbum(JSONObject album) throws JSONException {
        int position = album.getInt("position");
        String albumName = album.getString("title");
        JSONObject artist = album.getJSONObject("artist");
        String artistName = artist.getString("name");
        String link = album.getString("link");
        System.out.println("--- Rank " + position + " ---");
        System.out.println("Album: " + albumName);
        System.out.println("Artist: " + artistName);
        System.out.println("Click here to listen: " + link);
        System.out.println();
    }

}