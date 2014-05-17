package com.varun.omvishnu.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by varuntayur on 4/5/2014.
 */
@Root
public class Section implements Parcelable {

    public Section() {
    }

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Section(Parcel in) {
        in.readList(shlokaList, null);
        name = in.readString();
    }

    @Attribute(name = "name")
    private String name;

    @ElementList(inline = true, name = "shloka")
    private List<Shloka> shlokaList;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(shlokaList);
        parcel.writeString(name);
    }


    public static final Parcelable.Creator<Section> CREATOR
            = new Parcelable.Creator<Section>() {
        public Section createFromParcel(Parcel in) {
            return new Section(in);
        }

        public Section[] newArray(int size) {
            return new Section[size];
        }
    };

}
