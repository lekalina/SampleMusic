package com.android.music.sample.samplemusic.Network;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionObject {

    public HttpConnectionObject() {

    }

    public String setUpSimpleRequest(String link, JSONObject obj, String requestType) {

        URL url;
        HttpURLConnection urlConnection = null;
        String response = null;
        int responseCode;

        try {

            url = new URL(link);
            urlConnection = (HttpURLConnection) url.openConnection();

            if(obj != null) {

                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod(requestType);
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.connect();

                OutputStream os = urlConnection.getOutputStream();
                os.write(obj.toString().getBytes("UTF-8"));
                os.flush();
                os.close();

            } else {

                urlConnection.setRequestMethod(requestType);
                urlConnection.setRequestProperty("Content-Type", "close");
                urlConnection.connect();
            }

            responseCode = urlConnection.getResponseCode();
            Log.d("TESTING: ", "response code = "+ responseCode);

            InputStream in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            StringBuilder sb = new StringBuilder();

            char[] buf = new char[8192];
            int charsRead;
            while((charsRead = isw.read(buf, 0, 8192)) > 0) {
                sb.append(buf, 0, charsRead);
            }

            response = sb.toString();
            Log.d("TESTING: ","response = "+response);

            if (responseCode > 299 || responseCode < 200) {
                response = "error";
            }

            if(response == null || response.trim().length() == 0) {
                response = "success";
            }

            urlConnection.disconnect();

        } catch (Exception e) {
            Log.e("TESTING: ", "error = "+e.toString());
            e.printStackTrace();

        } finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return response;
    }
}
