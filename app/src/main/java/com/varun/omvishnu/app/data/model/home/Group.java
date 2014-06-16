package com.varun.omvishnu.app.data.model.home;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by varuntayur on 6/14/2014.
 */
public class Group {

    public String string;
    public final List<String> children = new ArrayList<String>();

    public Group(String string) {
        this.string = string;
    }

}
