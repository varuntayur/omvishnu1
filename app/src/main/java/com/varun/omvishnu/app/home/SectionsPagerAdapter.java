package com.varun.omvishnu.app.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.adapters.StableArrayAdapter;
import com.varun.omvishnu.app.data.model.sahasranama.Shloka;
import com.varun.omvishnu.app.detail.AvatarasActivity;
import com.varun.omvishnu.app.detail.BirthstarsActivity;
import com.varun.omvishnu.app.detail.SahasranamaShlokaSlideActivity;
import com.varun.omvishnu.app.detail.ShlokaSlideActivity;
import com.varun.omvishnu.app.detail.ThousandNamesActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by varuntayur on 7/14/2014.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a DummySectionFragment (defined as a static inner class
        // below) with the page number as its lone argument.
        Fragment fragment = new SectionFragment();
        Bundle args = new Bundle();
        args.putInt(SectionFragment.ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return context.getString(R.string.title_section1);
            case 1:
                return context.getString(R.string.title_section2);
            case 2:
                return context.getString(R.string.title_section3);
        }
        return null;
    }

    public static class SectionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";

        public SectionFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            final int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

            final List<String> sectionNames = new ArrayList<String>(DataProvider.getSahasranama().getSectionNames());
            int indexOfSahasranama = sectionNames.indexOf("Sahasranama");

            switch (sectionNumber) {
                case 0:
                    View rootView = inflater.inflate(R.layout.fragment_dhyanam, container, false);

                    ListView listViewSection1 = (ListView) rootView.findViewById(R.id.listdhyanam);

                    List<String> sectionNamesPart1 = sectionNames.subList(0, indexOfSahasranama);

                    StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), sectionNamesPart1);
                    listViewSection1.setAdapter(adapter);

                    listViewSection1.setOnItemClickListener(getOnMenuClickListener(getActivity().getBaseContext()));
                    return rootView;

                case 1:
                    List<String> secNames = Arrays.asList(SahasranamaMenuGroupName.IN_BRIEF.toString(), SahasranamaMenuGroupName.DEEP_DIVE.toString(), SahasranamaMenuGroupName.BY_BIRTH_STAR.toString(), SahasranamaMenuGroupName.BY_AVATARA.toString());

                    rootView = inflater.inflate(R.layout.fragment_sahasranama, container, false);

                    ListView listViewSection2 = (ListView) rootView.findViewById(R.id.listsahasranama);
                    adapter = new StableArrayAdapter(getActivity(), secNames);
                    listViewSection2.setAdapter(adapter);

                    listViewSection2.setOnItemClickListener(getMainMenuClickListener(getActivity().getBaseContext()));

                    return rootView;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_phala_shruthi, container, false);
                    ListView listViewSection3 = (ListView) rootView.findViewById(R.id.listphalashruthi);
                    List<String> sectionNamesPart2 = sectionNames.subList(indexOfSahasranama + 1, sectionNames.size());
                    adapter = new StableArrayAdapter(getActivity(), sectionNamesPart2);
                    listViewSection3.setAdapter(adapter);

                    listViewSection3.setOnItemClickListener(getOnMenuClickListener(getActivity().getBaseContext()));
                    return rootView;
            }

            return null;
        }

        private AdapterView.OnItemClickListener getOnMenuClickListener(final Context ctx) {
            return new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item = (String) parent.getAdapter().getItem(position);

                    Toast.makeText(ctx, item,
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), ShlokaSlideActivity.class);
                    intent.putExtra("sectionName", item);
                    intent.putExtra("shlokaList", (Serializable) DataProvider.getSahasranama().getSection(item).getShlokaList());
                    startActivity(intent);
                }
            };
        }

        private AdapterView.OnItemClickListener getMainMenuClickListener(final Context ctx) {

            return new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String children = (String) parent.getAdapter().getItem(position);

                    Toast.makeText(ctx, children,
                            Toast.LENGTH_SHORT).show();

                    Activity activity = getActivity();


                    Toast.makeText(activity, children,
                            Toast.LENGTH_SHORT).show();

                    if (SahasranamaMenuGroupName.DEEP_DIVE.toString().equalsIgnoreCase(children)) {

                        Intent intent = new Intent(activity, SahasranamaShlokaSlideActivity.class);
                        intent.putExtra("sectionName", SahasranamaMenuGroupName.DEEP_DIVE.getMenuDisplayKey());
                        intent.putExtra("shlokaList", (Serializable) new ArrayList<Shloka>());
                        activity.startActivity(intent);

                        return;
                    }

                    if (SahasranamaMenuGroupName.IN_BRIEF.toString().equalsIgnoreCase(children)) {

                        Intent intent = new Intent(activity, ThousandNamesActivity.class);
                        activity.startActivity(intent);

                        return;
                    }

                    if (SahasranamaMenuGroupName.BY_BIRTH_STAR.toString().equalsIgnoreCase(children)) {

                        Intent intent = new Intent(activity, BirthstarsActivity.class);
                        activity.startActivity(intent);

                        return;
                    }


                    if (SahasranamaMenuGroupName.BY_AVATARA.toString().equalsIgnoreCase(children)) {

                        Intent intent = new Intent(activity, AvatarasActivity.class);
                        activity.startActivity(intent);

                        return;
                    }

                }
            };

        }

    }
}