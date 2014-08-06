package com.varun.omvishnu.app.home;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.model.sahasranama.Shloka;
import com.varun.omvishnu.app.detail.AvatarasActivity;
import com.varun.omvishnu.app.detail.BirthstarsActivity;
import com.varun.omvishnu.app.detail.SahasranamaShlokaSlideActivity;
import com.varun.omvishnu.app.detail.SampleAdapter;
import com.varun.omvishnu.app.detail.ShlokaSlideActivity;
import com.varun.omvishnu.app.detail.ThousandNamesActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by varuntayur on 7/13/2014.
 */
public class TiledHomeScreen extends FragmentActivity {


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgv);

        progressDialog = ProgressDialog.show(this, "", "Loading. Please wait...", true);

        new DataProviderTask(this).execute(getAssets());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private class DataProviderTask extends AsyncTask<AssetManager, Void, Long> {

        Activity currentActivity;

        public DataProviderTask(Activity activity) {
            this.currentActivity = activity;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Long result) {
            final LayoutInflater inflater = currentActivity.getLayoutInflater();
            final Activity activity = this.currentActivity;
            runOnUiThread(new Runnable() {
                public void run() {

                    StaggeredGridView listView = (StaggeredGridView) findViewById(R.id.grid_view);

                    View header = inflater.inflate(R.layout.list_item_header_footer, null);
//                    View footer = inflater.inflate(R.layout.list_item_header_footer, null);
                    TextView txtHeaderTitle = (TextView) header.findViewById(R.id.txt_title);
//                    TextView txtFooterTitle = (TextView) footer.findViewById(R.id.txt_title);
                    txtHeaderTitle.setText("Sri Vishnu Sahasranama Complete Reference");
//                    txtFooterTitle.setText("");

                    listView.addHeaderView(header);
//                    listView.addFooterView(footer);

                    SampleAdapter mAdapter = new SampleAdapter(activity, R.id.txt_line1);

                    final List<String> sectionNames = new ArrayList<String>(DataProvider.getSahasranama().getSectionNames());

                    int indexOfSahasranama = sectionNames.indexOf("Sahasranama");

                    List<String> builtSectionNames = new ArrayList<String>(sectionNames.subList(0, indexOfSahasranama));

                    builtSectionNames.addAll(Arrays.asList(SahasranamaMenuGroupName.IN_BRIEF.toString(), SahasranamaMenuGroupName.DEEP_DIVE.toString(), SahasranamaMenuGroupName.BY_BIRTH_STAR.toString(), SahasranamaMenuGroupName.BY_AVATARA.toString()));

                    builtSectionNames.addAll(sectionNames.subList(indexOfSahasranama + 1, sectionNames.size()));


                    for (String data : builtSectionNames) {
                        mAdapter.add(data);
                    }

//                    final SampleAdapterImage adapter1 = new SampleAdapterImage(this.getActivity(), android.R.layout.simple_list_item_1, sampleData);
                    listView.setAdapter(mAdapter);
                    listView.setOnItemClickListener(getOnMenuClickListener(activity, activity.getBaseContext()));

                    progressDialog.dismiss();
                }
            });
            System.out.println("Finished launching main-menu");

        }

        private AdapterView.OnItemClickListener getOnMenuClickListener(final Activity activity, final Context ctx) {
            return new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getAdapter().getItem(position);

                    Toast.makeText(ctx, item,
                            Toast.LENGTH_SHORT).show();


                    if (SahasranamaMenuGroupName.DEEP_DIVE.toString().equalsIgnoreCase(item)) {

                        Intent intent = new Intent(activity, SahasranamaShlokaSlideActivity.class);
                        intent.putExtra("sectionName", SahasranamaMenuGroupName.DEEP_DIVE.getMenuDisplayKey());
                        intent.putExtra("menuPosition", position);
                        intent.putExtra("shlokaList", (Serializable) new ArrayList<Shloka>());
                        startActivity(intent);

                    } else if (SahasranamaMenuGroupName.IN_BRIEF.toString().equalsIgnoreCase(item)) {

                        Intent intent = new Intent(activity, ThousandNamesActivity.class);
                        intent.putExtra("menuPosition", position);
                        startActivity(intent);

                    } else if (SahasranamaMenuGroupName.BY_BIRTH_STAR.toString().equalsIgnoreCase(item)) {

                        Intent intent = new Intent(activity, BirthstarsActivity.class);
                        intent.putExtra("menuPosition", position);
                        startActivity(intent);

                    } else if (SahasranamaMenuGroupName.BY_AVATARA.toString().equalsIgnoreCase(item)) {

                        Intent intent = new Intent(activity, AvatarasActivity.class);
                        intent.putExtra("menuPosition", position);
                        startActivity(intent);

                    } else {
                        Intent intent = new Intent(activity, ShlokaSlideActivity.class);
                        intent.putExtra("sectionName", item);
                        intent.putExtra("menuPosition", position);
                        intent.putExtra("shlokaList", (Serializable) DataProvider.getSahasranama().getSection(item).getShlokaList());
                        startActivity(intent);
                    }
                }
            };
        }

        @Override
        protected Long doInBackground(AssetManager... assetManagers) {

            DataProvider.init(getAssets());
            System.out.println("Finished background task execution");
            return 1l;
        }
    }

}