package com.android.music.sample.samplemusic;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.music.sample.samplemusic.Network.HttpConnectionObject;
import com.android.music.sample.samplemusic.Network.iTunesItem;
import com.android.music.sample.samplemusic.Network.iTunesItemParser;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    Boolean success = true;
    Boolean nonEmptyResult = false;
    public List<iTunesItem> iTunesList = new ArrayList<>();
    ListView listView;

    private static final String itunes_url = "https://itunes.apple.com/search?";
    private static final String media_key = "&media=";
    private static final String music_value = "music";
    private static final String clean_music = "&explicit=No";
    private static final String search_term = "term=";

    TextView result_error;
    String search_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .build();

        imageLoader.init(config);

        final EditText search_text = (EditText) findViewById(R.id.search_text);
        result_error = (TextView) findViewById(R.id.search_result_error);

        final Button search_btn = (Button) findViewById(R.id.search_button);
        search_btn.setOnClickListener(v -> {
            if(iTunesList.size()>0){
                iTunesList.removeAll(iTunesList);
            }
            String testing = search_text.getText().toString();
            Log.d("TESTING: "," search text = "+testing);
            search_query = formatSearchQuery(testing);
            Log.d("TESTING: "," query text = "+search_query);
            search_text.setText("");
            hideSoftKeyboard(search_text);
            new getData().execute(null,null);
        });

        search_text.setOnKeyListener((v, keyCode, event) -> {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            search_btn.performClick();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            });

        listView = (ListView) findViewById(R.id.result_list);
    }

    private String formatSearchQuery(String text){
        return text.toLowerCase().replace(" ","+");
    }

    //-- secondary thread for making network request
    public class getData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            HttpConnectionObject networkConnection = new HttpConnectionObject();

            String request = networkConnection.setUpSimpleRequest(itunes_url+search_term+search_query+media_key+music_value+clean_music, null, "GET");

            JSONObject response = networkConnection.getResponseJsonObject(request);

            if(response != null) {

                try {

                    int count = response.getInt("resultCount");

                    //nonEmptyResult = count > 0;

                    JSONArray jsonArray = response.getJSONArray("results");
                    Log.d("TESTING: ", "result array = "+jsonArray);

                    for(int i=0;i<count;i++){

                        JSONObject item = jsonArray.getJSONObject(i);
                        iTunesItem itunes = iTunesItemParser.getItunesItemFromJson(item);
                        iTunesList.add(itunes);

                    }

                    success = count > 0;


                } catch (JSONException e) {
                    success = false;
                    e.printStackTrace();
                }

            } else {

                success = false;
            }

            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            if (!success) {
                final String error_message = getString(R.string.result_empty) + search_query;
                result_error.setText(error_message);
                result_error.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            } else {
                listView.setVisibility(View.VISIBLE);
                setCustomAdapter(iTunesList);
                result_error.setVisibility(View.GONE);
            }
        }
    }


    //-- Sets the Adapter
    public void setCustomAdapter(final List<iTunesItem> list){

        CustomAdapter adapter = new CustomAdapter(this, list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent detail = new Intent(this, MusicDetail.class);
            detail.putExtra("item",iTunesList.get(position));
            startActivity(detail);
        });
    }

    public void hideSoftKeyboard(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
