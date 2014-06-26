package com.varun.omvishnu.app.data.model.sahasranama;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by varuntayur on 4/5/2014.
 */
@Root
public class Shloka implements Serializable {


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
        return text.replaceAll("[^\\S\\r\\n]+", " ");
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getExplanation() {
        return explanation.replaceAll("[^\\S\\r\\n]+", " ");
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getFormattedShlokaText() {
        return String.format("%s\n", getText());
    }

    public String getFormattedShloka() {
        return String.format("%s\n%s\n", getText(), getExplanation());
    }
}
