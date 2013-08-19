package de.reneruck.thirdfeCoffee;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Rene on 16/08/13.
 */
public class PlacesListAdapter extends BaseAdapter {

    private List<Place> mPlaces = new LinkedList<Place>();

    public PlacesListAdapter(List<Place> mPlaces) {
        this.mPlaces = mPlaces;
    }

    @Override
    public int getCount() {
        return mPlaces.size();
    }

    @Override
    public Object getItem(int i) {
        return mPlaces.get(i);
    }

    @Override
    public long getItemId(int i) {
        Place p = mPlaces.get(i);
        if(p != null) {
            return p.getId();
        }
        return -1;
    }

    @Override
    public View getView(int position, View converterView, ViewGroup parent) {
    	View view = converterView;
        if(view == null) {
        	view = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_list_entry, null);
        }

        Place place = mPlaces.get(position);

        File icon = new File(parent.getContext().getDir(Statics.ICON_PREFETCH_DIR, Context.MODE_PRIVATE), Integer.toString(place.getId()));
        
        ((TextView) view.findViewById(R.id.place_name)).setText(place.getName());

        if(icon.exists()) {
            try {
				((ImageView) view.findViewById(R.id.place_icon)).setImageBitmap(BitmapFactory.decodeStream(new FileInputStream(icon)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        }

        return view;
    }
}
