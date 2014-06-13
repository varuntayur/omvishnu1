package com.varun.omvishnu.app.thousandnames;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.common.StableArrayAdapter;
import com.varun.omvishnu.app.data.DataProvider;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ThousandNamesFragment extends FragmentActivity {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ThousandNamesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_thousandnames);

        List<String> list = DataProvider.getThousandNames().getLstStringNamas();

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);

        ListView view = (ListView) findViewById(R.id.listNama);

        view.setAdapter(adapter);


    }

}
