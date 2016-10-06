package animeindex.kortas.com.animeindex;

/**
 * Created by Aladinne on 28/08/2016.
 */


import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class XMLPullParserHandler {

    List<Anime> animes;

    private Anime anime;
    private String text;

    public XMLPullParserHandler() {
        animes = new ArrayList<Anime>();
    }

    public List<Anime> getEmployees() {
        return animes;
    }

    public List<Anime> parse(InputStream is) {

        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;

        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);

            parser = factory.newPullParser();
            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:

                        if(tagname.equalsIgnoreCase("anime")) {
                            anime = new Anime();
                        }
                        break;

                    case XmlPullParser.TEXT:

                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:

                        if(tagname.equalsIgnoreCase("anime")) {

                            animes.add(anime);
                        }else if(tagname.equalsIgnoreCase("name")) {
                            anime.setName(text);
                         // Log.d("MyApp", text);

                        }else if(tagname.equalsIgnoreCase("description")) {
                            anime.setDesc(text);
                        }
                else if(tagname.equalsIgnoreCase("id")) {
                    anime.setId(Integer.parseInt(text));
                }
                        else if(tagname.equalsIgnoreCase("rank")) {
                            anime.setId(Integer.parseInt(text));
                        }






                /*else if(tagname.equalsIgnoreCase("department")) {
                            employee.setDepartment(text);
                        }else if(tagname.equalsIgnoreCase("email")) {
                            employee.setEmail(text);
                        }else if(tagname.equalsIgnoreCase("type")) {
                            employee.setType(text);
                        }*/
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return animes;
    }
}
