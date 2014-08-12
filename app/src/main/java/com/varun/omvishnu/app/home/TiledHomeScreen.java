package com.varun.omvishnu.app.home;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;
import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.detail.SampleAdapter;

import java.util.List;

/**
 * Created by varuntayur on 7/13/2014.
 */
public class TiledHomeScreen extends FragmentActivity {

    private static final String TAG = "TiledHomeScreen";

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgv);

        progressDialog = ProgressDialog.show(this, "", getResources().getString(R.string.loading_please_wait), true);

        new DataProviderTask(this).execute(getAssets());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private class DataProviderTask extends AsyncTask<AssetManager, Void, Long> {

        Activity currentActivity;

        public DataProviderTask(Activity activity) {
            this.currentActivity = activity;
        }

        protected void onPostExecute(Long result) {
            final LayoutInflater inflater = currentActivity.getLayoutInflater();
            final Activity activity = this.currentActivity;
            runOnUiThread(new Runnable() {
                public void run() {

                    StaggeredGridView listView = (StaggeredGridView) findViewById(R.id.grid_view);

                    View header = inflater.inflate(R.layout.list_item_header_footer, null);
                    TextView txtHeaderTitle = (TextView) header.findViewById(R.id.txt_title);
                    txtHeaderTitle.setText(getResources().getString(R.string.app_name));

                    listView.addHeaderView(header);
                    header.setClickable(false);

                    SampleAdapter mAdapter = new SampleAdapter(activity, R.id.txt_line1);

                    final List<String> sectionNames = DataProvider.getMenuNames();

                    for (String data : sectionNames) {
                        mAdapter.add(data);
                    }
                    listView.setAdapter(mAdapter);
                    listView.setOnItemClickListener(getOnMenuClickListener(activity));

                    progressDialog.dismiss();
                }
            });
            Log.d(TAG, "Finished launching main-menu");

        }

        private AdapterView.OnItemClickListener getOnMenuClickListener(final Activity activity) {
            return new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getAdapter().getItem(position);

                    SahasranamaMenu.getEnum(item).execute(activity, item, position);

                }
            };
        }

        @Override
        protected Long doInBackground(AssetManager... assetManagers) {

            DataProvider.init(getAssets());
            Log.d(TAG, "Finished background task execution.");
            return 1l;
        }
    }

}