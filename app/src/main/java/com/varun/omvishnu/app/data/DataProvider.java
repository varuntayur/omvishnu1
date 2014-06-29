package com.varun.omvishnu.app.data;

import android.content.res.AssetManager;

import com.varun.omvishnu.app.R;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by varuntayur on 5/18/2014.
 */
public class DataProvider {

    private AssetManager am;

    private static Sahasranama sahasranama;

    private static ThousandNames thousandNames;

    private static BirthStars birthstars;

    private static Avataras avataras;

    private static Map<String, List<Shloka>> nakshatraName2Shlokas = new HashMap<String, List<Shloka>>();

    private static Map<String, List<Shloka>> avatara2Shlokas = new HashMap<String, List<Shloka>>();

    private static Map<String, Integer> menuName2Resource = new HashMap<String, Integer>() {
        {
            put("History", R.drawable.history);
            put("Invocation", R.drawable.invocation);
            put("Dhyanam", R.drawable.dhyanam);
            put("Sahasranama", R.drawable.vishwaroopa);
            put("Phala Shruthi", R.drawable.phalashruthi);
            put("Mangala", R.drawable.mangala);

            put("Hasta", R.drawable.star);
            put("Anuradha", R.drawable.star);
            put("Pushya", R.drawable.star);
            put("Punarvasu", R.drawable.star);
            put("Revati", R.drawable.star);
            put("Uttaraphalguni", R.drawable.star);
            put("Jyeshtha", R.drawable.star);
            put("Uttarabhadrapada", R.drawable.star);
            put("Chitra", R.drawable.star);
            put("Krithika", R.drawable.star);
            put("Uttaraashadha", R.drawable.star);
            put("Purvaphalguni", R.drawable.star);
            put("Bharani", R.drawable.star);
            put("Dhanishtha", R.drawable.star);
            put("Rohini", R.drawable.star);
            put("Mula", R.drawable.star);
            put("Vishakha", R.drawable.star);
            put("Mrigashirsha", R.drawable.star);
            put("Purvabhadrapada", R.drawable.star);
            put("Ashwini", R.drawable.star);
            put("Shravana", R.drawable.star);
            put("Ardra", R.drawable.star);
            put("Swati", R.drawable.star);
            put("Magha", R.drawable.star);
            put("Purvaashadha", R.drawable.star);
            put("Shatabhisha", R.drawable.star);
            put("Ashlesha", R.drawable.star);

            put("Lakshmidhara", R.drawable.lakshminarayana);
            put("Mohini", R.drawable.mohini);
            put("Narasimha", R.drawable.narasimha);
            put("Koorma", R.drawable.koorma);
            put("Kalki", R.drawable.kalki);
            put("Rama", R.drawable.rama);
            put("Buddha", R.drawable.buddha);
            put("Vyuha Swarupa", R.drawable.lakshminarayana);
            put("Parashurama", R.drawable.parashurama);
            put("Matsya", R.drawable.matsya);
            put("Purusha Sukta Swarupa", R.drawable.purushasukta);
            put("Para Swarupa", R.drawable.paraswarupa);
            put("Varaha", R.drawable.varaha);
            put("Vamana", R.drawable.vamana);
            put("Krishna", R.drawable.krishna);

        }
    };

    public DataProvider(AssetManager am) {
        this.am = am;
        this.init();
    }

    public static Integer getMenuName2Resource(String menuName) {
        final Integer resourceName = menuName2Resource.get(menuName);

        if (resourceName != null)
            return resourceName;

        return R.drawable.vishwaroopa;
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
            List<Shloka> shlokaList = Collections.EMPTY_LIST;
            if (!starShlokas.isEmpty()) {

                int startIndex = Integer.parseInt(starShlokas.get(0).getNum());
                int lastIndex = Integer.parseInt(starShlokas.get(starShlokas.size() - 1).getNum());
                shlokaList = sahasranama.getShlokaList(startIndex, lastIndex);
            }

            nakshatraName2Shlokas.put(star.getName(), shlokaList);
        }
    }

    private void buildAvataraToShlokaMap() {

        Section sahasranama = getSahasranama().getSection("Sahasranama");

        for (Avatara avatara : avataras.getLstAvataras()) {

            final List<Shloka> avataraShlokas = avatara.getShlokas();

            final List<Shloka> shlokaList = new ArrayList<Shloka>();

            for (Shloka shloka : avataraShlokas) {
                shlokaList.add(sahasranama.getShloka(shloka.getNum()));
            }

            avatara2Shlokas.put(avatara.getName(), shlokaList);
        }
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
                buildAvataraToShlokaMap();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("* IOException de-serializing the file *" + e);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("* Exception de-serializing the file *" + e);
        }
    }
}
