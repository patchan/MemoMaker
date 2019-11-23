package network;

// An example of a simple JSON parser to parse a json file representing a library of books
// The implementation of WebParser was borrowed from the CPSC210 JsonParserExample
// WebParser also borrows from https://dzone.com/articles/how-to-parse-json-data-from-a-rest-api-using-simpl

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class WebParser {

    public HashMap<Integer, ArrayList<String>> result;

    /**
     * Prints library parsed from JSON data to console
     * @param jsonObj  string containing JSON data
     * @throws JSONException when jsonData cannot be parsed as a JSONArray
     */

    // EFFECTS: parses jsonObj for album rank, album name, and artist name
    public void parseChart(String jsonObj) throws JSONException {
        JSONObject chart = new JSONObject(jsonObj);
        JSONArray albums = chart.optJSONArray("data");
        System.out.println("The top 3 albums of the day are: \n");
        result = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            JSONObject album = albums.getJSONObject(i);
            parseAlbum(album);
        }
    }

    // EFFECTS: parses an album for album name, album artist, rank, and album link
    private void parseAlbum(JSONObject album) throws JSONException {
        int position = album.getInt("position");
        String albumName = album.getString("title");
        JSONObject artist = album.getJSONObject("artist");
        String artistName = artist.getString("name");
        String link = album.getString("link");
        String albumPos = "--- Rank " + position + "---";
        String albumTitle = "Album: " + albumName;
        String albumArtist = "Artist: " + artistName;
        String albumLink = "Click here to listen: " + link;
        ArrayList<String> albums = new ArrayList<>();
        albums.add(albumPos);
        albums.add(albumTitle);
        albums.add(albumArtist);
        albums.add(albumLink);
        result.put(position, albums);
    }

    // EFFECTS: gets the album information at position i
    public ArrayList<String> getAlbum(int i) {
        return result.get(i);
    }

}