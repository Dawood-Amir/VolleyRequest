package com.dawoodamir.volleyrequest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private static final String URL = "http://www.google.com";
    public static final String DOMAIN_URL = "https://domainaction.000webhostapp.com/CleanerAppJson_dryClean.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        // volley();
        jsonObject();

    }

    void jsonObject() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                DOMAIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            StringBuffer buffer = new StringBuffer();
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("movies");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                                /*
                                ///////////////////
                                for getting whole json
                                buffer.append(jsonObject1.toString());
                                ////////////////////
                                for getting one object
                                buffer.append(jsonObject1.getString("clothesname")+"\n");
                                /////////////////////////////////////////////////////////
                                for getting whole json and with formated make a recyclerview
                                */

                                buffer.append(jsonObject1.getString("clothesname") + "\n");
                            }
                            tv.setText(buffer);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                tv.setText(error.getLocalizedMessage());
            }
        });
        requestQueue.add(stringRequest);

    }

    void volley() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        tv.setText(getString(R.string.responce) + response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setText(R.string.didntWork);
            }
        });

        requestQueue.add(stringRequest);

    }
}
