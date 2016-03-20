package tsg.jsonextractiontry1;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by terrelsimeongordon on 20/03/16.
 */
public class LocationPage extends AppCompatActivity {
EditText searchMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_page_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.nav_bar_title, null);

        actionBar.setCustomView(v);

        ColorDrawable cd = new ColorDrawable(0xff0000);
        actionBar.setBackgroundDrawable(cd);
        searchMain = (EditText) findViewById(R.id.search1);

    }

    public void ShowEmployees(View v) {
        String s1 = searchMain.getText().toString();

        Intent i = new Intent(getApplicationContext(), LocationResult.class);

        i.putExtra("s1", s1);

        startActivity(i);

    }
}
