package network;

// An example of a simple JSON parser to parse a json file representing a library of books
// The implementation of WebParser was borrowed from the CPSC210 JsonParserExample
// WebParser also borrows from https://dzone.com/articles/how-to-parse-json-data-from-a-rest-api-using-simpl

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;

public class WebParser {
    private JPanel panel;

    public WebParser(JPanel panel) {
        this.panel = panel;
    }

    /**
     * Prints library parsed from JSON data to console
     * @param jsonObj  string containing JSON data
     * @throws JSONException when jsonData cannot be parsed as a JSONArray
     */

    // EFFECTS: parses jsonObj for album rank, album name, and artist name
    public void parseChart(String jsonObj) throws JSONException {
        JSONObject chart = new JSONObject(jsonObj);
        JSONArray albums = chart.optJSONArray("data");
        panel.add(new JTextArea("For inspiration, the top 3 albums of the day are:"));

        for (int i = 0; i < 3; i++) {
            JSONObject album = albums.getJSONObject(i);
            parseAlbum(album);
        }
    }

    // EFFECTS: parses an album for album name, artist, rank, and album link and adds it to this.panel
    private void parseAlbum(JSONObject album) throws JSONException {
        int position = album.getInt("position");
        String albumName = album.getString("title");
        JSONObject artist = album.getJSONObject("artist");
        String artistName = artist.getString("name");
        String link = album.getString("link");
        addToText(position, albumName, artistName);
        addLink(link);
    }

    // EFFECTS: adds album position, album name, and album artist to this.panel
    private void addToText(int pos, String album, String artist) {
        JTextArea text = new JTextArea();
        text.setEditable(false);
        text.append("\nRank " + pos + ":");
        text.append("\nAlbum: " + album);
        text.append("\nArtist: " + artist);
        panel.add(text);
    }

    private void addLink(String link) {
        JEditorPane linkPane = new JEditorPane();
        linkPane.setContentType("text/html");
        linkPane.setEditable(false);
        linkPane.setText("Click <a href=" + link + ">here</a> to listen.");
        linkPane.addHyperlinkListener(new LinkListener());
        panel.add(linkPane);
    }

    // this implementation of HyperlinkListener was adapted from
    // https://stackoverflow.com/questions/14862425/clickable-html-link-in-jeditorpane
    private class LinkListener implements HyperlinkListener {
        @Override
        public void hyperlinkUpdate(HyperlinkEvent e) {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                System.out.println(e.getURL());
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}