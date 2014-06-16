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

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.birthstars.BirthStarsFragment;
import com.varun.omvishnu.app.common.StableArrayAdapter;
import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.model.home.Group;
import com.varun.omvishnu.app.history.HistoryFragment;
import com.varun.omvishnu.app.indetail.ScreenSlideActivity;
import com.varun.omvishnu.app.thousandnames.ThousandNamesFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class OmActivity extends ActionBarActivity {

    private static DataProvider dataProvider;

    // more efficient than HashMap for mapping integers to objects
    SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DataProviderTask().execute(getAssets());
//        setContentView(R.layout.activity_home);

        setContentView(R.layout.activity_main);
//        createData();
//        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
//        MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,
//                groups);
//        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.om, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startMainActivity(View view) {
        Intent intent = new Intent(this, ScreenSlideActivity.class);

        startActivity(intent);
    }


    private void setupLaunchMenu() {
        final ListView listview = (ListView) findViewById(R.id.listSahasranamaSections);
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, dataProvider.getSectionNames());
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(100).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {

                                adapter.notifyDataSetChanged();

                                launchActivity();

                                view.setAlpha(1);
                            }

                            private void launchActivity() {

                                if (item.equals("ThousandNames")) {

                                    Intent intent = new Intent(OmActivity.this, ThousandNamesFragment.class);
                                    startActivity(intent);

                                } else if (item.equals("ThousandNamesByBirthStars")) {

                                    Intent intent = new Intent(OmActivity.this, BirthStarsFragment.class);
                                    startActivity(intent);

                                } else if (item.equals("ThousandNamesByGods")) {

                                    Intent intent = new Intent(OmActivity.this, ThousandNamesFragment.class);
                                    startActivity(intent);

                                } else if (item.equals("History")) {

                                    Intent intent = new Intent(OmActivity.this, HistoryFragment.class);
                                    startActivity(intent);

                                } else {

                                    Intent intent = new Intent(OmActivity.this, ScreenSlideActivity.class);
                                    intent.putExtra("sectionName", item);
                                    startActivity(intent);

                                }
                            }
                        });
            }

        });
    }

    private class DataProviderTask extends AsyncTask<AssetManager, Void, Long> {

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Long result) {
            runOnUiThread(new Runnable() {
                public void run() {
//                    setupLaunchMenu();
                    createData();
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

    public void createData() {
//        for (int j = 0; j < 1; j++) {
//            Group group = new Group("Test " + j);
//            for (int i = 0; i < 5; i++) {
//                group.children.add("Sub Item" + i);
//            }
//            groups.append(j, group);
//        }
        final Collection<String> sectionNames = DataProvider.getSahasranama().getSectionNames();
        Group group = new Group("Sahasranama");
        for (String secName : sectionNames) {
            group.children.add(secName);
        }
        groups.append(0, group);

        final ListView listview = (ListView) findViewById(R.id.listSahasranamaSections);
        final List<String> list = new ArrayList<String>();
        list.add("ThousandNames");
        list.add("ThousandNamesByBirthStars");
        list.add("ThousandNamesByGods");
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        MyExpandableListAdapter adapter1 = new MyExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter1);

    }


}


