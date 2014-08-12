package com.varun.omvishnu.app.home;

import android.app.Activity;
import android.content.Intent;

import com.varun.omvishnu.app.data.DataProvider;
import com.varun.omvishnu.app.data.model.sahasranama.Section;
import com.varun.omvishnu.app.data.model.sahasranama.Shloka;
import com.varun.omvishnu.app.detail.AvatarasActivity;
import com.varun.omvishnu.app.detail.BirthstarsActivity;
import com.varun.omvishnu.app.detail.SahasranamaShlokaSlideActivity;
import com.varun.omvishnu.app.detail.ShlokaSlideActivity;
import com.varun.omvishnu.app.detail.ThousandNamesActivity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by varuntayur on 6/21/2014.
 */
public enum SahasranamaMenu {

    IN_BRIEF("Quick glance", "") {
        @Override
        public void execute(Activity activity, String item, int position) {
            Intent intent = new Intent(activity, ThousandNamesActivity.class);
            intent.putExtra("menuPosition", position);
            activity.startActivity(intent);
        }
    }, DEEP_DIVE("Complete Sahasranama explanation", "Sahasranama") {
        @Override
        public void execute(Activity activity, String item, int position) {
            Intent intent = new Intent(activity, SahasranamaShlokaSlideActivity.class);
            intent.putExtra("sectionName", SahasranamaMenu.DEEP_DIVE.getMenuDisplayKey());
            intent.putExtra("menuPosition", position);
            intent.putExtra("shlokaList", (Serializable) new ArrayList<Shloka>());
            activity.startActivity(intent);
        }
    }, BY_BIRTH_STAR("For your Birth Star", "") {
        @Override
        public void execute(Activity activity, String item, int position) {
            Intent intent = new Intent(activity, BirthstarsActivity.class);
            intent.putExtra("menuPosition", position);
            activity.startActivity(intent);
        }
    }, BY_AVATARA("By Avathara", "") {
        @Override
        public void execute(Activity activity, String item, int position) {
            Intent intent = new Intent(activity, AvatarasActivity.class);
            intent.putExtra("menuPosition", position);
            activity.startActivity(intent);
        }
    }, DEFAULT("Default", "") {
        @Override
        public void execute(Activity activity, String item, int position) {
            Intent intent = new Intent(activity, ShlokaSlideActivity.class);
            intent.putExtra("sectionName", item);
            intent.putExtra("menuPosition", position);

            Section section = DataProvider.getSahasranama().getSection(item);

            if (section == null) return;

            intent.putExtra("shlokaList", (Serializable) section.getShlokaList());
            activity.startActivity(intent);
        }
    };

    private String menuDisplayName;
    private String menuDisplayKey;

    SahasranamaMenu(String menu, String key) {
        this.menuDisplayName = menu;
        this.menuDisplayKey = key;
    }

    public static SahasranamaMenu getEnum(String item) {
        for (SahasranamaMenu v : values())
            if (v.toString().equalsIgnoreCase(item)) return v;
        return SahasranamaMenu.DEFAULT;
    }

    @Override
    public String toString() {
        return menuDisplayName;
    }

    public String getMenuDisplayKey() {
        return menuDisplayKey;
    }

    public abstract void execute(Activity activity, String item, int position);
}
