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

import android.os.Bundle;
import android.util.Log;

import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.home.Language;
import com.varun.omvishnu.app.home.SahasranamaMenu;

/**
 * Demonstrates a "screen-slide" animation using a {@link android.support.v4.view.ViewPager}. Because {@link android.support.v4.view.ViewPager}
 * automatically plays such an animation when calling {@link android.support.v4.view.ViewPager#setCurrentItem(int)}, there
 * isn't any animation-specific code in this sample.
 * <p/>
 * <p>This sample shows a "next" button that advances the user to the next step in a wizard,
 * animating the current screen out (to the left) and the next screen in (from the right). The
 * reverse animation is played when the user presses the "previous" button.</p>
 *
 * @see com.varun.omvishnu.app.detail.ShlokaPageFragment
 */
public class SahasranamaShlokaSlideActivity extends ShlokaSlideActivity {

    private static String TAG = "SahShlokaSlideActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Language lang = (Language) getIntent().getSerializableExtra("lang");
        mShlokas = DataProvider.getSahasranama(lang).getSection(SahasranamaMenu.DEEP_DIVE.getMenuDisplayKey()).getShlokaList();
        Log.d(TAG, "-> Starting SahasranamaScreenSlideActivity <-");

        super.onCreate(savedInstanceState);

    }

}
