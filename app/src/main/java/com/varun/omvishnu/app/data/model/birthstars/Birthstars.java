package com.varun.omvishnu.app.data.model.birthstars;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

/**
 * Created by varuntayur on 5/19/2014.
 */
@Root
public class BirthStars implements Serializable {

    @ElementList(inline = true, name = "star")
    private List<Star> lstStars;

    public BirthStars() {
    }

    public List<Star> getLstStars() {
        return lstStars;
    }

    public void setLstStars(List<Star> lstStars) {
        this.lstStars = lstStars;
    }

}
