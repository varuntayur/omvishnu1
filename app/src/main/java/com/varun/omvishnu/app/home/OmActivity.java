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
import com.varun.omvishnu.app.birthstars.BirthStarsShlokaFragment;
import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.model.home.Group;
import com.varun.omvishnu.app.history.HistoryFragment;
import com.varun.omvishnu.app.indetail.ScreenSlideActivity;

import java.util.Collection;


public class OmActivity extends ActionBarActivity {

    private static DataProvider dataProvider;

    // more efficient than HashMap for mapping integers to objects
    SparseArray<Group> groups = new SparseArray<Group>();

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void setupLaunchMenu() {
//        final ListView listview = (ListView) findViewById(R.id.listSahasranamaSections);
//        final StableArrayAdapter adapter = new StableArrayAdapter(this,
//                android.R.layout.simple_list_item_1, dataProvider.getSectionNames());
//        listview.setAdapter(adapter);
//
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view,
//                                    int position, long id) {
//                final String item = (String) parent.getItemAtPosition(position);
//                view.animate().setDuration(100).alpha(0)
//                        .withEndAction(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                adapter.notifyDataSetChanged();
//
//                                launchActivity();
//
//                                view.setAlpha(1);
//                            }
//
//                            private void launchActivity() {
//
//                                if (item.equals("ThousandNames")) {
//
//                                    Intent intent = new Intent(OmActivity.this, ThousandNamesFragment.class);
//                                    startActivity(intent);
//
//                                } else if (item.equals("ThousandNamesByBirthStars")) {
//
//                                    Intent intent = new Intent(OmActivity.this, BirthStarsFragment.class);
//                                    startActivity(intent);
//
//                                } else if (item.equals("ThousandNamesByGods")) {
//
//                                    Intent intent = new Intent(OmActivity.this, ThousandNamesFragment.class);
//                                    startActivity(intent);
//
//                                } else if (item.equals("History")) {
//
//                                    Intent intent = new Intent(OmActivity.this, HistoryFragment.class);
//                                    startActivity(intent);
//
//                                } else {
//
//                                    Intent intent = new Intent(OmActivity.this, ScreenSlideActivity.class);
//                                    intent.putExtra("sectionName", item);
//                                    startActivity(intent);
//
//                                }
//                            }
//                        });
//            }
//
//        });
//    }

    private class DataProviderTask extends AsyncTask<AssetManager, Void, Long> {

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Long result) {
            runOnUiThread(new Runnable() {
                public void run() {
                    createData();
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

    public void createData() {

        final Collection<String> sectionNames = DataProvider.getSahasranama().getSectionNames();
        Group group = new Group("Sahasranama Explained");
        for (String secName : sectionNames) {
            group.children.add(secName);
        }
        groups.append(0, group);

        final Collection<String> stars = DataProvider.getBirthStarToShloka().keySet();
        group = new Group("Birth Star Shlokas");
        for (String secName : stars) {
            group.children.add(secName);
        }
        groups.append(1, group);

        final Collection<String> dashavataras = DataProvider.getDashavataraList();
        group = new Group("Dashaavathara Shlokas");
        for (String dashavatara : dashavataras) {
            group.children.add(dashavatara);
        }
        groups.append(2, group);

        final Collection<String> sahasranamas = DataProvider.getThousandNames().getLstStringNamas();
        group = new Group("1000 Names");
        for (String name : sahasranamas) {
            group.children.add(name);
        }
        groups.append(3, group);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        MyExpandableListAdapter adapter1 = new MyExpandableListAdapter(this,
                groups);
        listView.setAdapter(adapter1);


//        final ListView listview = (ListView) findViewById(R.id.listSahasranamaSections);
//        final List<String> list = new ArrayList<String>();
//        list.add("ThousandNames");
//        final StableArrayAdapter adapter = new StableArrayAdapter(this,
//                android.R.layout.simple_list_item_1, list);
//        listview.setAdapter(adapter);

//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view,
//                                    int position, long id) {
//                final String item = (String) parent.getItemAtPosition(position);
//                view.animate().setDuration(100).alpha(0)
//                        .withEndAction(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                adapter.notifyDataSetChanged();
//
//                                launchActivity();
//
//                                view.setAlpha(1);
//                            }
//
//                            private void launchActivity() {
//
//                                if (item.equals("ThousandNames")) {
//
//                                    Intent intent = new Intent(OmActivity.this, ThousandNamesFragment.class);
//                                    startActivity(intent);
//
//                                } else if (item.equals("ThousandNamesByBirthStars")) {
//
//                                    Intent intent = new Intent(OmActivity.this, BirthStarsFragment.class);
//                                    startActivity(intent);
//
//                                } else if (item.equals("ThousandNamesByGods")) {
//
//                                    Intent intent = new Intent(OmActivity.this, ThousandNamesFragment.class);
//                                    startActivity(intent);
//
//                                } else if (item.equals("History")) {
//
//                                    Intent intent = new Intent(OmActivity.this, HistoryFragment.class);
//                                    startActivity(intent);
//
//                                } else {
//
//                                    Intent intent = new Intent(OmActivity.this, ScreenSlideActivity.class);
//                                    intent.putExtra("sectionName", item);
//                                    startActivity(intent);
//
//                                }
//                            }
//                        });
//            }
//
//        });


    }

    class MyExpandableListAdapter extends BaseExpandableListAdapter {

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
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity, children,
                            Toast.LENGTH_SHORT).show();

                    if (groupPosition == 1) {
                        System.out.println("nakshatra -> " + children);
                        System.out.println("shlokas -> " + DataProvider.getBirthStarToShloka().get(children));

                        Intent intent = new Intent(OmActivity.this, BirthStarsShlokaFragment.class);
                        intent.putExtra("nakshatraName", children);
                        startActivity(intent);
                    } else if (children.equals("History")) {

                        Intent intent = new Intent(OmActivity.this, HistoryFragment.class);
                        startActivity(intent);

                    } else {

                        Intent intent = new Intent(OmActivity.this, ScreenSlideActivity.class);
                        intent.putExtra("sectionName", children);
                        startActivity(intent);

                    }
                }
            });
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
    }


}


