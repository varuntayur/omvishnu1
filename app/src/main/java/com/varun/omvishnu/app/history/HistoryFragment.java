package com.varun.omvishnu.app.history;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.model.sahasranama.Section;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class HistoryFragment extends FragmentActivity {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HistoryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_history);

        Typeface devnanagariTf = Typeface.createFromAsset(getAssets(), "fonts/droidsansdevanagari.ttf");

        Section section = DataProvider.getSahasranama().getSection("History");

        TextView view = (TextView) findViewById(R.id.history);

        view.setTypeface(devnanagariTf);

        view.setText(section.getShloka("1").getExplanation());

    }

}
