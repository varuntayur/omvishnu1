package com.varun.omvishnu.app.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by varuntayur on 4/5/2014.
 */
@Root
public class Sahasranama {

    @ElementList(inline = true, name = "section")
    List<Section> sections;

    private Map<String, Section> mapSecName2Sec = new HashMap<String, Section>();

    public Sahasranama() {
    }

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

    public Section getSection(String sectionName) {

        if (mapSecName2Sec.keySet().isEmpty())
            for (Section section : sections) {
                mapSecName2Sec.put(section.getName(), section);
            }

        return mapSecName2Sec.get(sectionName);
    }

}
