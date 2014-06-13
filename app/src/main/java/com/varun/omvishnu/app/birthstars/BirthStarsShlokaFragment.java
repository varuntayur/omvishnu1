package com.varun.omvishnu.app.birthstars;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.varun.omvishnu.app.R;
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
public class BirthStarsShlokaFragment extends FragmentActivity {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BirthStarsShlokaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_birthstarshloka);

        Typeface devnanagariTf = Typeface.createFromAsset(getAssets(), "fonts/droidsansdevanagari.ttf");

        String nakshatraName = getIntent().getStringExtra("nakshatraName");

        List<String> lstNakshatras = DataProvider.getBirthStarToShloka().get(nakshatraName);

        TextView view = (TextView) findViewById(R.id.listBirthStarShlokas);

        view.setTypeface(devnanagariTf);

        StringBuilder sBuilder = new StringBuilder();

        sBuilder.append("\n");
        for (int i = 0; i < lstNakshatras.size(); i++) {
            sBuilder.append(lstNakshatras.get(i));
            sBuilder.append("----------\n");
        }

        view.setText(sBuilder.toString());

    }

}
