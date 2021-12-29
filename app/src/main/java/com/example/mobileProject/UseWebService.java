package com.example.mobileProject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UseWebService extends Activity {
    private static final String TAG = "FAIL";
    //URL json hosting
    String JsonURL = "https://api-mobile.000webhostapp.com/news.php";

    // Mendefiniskan Volley request queue yang akan menangani URL request
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // membuat volley request queue
        requestQueue = Volley.newRequestQueue(this);
        // Membuat objek JsonArrayRequest class dengan nama arrayreq,
        //JsonURL adalah URL yang akan diambil datanya
        JsonArrayRequest arrayreq = new JsonArrayRequest(JsonURL,
                //parameter kedua adalah Listener overrides dengan method //onResponse() dan melewatkan JSONArray sebagai parameter

                new Response.Listener<JSONArray>() {
                    //mengambil response dari JSON request
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            LinearLayout linearLayout = new LinearLayout(UseWebService.this);
                            setContentView(linearLayout);
                            linearLayout.setOrientation(LinearLayout.VERTICAL);
                            for (int i = 0; i < response.length(); i++) {
                                //mengambil tiap JSON object didalam JSON array
                                JSONObject jsonObject = response.getJSONObject(i);
                                // menyimpan ke dalam string dengan nama "idemp" and "namaemp",
                                // dan convert dalam bentuk javascript objects
                                String title = jsonObject.getString("title");
                                String news = jsonObject.getString("news");
                                TextView textView = new TextView(UseWebService.this);
                                textView.setText(title+"\n"+news+"\n\n");
                                linearLayout.addView(textView);
                            }
                        }
                        catch (JSONException e) {
                            Log.d(TAG, "onResponse: ERROR CUG");
                            // jika ada error json, print ke log
                            e.printStackTrace();
                        }
                    }
                },
                // parameter terakhir overrides adalah method onErrorResponse() dan
                // melewatkan VolleyError sebagai sebuah parameter
                new Response.ErrorListener() {
                    @Override
                    // handle error yang disebabkan Volley
                    public void onErrorResponse(VolleyError error){
                        Log.e("Volley", "Error");
                    }
                }
        );
// Add JSON array request "arrayreq" ke request queue
        requestQueue.add(arrayreq);
    }
}