package com.varun.omvishnu.app;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.varun.omvishnu.app.model.sahasranama.Section;
import com.varun.omvishnu.app.model.sahasranama.Shloka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * A fragment representing a nakshatraList of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ThousandNamesByBirthStarsFragment extends FragmentActivity {

    /**
     * The fragment's ListView/GridView.
     */
    private ListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    private List<String> nakshatraList = new ArrayList<String>(Arrays.asList("Ashvini", "Bharani", "Krithika", "Rohini", "Mrigashirsha", "Ardra", "Punarvasu", "Pushya", "Ashlesha", "Magha", "Purva Phalguni", "Uttara Phalguni", "Hasta", "Chitra", "Swati", "Vishakha", "Anuradha", "Jyeshtha", "Mula", "Purva Ashadha", "Uttara Ashadha", "Shravana", "Dhanishtha", "Shatabhisha", "Purva Bhadrapada", "Uttara Bhadrapada", "Revati"));

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ThousandNamesByBirthStarsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_thousandnamesbybirthstars);

        Section sahasranama = OmActivity.getSahasranama().getSection("Sahasranama");
        List<Shloka> shlokas = sahasranama.getShlokaList();

        List<String> lstShlokas = new ArrayList<String>(shlokas.size() + 27);
        Iterator<String> nakshatraListIter = nakshatraList.listIterator();
        for (int i = 0; i < shlokas.size(); i++) {
            if (i % 4 == 0) {
                lstShlokas.add(nakshatraListIter.next());
                lstShlokas.add(shlokas.get(i).getFormattedShloka());
                continue;
            }
            lstShlokas.add(shlokas.get(i).getFormattedShloka());
        }

        Typeface devnanagariTf = Typeface.createFromAsset(getAssets(), "fonts/droidsansdevanagari.ttf");
        TextView view = (TextView) findViewById(R.id.namesByBirthStars);

        view.setTypeface(devnanagariTf);

        view.setText(lstShlokas.toString().replaceAll("\\[", "").replaceAll("\\]", ""));

    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}
