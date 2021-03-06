package com.varun.omvishnu.app.detail;


import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.etsy.android.grid.util.DynamicHeightTextView;
import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.DataProvider;

import java.util.List;
import java.util.Random;

/**
 * ADAPTER
 */

public class SampleAdapter extends ArrayAdapter<String> {

    private static final String TAG = "SampleAdapter";
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    private final LayoutInflater mLayoutInflater;
    private final Random mRandom;
    private final List<Integer> mBackgroundColors;

    public SampleAdapter(final Context context, final int textViewResourceId) {
        super(context, textViewResourceId);
        mLayoutInflater = LayoutInflater.from(context);
        mRandom = new Random();
        mBackgroundColors = DataProvider.getBackgroundColorList();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_sample, parent, false);
            vh = new ViewHolder();
            vh.txtLineOne = (DynamicHeightTextView) convertView.findViewById(R.id.txt_line1);
//            vh.btnGo = (Button) convertView.findViewById(R.id.btn_go);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        double positionHeight = getPositionRatio(position);
        int backgroundIndex = position >= mBackgroundColors.size() ?
                position % mBackgroundColors.size() : position;

        convertView.setBackgroundResource(mBackgroundColors.get(backgroundIndex));

        Log.d(TAG, "getView position:" + position + " h:" + positionHeight);

//        vh.txtLineOne.setHeightRatio(positionHeight);
        vh.txtLineOne.setHeightRatio(1.5);
        vh.txtLineOne.setText(getItem(position));

//        vh.btnGo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                Toast.makeText(getContext(), "Button Clicked Position " +
//                        position, Toast.LENGTH_SHORT).show();
//            }
//        });

        return convertView;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        // if not yet done generate and stash the columns height
        // in our real world scenario this will be determined by
        // some match based on the known height and width of the image
        // and maybe a helpful way to get the column height!
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5 the width
    }

    static class ViewHolder {
        DynamicHeightTextView txtLineOne;
        Button btnGo;
    }
}