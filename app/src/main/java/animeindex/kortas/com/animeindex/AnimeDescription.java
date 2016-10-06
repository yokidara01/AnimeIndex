package animeindex.kortas.com.animeindex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AnimeDescription extends AppCompatActivity {

    public TextView name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_description);
            name= (TextView) findViewById(R.id.name) ;

        name.setText(this.getIntent().getStringExtra("name")) ;

    }
}
