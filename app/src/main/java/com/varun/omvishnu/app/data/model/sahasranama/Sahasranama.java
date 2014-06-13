package com.varun.omvishnu.app.data.model.sahasranama;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by varuntayur on 4/5/2014.
 */
@Root
public class Sahasranama {

    @ElementList(inline = true, name = "section")
    List<Section> sections;

    private Map<String, Section> mapSecName2Sec = new LinkedHashMap<String, Section>();

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

        buildMap();

        return mapSecName2Sec.get(sectionName);
    }

    public Collection<String> getSectionNames() {

        buildMap();

        return mapSecName2Sec.keySet();
    }

    private void buildMap() {
        if (mapSecName2Sec.keySet().isEmpty())
            for (Section section : sections) {
                mapSecName2Sec.put(section.getName(), section);
            }
    }

}
