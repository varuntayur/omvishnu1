package com.varun.omvishnu.app.data.model.avataras;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import java.io.Serializable;

/**
 * Created by varuntayur on 4/5/2014.
 */
@Element
public class AvShloka implements Serializable {


    public static final String EMPTY_STRING = "";
    @Attribute
    private String num;

    @Attribute(required = false)
    private String names;

    public AvShloka() {
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
