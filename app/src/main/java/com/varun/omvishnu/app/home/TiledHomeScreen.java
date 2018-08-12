package com.varun.omvishnu.app.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.detail.SampleAdapter;
import com.varun.omvishnu.app.settings.SettingsActivity;

import java.util.List;

/**
 * Created by varuntayur on 7/13/2014.
 */
public class TiledHomeScreen extends AppCompatActivity {

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

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.help:
                Toast.makeText(getApplicationContext(), "Help Selected", Toast.LENGTH_LONG).show();

                // Inflate the about message contents
                View messageView = getLayoutInflater().inflate(R.layout.about, null, false);

                // When linking text, force to always use default color. This works
                // around a pressed color state bug.
                TextView textView = (TextView) messageView.findViewById(R.id.about_credits);
                int defaultColor = textView.getTextColors().getDefaultColor();
                textView.setTextColor(defaultColor);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.dashavatharam);
                builder.setTitle(R.string.app_name);
                builder.setView(messageView);
                builder.create();
                builder.show();

                return true;
            case R.id.languageselector:
                Intent intent = new Intent(this, SettingsActivity.class);
                Log.d(TAG, "Launching Settings Activity");
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
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

                    String langSelected = getSharedPreferences(DataProvider.PREFS_NAME, 0).getString(DataProvider.SHLOKA_DISP_LANGUAGE, "");
                    final List<String> sectionNames = DataProvider.getMenuNames(Language.getLanguageEnum(langSelected));

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

                    String langSelected = getSharedPreferences(DataProvider.PREFS_NAME, 0).getString(DataProvider.SHLOKA_DISP_LANGUAGE, "");
                    SahasranamaMenu.getEnum(item).execute(activity, item, position, Language.getLanguageEnum(langSelected));

                }
            };
        }

        @Override
        protected Long doInBackground(AssetManager... assetManagers) {

            SharedPreferences settings = getSharedPreferences(DataProvider.PREFS_NAME, 0);
            String isSettingAlreadySaved = settings.getString(DataProvider.SHLOKA_DISP_LANGUAGE, "");
            if (isSettingAlreadySaved.isEmpty()) {
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(DataProvider.SHLOKA_DISP_LANGUAGE, Language.san.toString());

                editor.commit();

                Log.d(TAG, "Setting the default launch preference to Sanskrit at startup - " + settings.getString(DataProvider.SHLOKA_DISP_LANGUAGE, ""));
            }

            String langSelected = getSharedPreferences(DataProvider.PREFS_NAME, 0).getString(DataProvider.SHLOKA_DISP_LANGUAGE, "");

            DataProvider.init(getAssets(), Language.getLanguageEnum(langSelected));
            Log.d(TAG, "Finished background task execution.");
            return 1l;
        }
    }

}