package de.reneruck.thirdfeCoffee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Rene on 16/08/13.
 */
public class IconFetcherAsync extends AsyncTask<Void, Void, Void> {
	
    private static final String TAG = "IconFetcherAsync";
    
	private final List<Place> mPlaces;
    private Map<Integer, String> mIcons = new LinkedHashMap<Integer, String>();
	private Context mContext;
	private File mIconsDir;

    public IconFetcherAsync(Context context, List<Place> places) {
    	mContext = context;
        mPlaces = places;
    }

    @Override
    protected Void doInBackground(Void... voids) {
    	mIconsDir = mContext.getDir(Statics.ICON_PREFETCH_DIR, Context.MODE_PRIVATE);
        
        mIcons = extractIcons();
        removeAvailableIcons();
        
        for(Integer id: mIcons.keySet()) {
            fetchIcon(mIcons.get(id), id);
        }
        return null;
    }

    private void fetchIcon(String iconPath, Integer id) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(iconPath);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            InputStream content = response.getEntity().getContent();
			byte[] imageBuffer = new byte[(int) response.getEntity().getContentLength()];
			if(imageBuffer.length > 0) {
				content.read(imageBuffer);
			} else {
				Log.e(TAG,"Invalid response length");
			}

            writeToFile(imageBuffer, id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

	private void writeToFile(byte[] imageBuffer, int fileId) throws IOException {
        File imageFile = new File(mIconsDir, Integer.toString(fileId));
        imageFile.createNewFile();
        FileOutputStream stream = new FileOutputStream(imageFile);
        stream.write(imageBuffer);
        stream.flush();
    }

    private void removeAvailableIcons() {
		String[] files = mIconsDir.list();
		for (int i = 0; i < files.length; i++) {
			mIcons.remove(Integer.parseInt(files[i]));
		}
    }


    private Map<Integer, String> extractIcons() {
        Map<Integer, String> icons = new HashMap<Integer, String>();
        for(Place place : mPlaces) {
            icons.put(place.getId(), place.getLogo());
        }
        return icons;
    }


}
