package com.varun.omvishnu.app.detail;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by varuntayur on 7/2/2014.
 */
public class ThousandNamesFragment extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2"};
    }

}
