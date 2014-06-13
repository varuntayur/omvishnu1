package com.varun.omvishnu.app.data;

import android.content.res.AssetManager;

import com.varun.omvishnu.app.data.model.names.ThousandNames;
import com.varun.omvishnu.app.data.model.sahasranama.Sahasranama;
import com.varun.omvishnu.app.data.model.sahasranama.Section;
import com.varun.omvishnu.app.data.model.sahasranama.Shloka;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    private static List<String> nakshatraList = new ArrayList<String>(Arrays.asList("ashwini", "bharani", "krithika", "rohini", "mrigashirsha", "ardra", "punarvasu", "pushya", "ashlesha", "magha", "purvaphalguni", "uttaraphalguni", "hasta", "chitra", "swati", "vishakha", "anuradha", "jyeshtha", "mula", "purvaashadha", "uttaraashadha", "shravana", "dhanishtha", "shatabhisha", "purvabhadrapada", "uttarabhadrapada", "revati"));

    private static Map<String, List<String>> nakshatraName2Shlokas = new ConcurrentHashMap<String, List<String>>();

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

            if (nakshatraName2Shlokas.isEmpty())
                buildNakshatraToShlokaMap();

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

    private static void buildNakshatraToShlokaMap() {
        Section sahasranama = getSahasranama().getSection("Sahasranama");
        List<Shloka> shlokas = sahasranama.getShlokaList();

        List<String> lstShlokas = new ArrayList<String>();
        String nakshatraName = "";
        Iterator<String> nakshatraListIter = nakshatraList.listIterator();

        for (int i = 0; i < shlokas.size(); i++) {

            if (i % 4 == 0) {
                lstShlokas = new ArrayList<String>();
                lstShlokas.add(shlokas.get(i).getFormattedShloka());
                nakshatraName = nakshatraListIter.next();
                nakshatraName2Shlokas.put(nakshatraName, lstShlokas);
                System.out.println("nakshatraName:" + nakshatraName + " -> " + " shloka: " + lstShlokas);
                continue;
            }

            lstShlokas.add(shlokas.get(i).getFormattedShloka());
            System.out.println("nakshatraName:" + nakshatraName + " -> " + " shloka: " + lstShlokas);
        }
    }
}
