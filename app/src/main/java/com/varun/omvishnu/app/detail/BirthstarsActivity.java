package com.varun.omvishnu.app.detail;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.varun.omvishnu.app.data.DataProvider;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by varuntayur on 7/2/2014.
 */
public class BirthstarsActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Collection<String> stars = DataProvider.getBirthStarToShloka().keySet();
        String[] stringStars = new String[stars.size()];
        stars.toArray(stringStars);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1, stringStars);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) l.getAdapter().getItem(position);

        System.out.println("nakshatra -> " + item);
        System.out.println("shlokas -> " + DataProvider.getShlokasForBirthStar(item));

        Intent intent = new Intent(getBaseContext(), ShlokaSlideActivity.class);
        intent.putExtra("sectionName", item);
        intent.putExtra("shlokaList", (Serializable) DataProvider.getShlokasForBirthStar(item));
        startActivity(intent);
    }
}