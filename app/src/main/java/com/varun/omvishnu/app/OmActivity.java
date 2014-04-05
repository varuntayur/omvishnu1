package com.varun.omvishnu.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.varun.omvishnu.app.model.Sahasranama;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;
import java.io.InputStream;


public class OmActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_om);
//
//        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/droidsansdevanagari.ttf");
//        TextView tv = (TextView) findViewById(R.id.hello_world);
//        tv.setTypeface(tf);
//        tv.setText(R.string.vishnu);

        Serializer serializer = new Persister();
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("db/db.xml");
            serializer = new Persister();
            Sahasranama sahasranama = serializer.read(Sahasranama.class, inputStream);
            System.out.println("*****" + sahasranama);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.om, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startActivity(View view) {
        Intent intent = new Intent(OmActivity.this, ScreenSlideActivity.class);
        startActivity(intent);
    }

}

//try {
//        InputStream inputStream = getAssets().open("db/example.xml");
//        Serializer serializer = new Persister();
////            Example example = serializer.read(Example.class, inputStream);
////            System.out.println(example);
//
//        inputStream = getAssets().open("db/db.xml");
//        serializer = new Persister();
//        Sahasranama sahasranama = serializer.read(Sahasranama.class, inputStream);
//        System.out.println("*****" + sahasranama);
//
//        Shloka shloka = new Shloka();
//        shloka.setNum("1");
//        shloka.setText("test");
//        shloka.setExplanation("exp");
//        Section section = new Section();
//        section.setName("test1");
//
//        List<Shloka> lstShlokas = new ArrayList<Shloka>();
//        lstShlokas.add(shloka);
//        section.setShlokaList(lstShlokas);
//
//        List<Section> lstSections = new ArrayList<Section>();
//        lstSections.add(section);
//        Sahasranama sah = new Sahasranama();
//        sah.setSections(lstSections);
//
//        File file = new File(getCacheDir() + "/dump.xml");
//        OutputStream outputStream = new FileOutputStream(file);
//        serializer.write(sah, outputStream);
//
////            System.out.println(sahasranama);
//        System.out.println(file.getAbsolutePath());
//
//        BufferedReader is1 = new BufferedReader(new FileReader(file));
//        String line = null;
//        while ((line = is1.readLine()) != null)
//        System.out.println(line);
//
//        is1.close();
//
//        } catch (Exception e) {
//        e.printStackTrace();
//        }

