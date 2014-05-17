package com.varun.omvishnu.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by varuntayur on 4/5/2014.
 */
@Root
public class Shloka implements Parcelable {

    public Shloka() {
    }

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Shloka(Parcel in) {
        num = in.readString();
        text = in.readString();
        explanation = in.readString();
    }

    @Attribute
    private String num;

    @Element(required = false)
    private String text;

    @Element(required = false)
    private String explanation;

    @Override
    public String toString() {
        return "Shloka{" +
                "num='" + num + '\'' +
                ", text='" + text + '\'' +
                ", explanation=" + explanation +
                '}';
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text);
        parcel.writeString(explanation);
        parcel.writeString(num);
    }

    public static final Parcelable.Creator<Shloka> CREATOR
            = new Parcelable.Creator<Shloka>() {
        public Shloka createFromParcel(Parcel in) {
            return new Shloka(in);
        }

        public Shloka[] newArray(int size) {
            return new Shloka[size];
        }
    };

}
