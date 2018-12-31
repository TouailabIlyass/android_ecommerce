package com.lollipop.deve.commerce.Models;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lollipop.deve.commerce.R;
import com.lollipop.deve.commerce.Res;

import org.json.JSONException;
import org.json.JSONObject;

public class ValideMailActivity extends AppCompatActivity {

    private TextView status;
    private EditText code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valide_mail);

        Button send,exit;


        send = findViewById(R.id.btnvalideenvoyer);
        exit = findViewById(R.id.btnvalideexit);
        code = findViewById(R.id.txtvalidecode);
        status = findViewById(R.id.txtvalidestatus);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonRequestGetClient(getIntent().getIntExtra("id",0),Integer.parseInt(code.getText().toString()));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private   void jsonRequestGetClient(int id,int code)
    {
        RequestQueue mRequestQueue;
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        JSONObject json = new JSONObject();
        try {
            json.put("id",id);
            json.put("code",code);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = Res.URL+"/client/valide";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            boolean st=response.getBoolean("isvalide");
                            if(st == false)
                              status.setVisibility(View.VISIBLE);
                            else if(st==true)
                            {
                                status.setVisibility(View.INVISIBLE);
                                finish();
                            }

                        }catch (Exception e)
                        {

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(jsonObjectRequest);
    }
}
