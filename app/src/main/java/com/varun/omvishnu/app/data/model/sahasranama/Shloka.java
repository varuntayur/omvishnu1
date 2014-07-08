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


    public static final String EMPTY_STRING = "";
    @Attribute
    private String num;

    @Element(required = false)
    private String text;

    @Element(required = false)
    private ShlokaDescription explanation;

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
        if (text != null)
            return text.replaceAll("[^\\S\\r\\n]+", " ");
        return EMPTY_STRING;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setExplanation(ShlokaDescription explanation) {
        this.explanation = explanation;
    }

    public ShlokaDescription getExplanation() {
        return this.explanation;
    }

    public String getFormattedExplanation() {

        StringBuilder stringBuilder = new StringBuilder();
        for (Note note : explanation.getNotesList()) {
            stringBuilder.append(note.getFormattedNote());
        }
        return stringBuilder.toString();
    }
}
