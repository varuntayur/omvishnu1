package com.varun.omvishnu.app.data;

import android.content.res.AssetManager;

import com.varun.omvishnu.app.data.model.avataras.Avatara;
import com.varun.omvishnu.app.data.model.avataras.Avataras;
import com.varun.omvishnu.app.data.model.birthstars.BirthStars;
import com.varun.omvishnu.app.data.model.birthstars.Star;
import com.varun.omvishnu.app.data.model.names.ThousandNames;
import com.varun.omvishnu.app.data.model.sahasranama.Sahasranama;
import com.varun.omvishnu.app.data.model.sahasranama.Section;
import com.varun.omvishnu.app.data.model.sahasranama.Shloka;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by varuntayur on 5/18/2014.
 */
public class DataProvider {

    private AssetManager am;

    private static Sahasranama sahasranama;

    private static ThousandNames thousandNames;

    private static BirthStars birthstars;

    private static Avataras avataras;

    private static Map<String, List<Shloka>> nakshatraName2Shlokas = new ConcurrentHashMap<String, List<Shloka>>();

    private static Map<String, List<Shloka>> avatara2Shlokas = new ConcurrentHashMap<String, List<Shloka>>();

    public DataProvider(AssetManager am) {
        this.am = am;
        this.init();
    }

    private void init() {

        Serializer serializer = new Persister();
        InputStream inputStream = null;
        try {
            inputStream = am.open("db/sahasranama.xml");
            serializer = new Persister();
            sahasranama = serializer.read(Sahasranama.class, inputStream);
            System.out.println("* Finished de-serializing the file - sahasranama.xml *");

            inputStream = am.open("db/names.xml");
            serializer = new Persister();
            thousandNames = serializer.read(ThousandNames.class, inputStream);
            System.out.println("* Finished de-serializing the file - thousandNames.xml *");

            inputStream = am.open("db/stars.xml");
            serializer = new Persister();
            birthstars = serializer.read(BirthStars.class, inputStream);
            System.out.println("* Finished de-serializing the file - stars.xml *");

            inputStream = am.open("db/avataras.xml");
            serializer = new Persister();
            avataras = serializer.read(Avataras.class, inputStream);
            System.out.println("* Finished de-serializing the file - avataras.xml *");

            if (nakshatraName2Shlokas.isEmpty())
                buildNakshatraToShlokaMap();

            if (avatara2Shlokas.isEmpty())
                buildDashavataraToShlokaMap();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("* IOException de-serializing the file *" + e);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("* Exception de-serializing the file *" + e);
        }
    }

    public static Sahasranama getSahasranama() {
        return sahasranama;
    }

    public static ThousandNames getThousandNames() {
        return thousandNames;
    }

    public static Map<String, List<Shloka>> getBirthStarToShloka() {
        return nakshatraName2Shlokas;
    }

    public static List<Shloka> getShlokasForBirthStar(String name) {
        return new ArrayList<Shloka>(nakshatraName2Shlokas.get(name));
    }

    public static Map<String, List<Shloka>> getAvatara2Shlokas() {
        return avatara2Shlokas;
    }

    public static List<Shloka> getShlokaForAvatara(String name) {
        return new ArrayList<Shloka>(avatara2Shlokas.get(name));
    }

    private void buildNakshatraToShlokaMap() {

        Section sahasranama = getSahasranama().getSection("Sahasranama");

        for (Star star : birthstars.getLstStars()) {

            final List<Shloka> starShlokas = star.getShlokas();
            int startIndex = 0;
            int lastIndex = starShlokas.size();
            final List<Shloka> shlokaList = sahasranama.getShlokaList(startIndex, lastIndex);

            nakshatraName2Shlokas.put(star.getName(), shlokaList);
        }
    }

    private void buildDashavataraToShlokaMap() {

        Section sahasranama = getSahasranama().getSection("Sahasranama");

        for (Avatara avatara : avataras.getLstAvataras()) {

            final List<Shloka> avataraShlokas = avatara.getShlokas();
            int startIndex = 0;
            int lastIndex = avataraShlokas.size();
            final List<Shloka> shlokaList = sahasranama.getShlokaList(startIndex, lastIndex);

            avatara2Shlokas.put(avatara.getName(), shlokaList);
        }

    }
}
