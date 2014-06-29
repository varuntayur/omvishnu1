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

package com.varun.omvishnu.app.indetail;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
                .inflate(R.layout.fragment_screen_slide_page, container, false);

        ((TextView) rootView.findViewById(R.id.sectiontitle)).setText(sectionName);

        TextView viewById = (TextView) rootView.findViewById(R.id.shlokatext);

        viewById.setTypeface(devanagariTf);

        final List<Shloka> shlokaList = shlokas;

        viewById.setText(shlokaList.get(mPageNumber).getText());

        viewById = (TextView) rootView.findViewById(R.id.shlokaexplanation);
        viewById.setText(shlokaList.get(mPageNumber).getExplanation());

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return shlokas.size();
    }
}
