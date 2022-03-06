package com.holiday.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

@Component
public class DataReader {

    public JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        JSONArray json;
        try {
            String jsonText = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8"))).lines().collect(Collectors.joining("\n"));
            json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

}
