package animeindex.kortas.com.animeindex;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashScreen extends Activity {
TextView tv ;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tv =(TextView) findViewById(R.id.tvsplash) ;

        
            if(this.firstUse()){
                tv.setText("Welcom to first USE !!");
                MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);
                myDBHandler.LeadAllAnimeFromXml();
            }else
            {
                tv.setText("NO FIRST ");
            }




        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private boolean firstUse() {

        MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);


        return myDBHandler.isFirstUse() ;
    }

}