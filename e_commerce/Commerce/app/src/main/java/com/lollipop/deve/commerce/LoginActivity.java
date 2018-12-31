package com.lollipop.deve.commerce;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lollipop.deve.commerce.Models.Clients;
import com.lollipop.deve.commerce.Models.Likes;
import com.lollipop.deve.commerce.Models.ValideMailActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    Button log_btn,inscrire;
    EditText name;
    EditText pass;
    ProgressDialog progressDialog;
    Clients tmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tmp = null;

        log_btn = findViewById(R.id.loginbtn);
        inscrire = findViewById(R.id.sinscrirebtn);
        name = findViewById(R.id.usernameLogintxt);
        pass = findViewById(R.id.passLogintxt);
        progressDialog = new ProgressDialog(this);
        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Dologin d=  new Dologin();
               d.execute("");
            }
        });

        inscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,UserActivity.class));
            }
        });
    }
    private class Dologin extends AsyncTask<String, String, String> implements Runnable{
        String em;
        boolean isSuccess;
        String namestr;
        String nm;
        String passstr;
        String f11z;
        Thread t1;

        public void run()
        {
                while(tmp == null)
                {
                    try {
                        t1.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        }

        private Dologin() {
            this.namestr = LoginActivity.this.name.getText().toString();
            this.passstr = LoginActivity.this.pass.getText().toString();
            this.f11z = "";
            this.isSuccess = false;
            tmp = null;
        }

        protected void onPreExecute() {
            LoginActivity.this.progressDialog.setMessage("Loading...");
            LoginActivity.this.progressDialog.show();
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            if (this.namestr.trim().equals("") || this.passstr.trim().equals("")) {
                this.f11z = "Please enter all fields....";
            }
          //  if (1 != 2) {
                try{
                    t1 = new Thread(this);
                    t1.start();
                    tmp=jsonRequestGetClient(new Clients(0,"","","","","","",namestr,passstr,"",false));
                    while(t1.isAlive())
                        Thread.sleep(1000);
                    if (tmp.getId()!=0) {
                        this.isSuccess = true;
                        this.f11z = "Login successfull";
                        //Res.clients=c;
                        //Database database = new Database(getApplicationContext());
                        //database.deleteClient(null);
                        //database.addClient(tmp);
                        //
                        if(!tmp.isIsvalide()) {
                            Intent i = new Intent(LoginActivity.this, ValideMailActivity.class);
                            i.putExtra("id",tmp.getId());
                            startActivityForResult(i, 2);
                        }else
                        {
                            Database database = new Database(getApplicationContext());
                            database.deleteClient(null);
                            database.addClient(tmp);
                        }

                    } else {
                        this.isSuccess = false;
                    }
                } catch (Exception ex) {
                    this.isSuccess = false;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Exceptions");
                    stringBuilder.append(ex);
                    this.f11z = stringBuilder.toString();
                }
           //}
            return this.f11z;
        }
        protected void onPostExecute(String s) {
            if (this.isSuccess && tmp.isIsvalide()) {
                finish();
            }
            LoginActivity.this.progressDialog.hide();
        }

        /*protected void onPostExecute(String s) {
            Context baseContext = LoginActivity.this.getBaseContext();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(this.f11z);
            Toast.makeText(baseContext, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
            if (this.isSuccess) {
               // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
               // intent.putExtra("name", this.namestr);
               // LoginActivity.this.startActivity(intent);
                finish();
            }
            LoginActivity.this.progressDialog.hide();
        }*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Database database = new Database(getApplicationContext());
        database.deleteClient(null);
        database.addClient(tmp);
        finish();
    }

    private   Clients jsonRequestGetClient(Clients c)
    {
        final Clients clients = new Clients();
        RequestQueue mRequestQueue;
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());

        JSONObject json = new JSONObject();
        try {
            json.put("id",c.getId());
            json.put("nom",c.getNom());
            json.put("prenom",c.getPrenom());
            json.put("cin",c.getCin());
            json.put("addr",c.getAddr());
            json.put("ville",c.getVille());
            json.put("tel",c.getTel());
            json.put("username",c.getUsername());
            json.put("password",c.getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = Res.URL+"/client/login";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            //Toast.makeText(getApplicationContext(),"tt"+response.toString(),Toast.LENGTH_LONG).show();
                            clients.setId(response.getInt("id"));
                            clients.setNom(response.getString("nom"));
                            clients.setPrenom(response.getString("prenom"));
                            clients.setCin(response.getString("cin"));
                            clients.setAddr(response.getString("addr"));
                            clients.setVille(response.getString("ville"));
                            clients.setTel(response.getString("tel"));
                            clients.setUsername(response.getString("username"));
                            clients.setPassword(response.getString("password"));
                            clients.setMail(response.getString("mail"));
                            clients.setIsvalide(response.getBoolean("isvalide"));
                           // Dologin d=  new Dologin();
                            //d.execute("");
                        }catch (Exception e)
                        {
                            clients.setId(0);
                            Toast.makeText(getApplicationContext(),"exp",Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //serverResp.setText("Error getting response");
                clients.setId(0);
                Toast.makeText(getApplicationContext(),"errrrrrr",Toast.LENGTH_LONG).show();
            }
        });

        mRequestQueue.add(jsonObjectRequest);
        return  clients;
    }
    @Override
    public void onBackPressed() {

     //setResult(1,getIntent().putExtra("login","false"));
     //finish();
        System.exit(0);
    }


}
