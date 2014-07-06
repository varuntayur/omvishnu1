package com.varun.omvishnu.app.data.adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.model.home.Group;
import com.varun.omvishnu.app.detail.AvatarasActivity;
import com.varun.omvishnu.app.detail.BirthstarsActivity;
import com.varun.omvishnu.app.detail.ShlokaSlideActivity;
import com.varun.omvishnu.app.detail.ThousandNamesActivity;
import com.varun.omvishnu.app.home.SahasranamaMenuGroupName;

import java.io.Serializable;

/**
 * Created by varuntayur on 7/2/2014.
 */
public class MyExpandableListAdapter extends BaseExpandableListAdapter {

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
        text.setCompoundDrawablesWithIntrinsicBounds(DataProvider.getMenuName2Resource(children), 0, 0, 0);

        convertView.setOnClickListener(mainMenuClickListener(groupPosition, children, text));
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
        final CheckedTextView checkedView = (CheckedTextView) convertView;
        checkedView.setText(group.string);
        checkedView.setChecked(isExpanded);
//            checkedView.setCompoundDrawablesWithIntrinsicBounds(isExpanded ? 0 : android.R.drawable.ic_menu_more, 0, isExpanded ? 0 : android.R.drawable.ic_menu_more, 0);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private View.OnClickListener mainMenuClickListener(final int groupPosition, final String children, View view) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(activity, children,
                        Toast.LENGTH_SHORT).show();

                if (SahasranamaMenuGroupName.DEEP_DIVE.toString().equalsIgnoreCase(children)) {

                    Intent intent = new Intent(activity, ShlokaSlideActivity.class);
                    intent.putExtra("sectionName", children);
                    intent.putExtra("shlokaList", (Serializable) DataProvider.getSahasranama().getSection(SahasranamaMenuGroupName.DEEP_DIVE.getMenuDisplayKey()).getShlokaList());
                    activity.startActivity(intent);

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
