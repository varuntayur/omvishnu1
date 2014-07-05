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
import android.view.Menu;
import android.view.MenuItem;

import com.varun.omvishnu.app.R;
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
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

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

    private List<Shloka> mShlokas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_screen_slide);

        System.out.println("-> Starting ScreenSlideActivity <-");

        Typeface devnanagariTf = Typeface.createFromAsset(getAssets(), "fonts/droidsansdevanagari.ttf");
        System.out.println("-> ScreenSlideActivity created font typeface <-" + devnanagariTf);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mSectionName = getIntent().getStringExtra("sectionName");
        mShlokas = (List<Shloka>) getIntent().getSerializableExtra("shlokaList");
        mPagerAdapter = new ShlokaSlidePagerAdapter(mSectionName, mShlokas, getFragmentManager(), devnanagariTf);
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
            }
        });
        System.out.println("* ScreenSlideActivity created *");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.activity_screen_slide, menu);
//
//        menu.findItem(R.id.action_previous).setEnabled(mPager.getCurrentItem() > 0);
//
//        // Add either a "next" or "finish" button to the action bar, depending on which page
//        // is currently selected.
//        MenuItem item = menu.add(Menu.NONE, R.id.action_next, Menu.NONE,
//                (mPager.getCurrentItem() == mPagerAdapter.getCount() - 1)
//                        ? R.string.action_finish
//                        : R.string.action_next
//        );
//        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                // Navigate "up" the demo structure to the launchpad activity.
//                // See http://developer.android.com/design/patterns/navigation.html for more.
//                NavUtils.navigateUpTo(this, new Intent(this, OmActivity.class));
//                return true;
//
//            case R.id.action_previous:
//                // Go to the previous step in the wizard. If there is no previous step,
//                // setCurrentItem will do nothing.
//                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
//                return true;
//
//            case R.id.action_next:
//                // Advance to the next step in the wizard. If there is no next step, setCurrentItem
//                // will do nothing.
//                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
//                return true;
//
//            case R.string.action_finish:
//                // Navigate "up" the demo structure to the launchpad activity.
//                // See http://developer.android.com/design/patterns/navigation.html for more.
////                NavUtils.navigateUpTo(this, new Intent(this, OmActivity.class));
////                return true;
//                Intent upIntent = new Intent(this, OmActivity.class);
//                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
//                    // This activity is not part of the application's task, so create a new task
//                    // with a synthesized back stack.
//                    TaskStackBuilder.from(this)
//                            .addNextIntent(upIntent)
//                            .startActivities();
//                    finish();
//                } else {
//                    // This activity is part of the application's task, so simply
//                    // navigate up to the hierarchical parent activity.
//                    NavUtils.navigateUpTo(this, upIntent);
//                }
//        }

        return super.onOptionsItemSelected(item);
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
