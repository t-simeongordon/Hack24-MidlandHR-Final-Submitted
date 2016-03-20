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
 * Created by terrelsimeongordon on 19/03/16.
 */
public class SelectionPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_page_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.nav_bar_title, null);

        actionBar.setCustomView(v);

        ColorDrawable cd = new ColorDrawable(0xff0000);
        actionBar.setBackgroundDrawable(cd);
    }
    public void showAll(View v){
        Intent i = new Intent(getApplicationContext(),DisplayMain.class);
        startActivity(i);

    }

    public void showSkill(View v){
        Intent i = new Intent(getApplicationContext(),SkillPage.class);
        startActivity(i);

    }

    public void showLocation(View v){
        Intent i = new Intent(getApplicationContext(),LocationPage.class);
        startActivity(i);

    }


}
