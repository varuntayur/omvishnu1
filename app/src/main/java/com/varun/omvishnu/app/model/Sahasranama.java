package com.varun.omvishnu.app.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by varuntayur on 4/5/2014.
 */
@Root
public class Sahasranama {

    @ElementList(inline = true, name = "section")
    List<Section> sections;

    @Override
    public String toString() {
        return "Sahasranama{" +
                "sections=" + sections +
                '}';
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
