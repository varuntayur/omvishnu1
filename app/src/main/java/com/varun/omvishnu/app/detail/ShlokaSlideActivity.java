/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.varun.omvishnu.app.detail;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.model.sahasranama.Shloka;

import java.util.List;

/**
 * Demonstrates a "screen-slide" animation using a {@link android.support.v4.view.ViewPager}. Because {@link android.support.v4.view.ViewPager}
 * automatically plays such an animation when calling {@link android.support.v4.view.ViewPager#setCurrentItem(int)}, there
 * isn't any animation-specific code in this sample.
 * <p/>
 * <p>This sample shows a "next" button that advances the user to the next step in a wizard,
 * animating the current screen out (to the left) and the next screen in (from the right). The
 * reverse animation is played when the user presses the "previous" button.</p>
 *
 * @see ShlokaPageFragment
 */
public class ShlokaSlideActivity extends FragmentActivity {

    private static String TAG = "ShlokaSlideActivity";

    protected List<Shloka> mShlokas;
    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    private String mSectionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shloka_slide);

        Log.d(TAG, "-> Starting ScreenSlideActivity <-");

        Typeface devnanagariTf = Typeface.createFromAsset(getAssets(), "fonts/droidsansdevanagari.ttf");

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);

        mPager.setPageTransformer(true, new ZoomOutPageTransformer());

        Integer menuPosition = getIntent().getIntExtra("menuPosition", 0);
        mPager.setBackgroundResource(DataProvider.getBackgroundColor(menuPosition - 1));

        mSectionName = getIntent().getStringExtra("sectionName");
        if (mShlokas == null)
            mShlokas = (List<Shloka>) getIntent().getSerializableExtra("shlokaList");
        mPagerAdapter = new ShlokaSlidePagerAdapter(mSectionName, mShlokas, getFragmentManager(), devnanagariTf);
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                invalidateOptionsMenu();
            }
        });
        Log.d(TAG, "* ScreenSlideActivity created *");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * A simple pager adapter that represents 5 {@link ShlokaPageFragment} objects, in
     * sequence.
     */
    private class ShlokaSlidePagerAdapter extends FragmentStatePagerAdapter {

        private final Typeface tf;
        private final String sectionName;
        private List<Shloka> shlokas;

        public ShlokaSlidePagerAdapter(String sectionName, List<Shloka> shlokas, FragmentManager fm, Typeface tf) {
            super(fm);
            this.tf = tf;
            this.shlokas = shlokas;
            this.sectionName = sectionName;
        }

        @Override
        public Fragment getItem(int position) {
            return new ShlokaPageFragment(sectionName, shlokas, position, tf);
        }

        @Override
        public int getCount() {
            return shlokas.size();
        }
    }
}
