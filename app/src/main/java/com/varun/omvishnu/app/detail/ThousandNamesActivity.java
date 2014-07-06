package com.varun.omvishnu.app.detail;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.adapters.HtmlTextAdapter;
import com.varun.omvishnu.app.data.adapters.StableArrayAdapter;

/**
 * Created by varuntayur on 7/2/2014.
 */
public class ThousandNamesActivity extends Activity {

    // List view
    private ListView lv;

    // Search EditText
    EditText inputSearch;

    StableArrayAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thousand_names);


        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        // Adding items to listview
        adapter = new HtmlTextAdapter(this, DataProvider.getThousandNames().getLstStringNamas());
        lv.setAdapter(adapter);


        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                ThousandNamesActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }

}
