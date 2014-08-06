package com.varun.omvishnu.app.home;

/**
 * Created by varuntayur on 6/21/2014.
 */
public enum SahasranamaMenuGroupName {

    SAHASRANAMA_MENU_NAME("Sahasranama", ""), IN_BRIEF("Quick glance", ""), DEEP_DIVE("Complete Sahasranama explanation", "Sahasranama"), BY_BIRTH_STAR("For your Birth Star", ""), BY_AVATARA("By Avathara", "");

    private String menuDisplayName;
    private String menuDisplayKey;

    SahasranamaMenuGroupName(String menu, String key) {
        this.menuDisplayName = menu;
        this.menuDisplayKey = key;
    }

    @Override
    public String toString() {
        return menuDisplayName;
    }

    public String getMenuDisplayKey() {
        return menuDisplayKey;
    }
}
