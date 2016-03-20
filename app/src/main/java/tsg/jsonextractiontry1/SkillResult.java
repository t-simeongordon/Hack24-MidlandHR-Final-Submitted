


package tsg.jsonextractiontry1;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.drawable.ColorDrawable;
        import android.os.Bundle;
        import android.os. AsyncTask;

        import android.app.Activity;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ListView;

        import org.json.JSONArray;
        import org.json.JSONObject;
        import org.json.JSONException;

        import java.util.ArrayList;
        import java.util.List;

public class SkillResult extends AppCompatActivity {
    private static String urlString;
    // Declare Variables
    ListView listview;
    ProgressDialog mProgressDialog;
    MainListViewAdapter adapter;
    private List<OrganisationProFolder> organisationProList = null;
    String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        s1 = i.getStringExtra("s1");
//        final TextView tv = (TextView) findViewById(R.id.tv);
//        Button btn = (Button) findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tv.setText("");
        urlString = "https://zymb.eu/api/v1/employee/skill/";

        if(s1!=null) {
            urlString = "https://zymb.eu/api/v1/employee/skill/"+s1;

            new ProcessJSON().execute(urlString);
        }
//            }
//        });
    }

    private class ProcessJSON extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(SkillResult.this);
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
            organisationProList = new ArrayList<OrganisationProFolder>();

//            TextView tv = (TextView) findViewById(R.id.tv);
            //tv.setText(stream);

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
                String data = "";

                Log.e("enter1 ","******* ");
                try{
                    Log.e("enter2 ","******* ");

                    // Get the full HTTP Data as JSONObject
                    JSONObject reader= new JSONObject(stream);
//                    JSONObject reader = (JSONObject) new JSONTokener(stream).nextValue();
                    Log.e("check json reader ","******* "+reader);

                    // Get the JSONObject "coord"...........................
//                    JSONArray coord = reader.optJSONArray("movies");
                    JSONArray coord = reader.optJSONArray("Employees");

                    Log.e("check json array ","******* "+reader);


                    for(int i=0; i < coord.length(); i++){
                        OrganisationProFolder map = new OrganisationProFolder();

                        JSONObject jsonObject = coord.getJSONObject(i);

//                        int year1 = Integer.parseInt(jsonObject.optString("year").toString());
//                        String name = jsonObject.optString("movie").toString();
//                        int year1 = Integer.parseInt(jsonObject.optString("id").toString());
//                        String name = jsonObject.optString("first_name").toString();

                        map.setUserId(jsonObject.optString("id").toString());
                        map.setName(jsonObject.optString("first_name").toString());
                        map.setSkill(jsonObject.optString("skill_title").toString());
//                        map.setName((String) DoggieRecipes.get("user"));
//                        map.setPhoto(image.getUrl());
                        organisationProList.add(map);



                        Log.e("what happened ", "DoggieRecipeslist " + organisationProList);

                        Log.e("what happened ", "DoggieRecipeslist size" + organisationProList.size());

//                        data += "year= "+ year1 +" \n Name= "+ name +" \n ";
                    }
                    mProgressDialog.dismiss();
                    listview = (ListView) findViewById(R.id.listview);
                    // Pass the results into _ListViewAdapter.java
                    adapter = new MainListViewAdapter(SkillResult.this,
                            organisationProList);
                    // Binds the Adapter to the ListView
                    listview.setAdapter(adapter);
//                    tv.setText(data);

                }catch(JSONException e){
                    e.printStackTrace();
                }

            } // if statement end
        } // onPostExecute() end
    } // ProcessJSON class end
} // Activity end