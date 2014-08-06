package com.varun.omvishnu.app.data;

import android.content.res.AssetManager;

import com.varun.omvishnu.app.R;
import com.varun.omvishnu.app.data.model.avataras.AvShloka;
import com.varun.omvishnu.app.data.model.avataras.Avatara;
import com.varun.omvishnu.app.data.model.avataras.Avataras;
import com.varun.omvishnu.app.data.model.birthstars.BirthStars;
import com.varun.omvishnu.app.data.model.birthstars.Star;
import com.varun.omvishnu.app.data.model.names.ThousandNames;
import com.varun.omvishnu.app.data.model.sahasranama.Note;
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
public final class DataProvider {

    private static Sahasranama sahasranama;

    private static ThousandNames thousandNames;

    private static BirthStars birthstars;

    private static Avataras avataras;

    private static Map<String, List<Shloka>> nakshatraName2Shlokas = new HashMap<String, List<Shloka>>();

    private static Map<String, List<Shloka>> avatara2Shlokas = new HashMap<String, List<Shloka>>();

    private static List<Integer> mBackgroundColors = new ArrayList<Integer>() {
        {
            add(R.color.orange);
            add(R.color.green);
            add(R.color.blue);
            add(R.color.yellow);
            add(R.color.grey);
            add(R.color.lblue);
            add(R.color.slateblue);
            add(R.color.cyan);
            add(R.color.silver);
        }
    };

    private static Map<String, Integer> menuName2Resource = new HashMap<String, Integer>() {
        {
//            put("History", R.drawable.history);
//            put("Invocation", R.drawable.invocation);
//            put("Dhyanam", R.drawable.dhyanam);

//            put(SahasranamaMenuGroupName.SAHASRANAMA_MENU_NAME.toString(), R.drawable.vishwaroopa);
//            put(SahasranamaMenuGroupName.IN_BRIEF.toString(), R.drawable.inbrief);
//            put(SahasranamaMenuGroupName.BY_AVATARA.toString(), R.drawable.krishna);
//            put(SahasranamaMenuGroupName.DEEP_DIVE.toString(), R.drawable.vishwaroopa);
//            put(SahasranamaMenuGroupName.BY_BIRTH_STAR.toString(), R.drawable.star);

//            put("Phala Shruthi", R.drawable.phalashruthi);
//            put("Mangala", R.drawable.mangala);

            put("Hasta", R.drawable.kanya);
            put("Anuradha", R.drawable.vrischika);
            put("Pushya", R.drawable.kataka);
            put("Punarvasu", R.drawable.mithuna);
            put("Revati", R.drawable.meena);
            put("Uttaraphalguni", R.drawable.kanya);
            put("Jyeshtha", R.drawable.vrischika);
            put("Uttarabhadrapada", R.drawable.dhanu);
            put("Chitra", R.drawable.tula);
            put("Krithika", R.drawable.mesha);
            put("Uttaraashadha", R.drawable.makara);
            put("Purvaphalguni", R.drawable.kumbha);
            put("Bharani", R.drawable.mesha);
            put("Dhanishtha", R.drawable.makara);
            put("Rohini", R.drawable.vrishabha);
            put("Mula", R.drawable.dhanu);
            put("Vishakha", R.drawable.vrischika);
            put("Mrigashirsha", R.drawable.vrishabha);
            put("Purvabhadrapada", R.drawable.kumbha);
            put("Shravana", R.drawable.makara);
            put("Ardra", R.drawable.mithuna);
            put("Swati", R.drawable.tula);
            put("Magha", R.drawable.simha);
            put("Purvaashadha", R.drawable.dhanu);
            put("Shatabhisha", R.drawable.kumbha);
            put("Ashlesha", R.drawable.kataka);
            put("Ashwini", R.drawable.mesha);

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
            put("Vyasa", R.drawable.vyasa);
            put("Vatapatra", R.drawable.vatapatra);

        }
    };

    private DataProvider() {
    }

    public static void init(AssetManager am) {

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

//            printDetailedSahasranamaNames();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("* IOException de-serializing the file *" + e);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("* Exception de-serializing the file *" + e);
        }
    }

    public static List<Integer> getBackgroundColorList() {
        return Collections.unmodifiableList(mBackgroundColors);
    }

    private static void printDetailedSahasranamaNames() {

        List<Shloka> lstShlokas = getSahasranama().getSection("Sahasranama").getShlokaList();
        int i = 1;
        for (Shloka shloka : lstShlokas) {
            List<Note> lstNotes = shloka.getExplanation().getNotesList();
            for (Note note : lstNotes) {
                System.out.println(i + " " + note.getTitle());
                i++;
            }
        }
    }

    public static Integer getDrawableResourceForMenu(String menuName) {
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

    public static int getBackgroundColor(int location) {
        return mBackgroundColors.get(location);
    }

    private static void buildNakshatraToShlokaMap() {

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

    private static void buildAvataraToShlokaMap() {

        Section sahasranama = getSahasranama().getSection("Sahasranama");

        for (Avatara avatara : avataras.getLstAvataras()) {

            final List<AvShloka> avataraShlokas = avatara.getAvShlokas();

            final List<Shloka> shlokaList = new ArrayList<Shloka>();

            for (AvShloka shloka : avataraShlokas) {
                shlokaList.add(sahasranama.getShloka(shloka.getNum()));
            }

            avatara2Shlokas.put(avatara.getName(), shlokaList);
        }
    }


}
