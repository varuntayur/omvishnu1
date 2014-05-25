package com.varun.omvishnu.app.model.names;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by varuntayur on 5/19/2014.
 */
@Root
public class ThousandNames {

    @ElementList(inline = true, name = "shloka")
    private List<Nama> lstNamas;

    public ThousandNames() {
    }

    public List<Nama> getLstNamas() {
        return lstNamas;
    }

    public List<String> getLstStringNamas() {
        List<String> lstStrNamas = new ArrayList<String>(lstNamas.size());
        for (Nama nama : lstNamas) {
            lstStrNamas.add(nama.toString());
        }
        return lstStrNamas;
    }

    public void setLstNamas(List<Nama> lstNamas) {
        this.lstNamas = lstNamas;
    }

}
