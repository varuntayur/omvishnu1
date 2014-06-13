package com.varun.omvishnu.app.data.model.names;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by varuntayur on 5/19/2014.
 */
@Root
public class Nama {

    @Element(required = true)
    private String name;

    @Element(required = true)
    private String meaning;

    public Nama() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return name + " - " + meaning;
    }
}
