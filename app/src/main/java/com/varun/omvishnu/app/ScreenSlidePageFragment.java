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

package com.varun.omvishnu.app;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.varun.omvishnu.app.model.Sahasranama;
import com.varun.omvishnu.app.model.Section;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 * <p/>
 * <p>This class is used by the {} and {@link
 * ScreenSlideActivity} samples.</p>
 */
public class ScreenSlidePageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    private static Typeface devanagariTf;

    private Sahasranama sahasranama;

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    public ScreenSlidePageFragment(Typeface tf, Sahasranama sahasranama, int position) {
        this.sahasranama = sahasranama;
        this.devanagariTf = tf;
        this.mPageNumber = position;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_slide_page, container, false);

        Section section = sahasranama.getSections().get(mPageNumber);

        ((TextView) rootView.findViewById(R.id.sectiontitle)).setText(
                section.getName());

        TextView viewById = (TextView) rootView.findViewById(R.id.shlokatext);
        viewById.setTypeface(devanagariTf);
        viewById.setText(section.getShlokaList().get(mPageNumber).getText());

        viewById = (TextView) rootView.findViewById(R.id.shlokaexplanation);
        viewById.setText(section.getShlokaList().get(mPageNumber).getText());

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
