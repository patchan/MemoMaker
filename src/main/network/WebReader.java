package network;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;

import javax.swing.*;

// This implementation of WebReader was adapted from the CPSC 210 Design Patterns and the Web example
// I also incorporated

/**
 * Simple demo application.
 */
public class WebReader {
    private static JPanel parsedInfo;

    public WebReader() throws IOException {
        parsedInfo = new JPanel();
        parsedInfo.setLayout(new BoxLayout(parsedInfo, BoxLayout.Y_AXIS));
        String url = "https://api.deezer.com/chart/0/albums";
        URL deezer = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) deezer.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseString = getJsonString(input);
        WebParser parser = new WebParser(parsedInfo);
        try {
            parser.parseChart(responseString.toString());
        } catch (JSONException e) {
            System.out.println("Failed to retrieve top albums of the day.");
        }
        input.close();
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

    // EFFECTS: returns a JPanel with parsed text for top 3 albums of the day
    public static JPanel getParsedText() {
        return parsedInfo;
    }
}