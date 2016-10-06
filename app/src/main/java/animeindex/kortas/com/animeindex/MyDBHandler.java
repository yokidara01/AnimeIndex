package animeindex.kortas.com.animeindex;

/**
 * Created by Aladinne on 08/09/2016.
 */

// This class handles all the database activities
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "animeindex.db";
    public static final String TABLE_ANIMES = "animes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ANIMESNAME = "animename";
    public static final String imgurl= "imgurl" ;
    public static final String id="id";
    public static final String rank= "rank";
    public static final String userStatus ="userStatus" ;
    public static final String desc ="desc" ;
    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_ANIMES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                id + " INTEGER , " +
                rank + " INTEGER, " +
                imgurl + " TEXT, " +
                COLUMN_ANIMESNAME + " TEXT ," +
                desc + " TEXT ," +
                userStatus+ " TEXT"+
                ");";
        db.execSQL(query);
    }
    //Lesson 51
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIMES);
        onCreate(db);
    }

    //Add a new row to the database
    public void addAnime(Anime anime){
        ContentValues values = new ContentValues();
        values.put(id, anime.getId());
        values.put(rank, anime.getRank());
        values.put(desc, anime.getDesc());
        values.put(imgurl, anime.getImg());
        values.put(COLUMN_ANIMESNAME, anime.getName());
        values.put(userStatus, anime.getUserStatus());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ANIMES, null, values);
        db.close();
    }

    //Delete a product from the database
    public void deleteAnime(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_ANIMES + " WHERE " + id + "=" + id +";");
    }

    // this is goint in record_TextView in the Main activity.
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ANIMES + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("animename")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("animename"));
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }


    public List<Anime> getAllAnime(){
        List<Anime> listAnime = new ArrayList<>();
        Anime a ; 
        
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ANIMES + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("animename")) != null) {
               // dbString += recordSet.getString(recordSet.getColumnIndex("animename"));
               // dbString += "\n";
                a = new Anime() ;
                a.setName(recordSet.getString(recordSet.getColumnIndex("animename")));
                a.setUserStatus(recordSet.getString(recordSet.getColumnIndex("userStatus")));
                listAnime.add(a);
            }
            recordSet.moveToNext();
        }
        db.close();
        return listAnime;
    }

}