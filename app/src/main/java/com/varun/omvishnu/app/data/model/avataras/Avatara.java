package com.varun.omvishnu.app.data.model.avataras;

import com.varun.omvishnu.app.data.model.sahasranama.Shloka;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import java.io.Serializable;
import java.util.List;

/**
 * Created by varuntayur on 6/22/2014.
 */
public class Avatara implements Serializable {

    @Attribute(name = "name")
    private String name;

    @Attribute(name = "num")
    private String num;

    @ElementList(inline = true, name = "shloka")
    private List<Shloka> shlokas;

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

    public List<Shloka> getShlokas() {
        return shlokas;
    }

    public void setShlokas(List<Shloka> shlokas) {
        this.shlokas = shlokas;
    }
}
