package com.example.travelling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button btnRequest;
    private String output="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://run.mocky.io/v3/8f71ce66-9edf-4ebf-8c4f-7b67015c4936";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for(int i=0;i<response.length();i++) {
                        JSONObject Canzoni = response.getJSONObject(i);
                        // now we get our response from API in json object format.
                        // in below line we are extracting a string with its key
                        // value from our json object.
                        // similarly we are extracting all the strings from our json object.
                        String autore = Canzoni.getString("Autore");
                        String titolo = Canzoni.getString("Titolo");
                        output = output + "Titolo: "+titolo+ " | Autore: "+autore+" ";
                    }

                    Toast t = Toast.makeText(getApplicationContext(),output,Toast.LENGTH_LONG);
                    t.show();
                } catch (JSONException e) {
                    // if we do not extract data from json object properly.
                    // below line of code is use to handle json exception
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            // this is the error listener method which
            // we will call if we get any error from API.
            @Override
            public void onErrorResponse(VolleyError error) {
                // below line is use to display a toast message along with our error.
                Toast.makeText(MainActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        // at last we are adding our json
        // object request to our request
        // queue to fetch all the json data.
        queue.add(jsonArrayRequest);


    }
}