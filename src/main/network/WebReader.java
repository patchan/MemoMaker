package network;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;

// This implementation of WebReader was adapted from the CPSC 210 Design Patterns and the Web example
// I also incorporated

/**
 * Simple demo application.
 */
public class WebReader {
    private ArrayList<ArrayList<String>> albums;

    public WebReader() throws IOException {
        String url = "https://api.deezer.com/chart/0/albums";
        URL deezer = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) deezer.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseString = getJsonString(input);
        WebParser parser = new WebParser();
        try {
            parser.parseChart(responseString.toString());
        } catch (JSONException e) {
            System.out.println("Failed to retrieve top albums of the day.");
        }
        input.close();
        loadAlbumInfo(parser);
    }

    public static void main(String[] args) throws IOException {
        new WebReader();
    }

    // EFFECTS: returns the json string as a StringBuilder
    private static StringBuilder getJsonString(BufferedReader input) throws IOException {
        StringBuilder responseString = new StringBuilder();
        String line;
        while ((line = input.readLine()) != null) {
            responseString.append(line);
        }
        return responseString;
    }

    private void loadAlbumInfo(WebParser parser) {
        for (int i = 0; i < 3; i++) {
            ArrayList<String> album = parser.getAlbum(i);
            albums.add(album);
        }
    }

    // EFFECTS: returns the top album of the day at index i
    public ArrayList<String> getAlbum(int i) {
        return albums.get(i - 1);
    }
}