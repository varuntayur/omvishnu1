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

    private static Map<String, List<String>> nakshatraName2Shlokas = new ConcurrentHashMap<String, List<String>>();

    private static Map<String, List<String>> dashavatara2Shlokas = new ConcurrentHashMap<String, List<String>>();

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

            if (dashavatara2Shlokas.isEmpty())
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

    public static Map<String, List<String>> getBirthStarToShloka() {
        return nakshatraName2Shlokas;
    }

    public static Map<String, List<String>> getDashavatara2Shlokas() {
        return dashavatara2Shlokas;
    }

    private void buildNakshatraToShlokaMap() {

        Section sahasranama = getSahasranama().getSection("Sahasranama");

        for (Star star : birthstars.getLstStars()) {

            final List<Shloka> starShlokas = star.getShlokas();
            final List<Shloka> shlokaList = sahasranama.getShlokaList(Integer.parseInt(starShlokas.get(0).getNum()), Integer.parseInt(starShlokas.get(3).getNum()));

            final List<String> stringShlokas = new ArrayList<String>();
            for (Shloka shloka : shlokaList) {
                stringShlokas.add(shloka.getFormattedShloka());
            }
            nakshatraName2Shlokas.put(star.getName(), stringShlokas);
        }
    }

    private void buildDashavataraToShlokaMap() {

        Section sahasranama = getSahasranama().getSection("Sahasranama");

        for (Avatara avatara : avataras.getLstAvataras()) {

            final List<Shloka> avataraShlokas = avatara.getShlokas();
            final List<Shloka> shlokaList = sahasranama.getShlokaList(Integer.parseInt(avataraShlokas.get(0).getNum()), Integer.parseInt(avataraShlokas.get(3).getNum()));

            final List<String> stringShlokas = new ArrayList<String>();
            for (Shloka shloka : shlokaList) {
                stringShlokas.add(shloka.getFormattedShloka());
            }
            dashavatara2Shlokas.put(avatara.getName(), stringShlokas);
        }

    }
}
