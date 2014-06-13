package com.varun.omvishnu.app.data.model.sahasranama;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by varuntayur on 4/5/2014.
 */
@Root
public class Shloka {


    @Attribute
    private String num;

    @Element(required = false)
    private String text;

    @Element(required = false)
    private String explanation;

    public Shloka() {
    }

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

    public String getFormattedShloka() {
        return String.format("%s\n", text);
    }
}
