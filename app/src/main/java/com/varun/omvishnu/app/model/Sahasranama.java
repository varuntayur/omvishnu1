package com.varun.omvishnu.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by varuntayur on 4/5/2014.
 */
@Root
public class Sahasranama implements Parcelable {

    public Sahasranama(){}

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Sahasranama(Parcel in) {
        in.readList(sections, null);
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(sections);
    }

    public static final Parcelable.Creator<Sahasranama> CREATOR
            = new Parcelable.Creator<Sahasranama>() {
        public Sahasranama createFromParcel(Parcel in) {
            return new Sahasranama(in);
        }

        public Sahasranama[] newArray(int size) {
            return new Sahasranama[size];
        }
    };

}
