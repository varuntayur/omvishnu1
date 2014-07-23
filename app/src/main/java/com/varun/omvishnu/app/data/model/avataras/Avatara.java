package com.varun.omvishnu.app.data.model.avataras;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by varuntayur on 6/22/2014.
 */
public class Avatara implements Serializable {

    @Attribute(name = "name")
    private String name;

    @Attribute(name = "num")
    private String num;

    @Element(name = "note", required = false)
    private String note;

    @ElementList(inline = true, entry = "avshloka", name = "avshloka", required = false)
    private List<AvShloka> avShlokas;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AvShloka> getAvShlokas() {

        if (avShlokas == null) return Collections.EMPTY_LIST;

        return avShlokas;
    }

    public void setAvShlokas(List<AvShloka> shlokas) {
        this.avShlokas = shlokas;
    }
}
