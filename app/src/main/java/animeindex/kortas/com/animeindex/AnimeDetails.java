package animeindex.kortas.com.animeindex;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimeDetails extends AppCompatActivity {


    ImageView img ;
    TextView name ,description,rank;
    String NameFromIntent;
    Button b1,b2,b3 ;
    MyDBHandler dbHandler;
    Anime a = new Anime();
    CollapsingToolbarLayout collapsingToolbarLayout ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHandler = new MyDBHandler(this, null, null, 1);



// custom dialog
        final Dialog dialog = new Dialog(AnimeDetails.this);
        dialog.setContentView(R.layout.dialog_for_add_to_list);
        dialog.setTitle("Add Anime to my list");


        b1=(Button) dialog.findViewById(R.id.button1) ;
        b2=(Button) dialog.findViewById(R.id.button2) ;
        b3=(Button) dialog.findViewById(R.id.button3) ;

        // set the custom dialog components - text, image and button






        // if button is clicked, close the custom dialog



        NameFromIntent=getIntent().getStringExtra("name");
        a.setName(NameFromIntent);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View view) {

                    dialog.show();
    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            a.setUserStatus("Watching");
            dbHandler.addAnime(a);
            dialog.dismiss();

        }
    });

    b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            a.setUserStatus("To Watch");
            dbHandler.addAnime(a);
            dialog.dismiss();

        }
    });

    b3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            a.setUserStatus("Watched");
            dbHandler.addAnime(a);
            dialog.dismiss();

        }
    });


    Log.e("DBDump", dbHandler.databaseToString());

      /*    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




     /*   */



        img = (ImageView) findViewById(R.id.img)  ;
       rank =(TextView) findViewById(R.id.rank) ;
        description =(TextView) findViewById(R.id.desc) ;
       // rank.setText(Global.acAnime.getRank());

        collapsingToolbarLayout =(android.support.design.widget.CollapsingToolbarLayout) findViewById(R.id.toolbar_layout) ;

       // name.setText("dsfds");
        collapsingToolbarLayout.setTitle(this.getIntent().getStringExtra("name"));
      //  description.setText("qshdqklmshdjklqshdklqjshdjkl");
        description.setText(Global.acAnime.getDesc());






    }


}
