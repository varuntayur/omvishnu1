package com.varun.omvishnu.app.home;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.model.home.Group;
import com.varun.omvishnu.app.indetail.ShlokaSlideActivity;

import java.io.Serializable;
import java.util.Collection;


public class OmActivity extends ActionBarActivity {

    private static DataProvider dataProvider;

    private SparseArray<Group> groups = new SparseArray<Group>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new DataProviderTask().execute(getAssets());

        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.om, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyExpandableListAdapter extends BaseExpandableListAdapter {

        private final SparseArray<Group> groups;
        public LayoutInflater inflater;
        public Activity activity;

        public MyExpandableListAdapter(Activity act, SparseArray<Group> groups) {
            activity = act;
            this.groups = groups;
            inflater = act.getLayoutInflater();
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return groups.get(groupPosition).children.get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            final String children = (String) getChild(groupPosition, childPosition);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.activity_main_listrow_details, null);
            }

            final TextView text = (TextView) convertView.findViewById(R.id.textView1);
            text.setText(children);

            convertView.setOnClickListener(mainMenuClickListener(groupPosition, children));
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return groups.get(groupPosition).children.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return groups.size();
        }

        @Override
        public void onGroupCollapsed(int groupPosition) {
            super.onGroupCollapsed(groupPosition);
        }

        @Override
        public void onGroupExpanded(int groupPosition) {
            super.onGroupExpanded(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.activity_main_listrow_group, null);
            }
            Group group = (Group) getGroup(groupPosition);
            ((CheckedTextView) convertView).setText(group.string);
            ((CheckedTextView) convertView).setChecked(isExpanded);
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        private View.OnClickListener mainMenuClickListener(final int groupPosition, final String children) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(activity, children,
                            Toast.LENGTH_SHORT).show();

                    if (groupPosition == MainMenuGroupName.SAHASRANAMA_EXPLAINED.ordinal()) {

                        Intent intent = new Intent(OmActivity.this, ShlokaSlideActivity.class);
                        intent.putExtra("sectionName", children);
                        intent.putExtra("shlokaList", (Serializable) DataProvider.getSahasranama().getSection(children).getShlokaList());
                        startActivity(intent);

                    }

                    if (groupPosition == MainMenuGroupName.BIRTH_STAR_SHLOKAS.ordinal()) {

                        System.out.println("nakshatra -> " + children);
                        System.out.println("shlokas -> " + DataProvider.getShlokasForBirthStar(children));

                        Intent intent = new Intent(OmActivity.this, ShlokaSlideActivity.class);
                        intent.putExtra("sectionName", children);
                        intent.putExtra("shlokaList", (Serializable) DataProvider.getShlokasForBirthStar(children));
                        startActivity(intent);

                        return;
                    }


                    if (groupPosition == MainMenuGroupName.AVATARA_SHLOKAS.ordinal()) {

                        System.out.println("avatara -> " + children);
                        System.out.println("shlokas -> " + DataProvider.getShlokaForAvatara(children));

                        Intent intent = new Intent(OmActivity.this, ShlokaSlideActivity.class);
                        intent.putExtra("sectionName", children);
                        intent.putExtra("shlokaList", (Serializable) DataProvider.getShlokaForAvatara(children));
                        startActivity(intent);

                        return;
                    }

                    if (groupPosition == MainMenuGroupName.NAMES_1000.ordinal()) {
                    }
                }
            };
        }
    }

    private class DataProviderTask extends AsyncTask<AssetManager, Void, Long> {

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Long result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    createMainMenu();
                }
            });
            System.out.println("Finished launching main-menu");

        }

        @Override
        protected Long doInBackground(AssetManager... assetManagers) {

            dataProvider = new DataProvider(getAssets());
            System.out.println("Finished background task execution");
            return 1l;
        }
    }

    private void createMainMenu() {

        final Collection<String> sectionNames = DataProvider.getSahasranama().getSectionNames();
        Group group = new Group(MainMenuGroupName.SAHASRANAMA_EXPLAINED.toString());
        for (String secName : sectionNames) {
            group.children.add(secName);
        }
        groups.append(MainMenuGroupName.SAHASRANAMA_EXPLAINED.ordinal(), group);

        final Collection<String> stars = DataProvider.getBirthStarToShloka().keySet();
        group = new Group(MainMenuGroupName.BIRTH_STAR_SHLOKAS.toString());
        for (String starName : stars) {
            group.children.add(starName);
        }
        groups.append(MainMenuGroupName.BIRTH_STAR_SHLOKAS.ordinal(), group);

        final Collection<String> dashavataras = DataProvider.getAvatara2Shlokas().keySet();
        group = new Group(MainMenuGroupName.AVATARA_SHLOKAS.toString());
        for (String dashavatara : dashavataras) {
            group.children.add(dashavatara);
        }
        groups.append(MainMenuGroupName.AVATARA_SHLOKAS.ordinal(), group);

        final Collection<String> sahasranamas = DataProvider.getThousandNames().getLstStringNamas();
        group = new Group(MainMenuGroupName.NAMES_1000.toString());
        for (String name : sahasranamas) {
            group.children.add(name);
        }
        groups.append(MainMenuGroupName.NAMES_1000.ordinal(), group);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        MyExpandableListAdapter adapter1 = new MyExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter1);
        listView.expandGroup(0);

    }

}


