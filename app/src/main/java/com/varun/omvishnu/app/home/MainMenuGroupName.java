package com.varun.omvishnu.app.home;

/**
 * Created by varuntayur on 6/21/2014.
 */
public enum MainMenuGroupName {

    SAHASRANAMA_EXPLAINED("Sahasranama Explained"), BIRTH_STAR_SHLOKAS("Birth Star Shlokas"), AVATARA_SHLOKAS("Avathara Shlokas"), NAMES_1000("1000 Names");

    private String menu;

    MainMenuGroupName(String menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return menu;
    }


}
