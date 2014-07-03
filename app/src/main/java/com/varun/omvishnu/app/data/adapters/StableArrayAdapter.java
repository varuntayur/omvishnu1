package com.varun.omvishnu.app.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.DataProvider;

import java.util.List;

/**
 * Created by varuntayur on 6/8/2014.
 */

public class StableArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final List<String> values;

    public StableArrayAdapter(Context context, List<String> values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        final String text = values.get(position);
        textView.setText(text);
        textView.setCompoundDrawablesWithIntrinsicBounds(DataProvider.getMenuName2Resource(text), 0, 0, 0);

        return rowView;
    }
}