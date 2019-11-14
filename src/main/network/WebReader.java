package network;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;

// This implementation of WebReader was adapted from the CPSC 210 Design Patterns and the Web example
// I also incorporated

/**
 * Simple demo application.
 */
public class WebReader {

    public static void main(String[] args) throws IOException, JSONException {
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
    }

    public static StringBuilder getJsonString(BufferedReader input) throws IOException {
        StringBuilder responseString = new StringBuilder();
        String line;
        while ((line = input.readLine()) != null) {
            responseString.append(line);
        }
        return responseString;
    }

}