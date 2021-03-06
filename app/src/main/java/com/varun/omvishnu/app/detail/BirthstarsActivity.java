package com.varun.omvishnu.app.detail;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.adapters.StableArrayAdapter;
import com.varun.omvishnu.app.data.model.sahasranama.Shloka;
import com.varun.omvishnu.app.home.Language;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by varuntayur on 7/2/2014.
 */
public class BirthstarsActivity extends ListActivity {

    private static String TAG = "BirthstarsActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Collection<String> stars = DataProvider.getBirthStarToShloka().keySet();
        String[] stringStars = new String[stars.size()];
        stars.toArray(stringStars);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
//                android.R.layout.simple_list_item_1, stringStars);
        StableArrayAdapter adapter = new StableArrayAdapter(getBaseContext(),
                Arrays.asList(stringStars));
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) l.getAdapter().getItem(position);

        Language lang = (Language) getIntent().getSerializableExtra("lang");
        Intent intent = new Intent(getBaseContext(), ShlokaSlideActivity.class);
        intent.putExtra("menuPosition", getIntent().getIntExtra("menuPosition", 0));
        intent.putExtra("sectionName", item);
        intent.putExtra("lang", lang);
        List<Shloka> shlokasForBirthStar = DataProvider.getShlokasForBirthStar(item, lang);
        intent.putExtra("shlokaList", (Serializable) shlokasForBirthStar);
        Log.d(TAG, "shlokas -> " + shlokasForBirthStar);
        Log.d(TAG, "nakshatra -> " + item);
        startActivity(intent);
    }
}
