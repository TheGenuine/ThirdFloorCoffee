package de.reneruck.thirdfeCoffee;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.google.gson.Gson;

public class PlacesListActivity extends Activity {

    private PlacesSet mPlacesSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_list_activity);
        mPlacesSet = readLocalList();
        fetchIcons(mPlacesSet.getPlaces());

        initList();
    }

    private void initList() {
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new PlacesListAdapter(mPlacesSet.getPlaces()));
        listView.invalidate();

    }

    private void fetchIcons(List<Place> places) {
        IconFetcherAsync iconFetcher = new IconFetcherAsync(this, places);
        iconFetcher.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.places_list, menu);
        return true;
    }

    private PlacesSet readLocalList() {
        try {
            InputStream placesFile = getAssets().open("places.json");
            String fileContent = readFileContent(placesFile);
            Gson gson = new Gson();
            return gson.fromJson(fileContent, PlacesSet.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String readFileContent(InputStream inputStream) throws IOException {
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        return new String(buffer);
    }
}
