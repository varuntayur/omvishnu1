package com.varun.omvishnu.app.home;

import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.DataProvider;

/**
 * Created by varuntayur on 7/13/2014.
 */
public class MainActivity extends FragmentActivity {


    SectionsPagerAdapter mSectionsPagerAdapter;

    ViewPager mViewPager;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_paged);

        progressDialog = ProgressDialog.show(this, "", "Loading. Please wait...", true);

        new DataProviderTask().execute(getAssets());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private class DataProviderTask extends AsyncTask<AssetManager, Void, Long> {

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Long result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    // Create the adapter that will return a fragment for each of the three
                    // primary sections of the app.
                    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), getBaseContext());

                    // Set up the ViewPager with the sections adapter.
                    mViewPager = (ViewPager) findViewById(R.id.pager1);
                    mViewPager.setAdapter(mSectionsPagerAdapter);

                    progressDialog.dismiss();
                }
            });
            System.out.println("Finished launching main-menu");

        }

        @Override
        protected Long doInBackground(AssetManager... assetManagers) {

            DataProvider.init(getAssets());
            System.out.println("Finished background task execution");
            return 1l;
        }
    }

}