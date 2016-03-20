package tsg.jsonextractiontry1;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by terrelsimeongordon on 20/03/16.
 */
public class SplashPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.nav_bar_title, null);

        actionBar.setCustomView(v);

        ColorDrawable cd = new ColorDrawable(0xff0000);
        actionBar.setBackgroundDrawable(cd);

        Thread timer = new Thread() {

            public void run() {

                try {

                    synchronized(this){
                        wait(3000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                        Intent openString = new Intent(SplashPage.this, SelectionPage.class);
                        startActivity(openString);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                }
            }

        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
