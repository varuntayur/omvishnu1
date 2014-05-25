package com.varun.omvishnu.app;

import android.content.res.AssetManager;

import com.varun.omvishnu.app.model.names.ThousandNames;
import com.varun.omvishnu.app.model.sahasranama.Sahasranama;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by varuntayur on 5/18/2014.
 */
public class DataProvider {

    private AssetManager am;

    private static Sahasranama sahasranama;

    private static ThousandNames thousandNames;

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
}
