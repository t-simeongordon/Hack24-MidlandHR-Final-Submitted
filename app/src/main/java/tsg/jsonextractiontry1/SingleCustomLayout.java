package tsg.jsonextractiontry1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by terrelsimeongordon on 19/03/16.
 */
public class SingleCustomLayout extends AppCompatActivity {

    private static String urlString;
    // Declare Variables
    ListView listview;
    ProgressDialog mProgressDialog;
    ListViewSingleAdapter adapter;
    private List<OrganisationProFolder> allOrganisationProList = null;

String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_custom_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.nav_bar_title, null);

        actionBar.setCustomView(v);

        ColorDrawable cd = new ColorDrawable(0xff0000);
        actionBar.setBackgroundDrawable(cd);
        Intent i = getIntent();
        // Get the result of title
        user_id = i.getStringExtra("userID");
Log.e("user id"," number "+user_id);
        urlString = "https://zymb.eu/api/v1/employee/";
        Log.e("url "," number "+urlString);
        urlString = "https://zymb.eu/api/v1/employee/"+user_id;
        Log.e("new url "," number "+urlString);

        new ProcessJSON().execute(urlString);

    }

    private class ProcessJSON extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Create a progressdialog
            mProgressDialog = new ProgressDialog(SingleCustomLayout.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Organise PRO");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }



        protected String doInBackground(String... strings){
            String stream = null;
            String urlString = strings[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            stream = hh.GetHTTPData(urlString);

            // Return the data from specified url
            return stream;
        }

        protected void onPostExecute(String stream){
            allOrganisationProList = new ArrayList<OrganisationProFolder>();

           final TextView firstNameTV = (TextView) findViewById(R.id.first_name_json);
            final TextView lastNameTV = (TextView) findViewById(R.id.last_name_json);
            final TextView jobTitleTV = (TextView) findViewById(R.id.job_title_json);
            final TextView ageTV = (TextView) findViewById(R.id.user_age_json);
            final TextView skillsTV = (TextView) findViewById(R.id.user_skills_json);

            /*
                Important in JSON DATA
                -------------------------
                * Square bracket ([) represents a JSON array
                * Curly bracket ({) represents a JSON object
                * JSON object contains key/value pairs
                * Each key is a String and value may be different data types
             */

            //..........Process JSON DATA................
            Log.e("enter ", "******* " + stream);

            if(stream !=null){

                Log.e("enter1 ","******* ");
                try{
                    Log.e("enter2 ","******* ");

                    // Get the full HTTP Data as JSONObject
                    JSONObject reader= new JSONObject(stream);

                    Log.e("check json reader ","******* "+reader);

                    // Get the JSONObject "coord"...........................

                    JSONObject coord = reader.optJSONObject("Employee");
                    Log.e("check json OBJECT ","******* "+coord);


                    String name = coord.getString("first_name");
                    String jobTitle = coord.getString("job_title");
                    String id = coord.getString("id");
                    String last_name = coord.getString("last_name");
                    String age = coord.getString("age");
//                    String skills = coord.getString("skills");


                    Log.e("check json OBJECT 1 ", "******* " + coord);

if (name!=null && jobTitle!=null && id !=null && last_name!=null && age!=null) {
    firstNameTV.setText(name);
    lastNameTV.setText(last_name);
    jobTitleTV.setText(jobTitle);
    ageTV.setText(age);
//    skillsTV.setText(skills);

}
                    mProgressDialog.dismiss();


                }catch(JSONException e){
                    e.printStackTrace();
                }

            } // if statement end
        } // onPostExecute() end
    } // ProcessJSON class end
} // Activity end



