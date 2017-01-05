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

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.common.ShlokaMediaPlayer;
import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.model.sahasranama.Shloka;

import java.util.List;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 * <p/>
 * <p>This class is used by the {} and {@link
 * ShlokaSlideActivity} samples.</p>
 */
public class ShlokaPageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    private static String TAG = "ShlokaPageFragment";
    private static Typeface devanagariTf;

    private String sectionName;

    private List<Shloka> shlokas;

    private int mPageNumber;

    public ShlokaPageFragment() {
    }

    public ShlokaPageFragment(String sectionName, List<Shloka> shlokas, int position, Typeface tf) {
        this.shlokas = shlokas;
        this.devanagariTf = tf;
        this.mPageNumber = position;
        this.sectionName = sectionName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this fragment across configuration changes.
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initalizeView(inflater, container);

    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d(TAG, "************ Attempting to stop media that was initiated with this fragment *********");
        ShlokaMediaPlayer.release();
        Log.d(TAG, "************ Pause media was successful *********");
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return shlokas.size();
    }

    private View initalizeView(LayoutInflater inflater, ViewGroup container) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_shloka_slide_page, container, false);

        final Activity curActivity = this.getActivity();

        String displayPageNumber = String.valueOf(mPageNumber + 1);

        TextView secTitleViewById = (TextView) rootView.findViewById(R.id.sectiontitle);
        secTitleViewById.setText(sectionName + " ( " + displayPageNumber + " / " + shlokas.size() + " )");

        final Shloka shloka = shlokas.get(mPageNumber);

        TextView shlokaText = (TextView) rootView.findViewById(R.id.shlokatext);
        shlokaText.setTypeface(devanagariTf);
        shlokaText.setText(shloka.getText());
        shlokaText.setTypeface(shlokaText.getTypeface(), Typeface.BOLD);

        TextView shlokaenText = (TextView) rootView.findViewById(R.id.shlokaentext);
        shlokaenText.setText(shloka.getEnText());
        shlokaenText.setTypeface(shlokaText.getTypeface(), Typeface.BOLD);

        WebView shlokaExplanation = (WebView) rootView.findViewById(R.id.shlokaexplanation);
        shlokaExplanation.setBackgroundColor(Color.TRANSPARENT);
        shlokaExplanation.loadData(shloka.getFormattedExplanation(), "text/html", null);

        final String resourceName = sectionName.toLowerCase().concat(displayPageNumber).replaceAll(" ", "");

        final int resNameId = curActivity.getResources().getIdentifier(resourceName, "raw", curActivity.getPackageName());

        Log.d(TAG, "ID fetched for " + resourceName + " -> " + resNameId);


        ImageButton pauseButton = (ImageButton) rootView.findViewById(R.id.imageButtonPause);
        setVisibility(resNameId, pauseButton);

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(curActivity, "Pausing media playback",
                        Toast.LENGTH_SHORT).show();

                ShlokaMediaPlayer.pause();
            }
        });

        ImageButton playButton = (ImageButton) rootView.findViewById(R.id.imageButtonPlay);
        setVisibility(resNameId, playButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (resNameId == 0) return;

                ShlokaMediaPlayer.setLoopCounter(getShlokaRepeatCount());

                String playStatus = ShlokaMediaPlayer.play(getActivity(), resNameId);

                if (!playStatus.isEmpty()) {

                    Toast.makeText(curActivity, playStatus,
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                Toast.makeText(curActivity, "Playing media",
                        Toast.LENGTH_SHORT).show();

            }
        });


        return rootView;
    }

    private int getShlokaRepeatCount() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(DataProvider.PREFS_NAME, 0);
        String repeatShloka = sharedPreferences.getString(DataProvider.REPEAT_SHLOKA, DataProvider.REPEAT_SHLOKA_DEFAULT);
        return Integer.valueOf(repeatShloka);
    }

    private void setVisibility(int resNameId, ImageButton pauseButton) {
        if (resNameId == 0)
            pauseButton.setVisibility(View.INVISIBLE);
        else
            pauseButton.setVisibility(View.VISIBLE);
    }

}
