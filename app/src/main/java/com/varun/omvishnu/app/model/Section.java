package com.varun.omvishnu.app.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by varuntayur on 4/5/2014.
 */
@Root
public class Section {


    @Attribute(name = "name")
    private String name;

    @ElementList(inline = true, name = "shloka")
    private List<Shloka> shlokaList;

    private Map<String, Shloka> mapShlName2Shl = new HashMap<String, Shloka>();

    public Section() {
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                ", shlokaList=" + shlokaList +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Shloka> getShlokaList() {
        return shlokaList;
    }

    public void setShlokaList(List<Shloka> shlokaList) {
        this.shlokaList = shlokaList;
    }

    public Shloka getShloka(String shlokaNum) {

        if (mapShlName2Shl.keySet().isEmpty())
            for (Shloka shloka : this.shlokaList) {
                mapShlName2Shl.put(shloka.getNum(), shloka);
            }

        return mapShlName2Shl.get(shlokaNum);
    }

}
