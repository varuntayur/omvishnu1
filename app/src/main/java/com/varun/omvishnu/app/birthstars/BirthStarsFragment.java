package com.varun.omvishnu.app.birthstars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.common.StableArrayAdapter;
import com.varun.omvishnu.app.data.DataProvider;

import java.util.ArrayList;

/**
 * A fragment representing a nakshatraList of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class BirthStarsFragment extends FragmentActivity {

    public BirthStarsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_birthstars_new);

        final ListView listview = (ListView) findViewById(R.id.listStars);
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, new ArrayList<String>(DataProvider.getBirthStarToShloka().keySet()));
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final String item = (String) adapterView.getItemAtPosition(i);

                adapter.notifyDataSetChanged();

                System.out.println("Item selected = " + item);

                showBirthStarShlokas(item);
            }
        });
    }

    public void showBirthStarShlokas(String itemName) {
        System.out.println("nakshatra -> " + itemName);
        System.out.println("shlokas -> " + DataProvider.getBirthStarToShloka().get(itemName));

        Intent intent = new Intent(this, BirthStarsShlokaFragment.class);
        intent.putExtra("nakshatraName", itemName);
        startActivity(intent);
    }


}
