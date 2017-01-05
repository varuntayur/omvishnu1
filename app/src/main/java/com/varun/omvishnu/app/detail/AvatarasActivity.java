package com.varun.omvishnu.app.detail;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.adapters.StableArrayAdapter;
import com.varun.omvishnu.app.home.Language;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by varuntayur on 7/2/2014.
 */
public class AvatarasActivity extends ListActivity {

    private static String TAG = "AvatarasActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Collection<String> avataras = DataProvider.getAvatara2Shlokas().keySet();
        String[] stringAvataras = new String[avataras.size()];
        avataras.toArray(stringAvataras);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
//                android.R.layout.simple_list_item_1, stringAvataras);
        StableArrayAdapter adapter = new StableArrayAdapter(getBaseContext(),
                Arrays.asList(stringAvataras));
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) l.getAdapter().getItem(position);

        Log.d(TAG, "avatara -> " + item);
        Log.d(TAG, "shlokas -> " + DataProvider.getShlokaForAvatara(item));
        Language lang = (Language) getIntent().getSerializableExtra("lang");
        Intent intent = new Intent(getBaseContext(), ShlokaSlideActivity.class);
        intent.putExtra("sectionName", item);
        intent.putExtra("menuPosition", getIntent().getIntExtra("menuPosition", 0));
        intent.putExtra("shlokaList", (Serializable) DataProvider.getShlokaForAvatara(item));
        intent.putExtra("lang", lang);
        startActivity(intent);
    }
}
