package com.varun.omvishnu.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.varun.omvishnu.app.model.names.ThousandNames;
import com.varun.omvishnu.app.model.sahasranama.Sahasranama;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class OmActivity extends ActionBarActivity {

    private static DataProvider dataProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_om);

        new DataProviderTask().execute(getAssets());

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

    public static Sahasranama getSahasranama() {
        return dataProvider.getSahasranama();
    }

    public static ThousandNames getThousandNames() {
        return dataProvider.getThousandNames();
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

    private void setupLaunchMenu() {
        final List<String> list = new ArrayList<String>(dataProvider.getSahasranama().getSectionNames());
        list.add("ThousandNames");
        list.add("ThousandNamesByBirthStars");
        list.add("ThousandNamesByGods");
        final ListView listview = (ListView) findViewById(R.id.listSahasranamaSections);
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
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
//                                list.remove(item);
                                adapter.notifyDataSetChanged();


                                if (item.equals("ThousandNames")) {

                                    Intent intent = new Intent(OmActivity.this, ThousandNamesFragment.class);
                                    startActivity(intent);

                                } else if (item.equals("ThousandNamesByBirthStars")) {

                                    Intent intent = new Intent(OmActivity.this, ThousandNamesByBirthStarsFragment.class);
                                    startActivity(intent);

                                } else if (item.equals("ThousandNamesByGods")) {

                                    Intent intent = new Intent(OmActivity.this, ThousandNamesFragment.class);
                                    startActivity(intent);

                                } else {

                                    Intent intent = new Intent(OmActivity.this, ScreenSlideActivity.class);
                                    intent.putExtra("sectionName", item);
                                    startActivity(intent);

                                }
                                view.setAlpha(1);
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
                    setupLaunchMenu();
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


}


