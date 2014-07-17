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
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.varun.omvishnu.app.R;
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
    private static Typeface devanagariTf;

    private String sectionName;

    private List<Shloka> shlokas;

    private int mPageNumber;

    private MediaPlayer mediaPlayer;

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
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_shloka_slide_page, container, false);

        final Activity curActivity = this.getActivity();

        ((TextView) rootView.findViewById(R.id.sectiontitle)).setText(sectionName);

        final Shloka shloka = shlokas.get(mPageNumber);

        TextView shlokaText = (TextView) rootView.findViewById(R.id.shlokatext);
        shlokaText.setTypeface(devanagariTf);
        shlokaText.setText(shloka.getText());
        shlokaText.setTypeface(shlokaText.getTypeface(), Typeface.BOLD);

        WebView shlokaExplanation = (WebView) rootView.findViewById(R.id.shlokaexplanation);
        shlokaExplanation.loadData(shloka.getFormattedExplanation(), "text/html", null);

        ImageButton pauseButton = (ImageButton) rootView.findViewById(R.id.imageButtonPause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(curActivity, "Pausing sound",
                        Toast.LENGTH_SHORT).show();

                mediaPlayer.pause();
            }
        });

        ImageButton playButton = (ImageButton) rootView.findViewById(R.id.imageButtonPlay);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String resourceName = sectionName.toLowerCase().concat(String.valueOf(mPageNumber + 1));

                final int resName = curActivity.getResources().getIdentifier(resourceName, "raw", curActivity.getPackageName());

                System.out.println("ID fetched for " + resourceName + " -> " + resName);

                Toast.makeText(curActivity, "Playing sound",
                        Toast.LENGTH_SHORT).show();
                mediaPlayer = MediaPlayer.create(getActivity(), resName);
                mediaPlayer.start();

            }
        });


        return rootView;
    }

    @Override
    public void onStop() {

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            System.out.println("************ Attempting to stop media if it is playing *********");
            mediaPlayer.pause();
            System.out.println("************ Pause media was successful *********");
        }

        super.onStop();
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return shlokas.size();
    }
}
