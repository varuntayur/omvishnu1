package com.varun.omvishnu.app.data.model.avataras;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

/**
 * Created by varuntayur on 5/19/2014.
 */
@Root
public class Avataras implements Serializable {

    @ElementList(inline = true, name = "avatara")
    private List<Avatara> lstAvataras;

    public Avataras() {
    }

    public List<Avatara> getLstAvataras() {
        return lstAvataras;
    }

    public void setLstAvataras(List<Avatara> lstAvataras) {
        this.lstAvataras = lstAvataras;
    }
}
