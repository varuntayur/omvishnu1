package com.varun.omvishnu.app.home;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.adapters.MyExpandableListAdapter;
import com.varun.omvishnu.app.data.adapters.StableArrayAdapter;
import com.varun.omvishnu.app.data.model.home.Group;
import com.varun.omvishnu.app.detail.ShlokaSlideActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OmActivity extends ActionBarActivity {

    private static DataProvider dataProvider;

    private SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new DataProviderTask().execute(getAssets());

        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.om, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onConfigurationChanged() {

    }

    private class DataProviderTask extends AsyncTask<AssetManager, Void, Long> {

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Long result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    createMainMenu();
                }
            });
            System.out.println("Finished launching main-menu");

        }

        @Override
        protected Long doInBackground(AssetManager... assetManagers) {

            dataProvider = new DataProvider(getAssets());
            System.out.println("Finished background task execution");
            return 1l;
        }
    }

    private void createMainMenu() {

        final List<String> sectionNames = new ArrayList<String>(DataProvider.getSahasranama().getSectionNames());
        int indexOfSahasranama = sectionNames.indexOf("Sahasranama");

        setupMenuSection1(sectionNames, indexOfSahasranama);

        setupMenuSection2(sectionNames);

        setupMenuSection3(sectionNames, indexOfSahasranama);

    }

    private void setupMenuSection3(List<String> sectionNames, int indexOfSahasranama) {
        ListView listViewSection3 = (ListView) findViewById(R.id.listMainMenuSection3);
        List<String> sectionNamesPart2 = sectionNames.subList(indexOfSahasranama + 1, sectionNames.size());
        StableArrayAdapter adapter = new StableArrayAdapter(this, sectionNamesPart2);
        listViewSection3.setAdapter(adapter);

        listViewSection3.setOnItemClickListener(getOnMenuClickListener());
    }

    private void setupMenuSection2(List<String> sectionNames) {
        List<String> secNames = Arrays.asList(SahasranamaMenuGroupName.IN_BRIEF.toString(), SahasranamaMenuGroupName.DEEP_DIVE.toString(), SahasranamaMenuGroupName.BY_BIRTH_STAR.toString(), SahasranamaMenuGroupName.BY_AVATARA.toString());
        Group group = new Group(SahasranamaMenuGroupName.SAHASRANAMA_MENU_NAME.toString());
        for (String secName : secNames) {
            group.children.add(secName);
        }
        groups.append(SahasranamaMenuGroupName.SAHASRANAMA_MENU_NAME.ordinal(), group);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listMainMenuSection2);
        MyExpandableListAdapter adapter1 = new MyExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter1);
        listView.expandGroup(0);
    }

    private void setupMenuSection1(List<String> sectionNames, int lastMenuIndex) {

        ListView listViewSection1 = (ListView) findViewById(R.id.listMainMenuSection1);

        List<String> sectionNamesPart1 = sectionNames.subList(0, lastMenuIndex);

        StableArrayAdapter adapter = new StableArrayAdapter(this, sectionNamesPart1);
        listViewSection1.setAdapter(adapter);

        listViewSection1.setOnItemClickListener(getOnMenuClickListener());

    }

    private AdapterView.OnItemClickListener getOnMenuClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getAdapter().getItem(position);

                Toast.makeText(getBaseContext(), item,
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(OmActivity.this, ShlokaSlideActivity.class);
                intent.putExtra("sectionName", item);
                intent.putExtra("shlokaList", (Serializable) DataProvider.getSahasranama().getSection(item).getShlokaList());
                startActivity(intent);
            }
        };
    }
}


