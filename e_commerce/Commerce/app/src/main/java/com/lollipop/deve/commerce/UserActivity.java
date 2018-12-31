package com.lollipop.deve.commerce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lollipop.deve.commerce.Models.Clients;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UserActivity extends AppCompatActivity {
     EditText nom,prenom,cin,addr,ville,tel,username,password,mail;
     TextView prev,next;
     private int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        final Button valide = findViewById(R.id.valide_btn);

        nom = findViewById(R.id.nomCText);
        prenom = findViewById(R.id.prenomCText);
        cin = findViewById(R.id.cinCText);
        ville = findViewById(R.id.villeCText);
        addr = findViewById(R.id.addrCText);
        tel = findViewById(R.id.telCText);
        password = findViewById(R.id.passCText);
        username = findViewById(R.id.usernameCText);
        mail = findViewById(R.id.mailCText);
        prev = findViewById(R.id.user_prev);
        next = findViewById(R.id.user_next);
        valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Database database = new Database(getApplicationContext());
               // database.addClient(new Clients(0,nom.getText().toString(),prenom.getText().toString(),cin.getText().toString(),ville.getText().toString(),addr.getText().toString(),tel.getText().toString()));
                jsonRequestAddClient(new Clients(0,nom.getText().toString(),prenom.getText().toString(),cin.getText().toString(),ville.getText().toString(),addr.getText().toString(),tel.getText().toString(),username.getText().toString(),password.getText().toString(),mail.getText().toString(),false));
                finish();

            }
        });
        status = 0;
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status == 0)
                {
                    nom.setVisibility(View.VISIBLE);
                    prenom.setVisibility(View.VISIBLE);
                    cin.setVisibility(View.GONE);
                    tel.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);
                    prev.setVisibility(View.INVISIBLE);
                }
                else if(status == 1)
                {
                    cin.setVisibility(View.VISIBLE);
                    tel.setVisibility(View.VISIBLE);
                    mail.setVisibility(View.GONE);
                    ville.setVisibility(View.GONE);
                    addr.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);
                    status--;
                }
                else if(status == 2)
                {
                    addr.setVisibility(View.VISIBLE);
                    ville.setVisibility(View.VISIBLE);
                    username.setVisibility(View.GONE);
                    password.setVisibility(View.GONE);
                    mail.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);
                    valide.setVisibility(View.GONE);
                    status--;
                }

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status == 0)
                {
                    nom.setVisibility(View.GONE);
                    prenom.setVisibility(View.GONE);
                    cin.setVisibility(View.VISIBLE);
                    tel.setVisibility(View.VISIBLE);
                    prev.setVisibility(View.VISIBLE);
                    status++;
                }
                else if(status == 1)
                {
                    cin.setVisibility(View.GONE);
                    tel.setVisibility(View.GONE);
                    ville.setVisibility(View.VISIBLE);
                    addr.setVisibility(View.VISIBLE);
                    status++;
                }
                else if(status == 2)
                {
                    addr.setVisibility(View.GONE);
                    ville.setVisibility(View.GONE);
                    mail.setVisibility(View.VISIBLE);
                    username.setVisibility(View.VISIBLE);
                    password.setVisibility(View.VISIBLE);
                    valide.setVisibility(View.VISIBLE);
                    next.setVisibility(View.INVISIBLE);
                }
            }
        });
/*
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.INTERNAL_CONTENT_URI);
                //startActivityForResult(gallery,100);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
            }
        });
        */

    }
    private   void jsonRequestAddClient(Clients c)
    {
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
            json.put("mail",c.getMail());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = Res.URL+"/client";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //serverResp.setText("String Response : "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //serverResp.setText("Error getting response");
            }
        });

        mRequestQueue.add(jsonObjectRequest);

    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 100)
        {
            imageUri = data.getData();
            profile.setImageURI(imageUri);
            //bitmap = BitmapFactory.decodeResource(getResources(), R.id.profile_pic);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            }catch (Exception e) {
                e.printStackTrace();
            }
            uploaduserimage();
        }
    }
    public void uploaduserimage(){
        progressDialog = new ProgressDialog(UserActivity.this);
        progressDialog.setMessage("Uploading, please wait...");
        progressDialog.show();
        String url=Res.URL+"/file/upload";
        //converting image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        //sending image to server
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                progressDialog.dismiss();
                if(s.equals("true")){
                    Toast.makeText(UserActivity.this, "Uploaded Successful", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(UserActivity.this, "Some error occurred!", Toast.LENGTH_LONG).show();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(UserActivity.this, "Some error occurred -> "+volleyError, Toast.LENGTH_LONG).show();;
            }
        }) {
            //adding parameters to send
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("image", imageString);
                return parameters;
            }
        };

        RequestQueue rQueue = Volley.newRequestQueue(UserActivity.this);
        rQueue.add(request);
    }
*/
   /* public void uploaduserimage(){


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url=Res.URL+"/file/upload";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("Myresponse",""+response);
                Toast.makeText(UserActivity.this, ""+response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Mysmart",""+error);
                Toast.makeText(UserActivity.this, "ttt"+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                String images = getStringImage(bitmap);
                param.put("image",images);
                return param;
            }
        };

        requestQueue.add(stringRequest);
    }
    public String getStringImage(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }*/
/* protected void sendEmail() {
        Log.i("Send email", "");
        int max=9999,min=1000;
        String[] TO = {mail.getText().toString()};
        String[] CC = {mail.getText().toString()};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        int random = new Random().nextInt((max - min) + 1) + min;

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Confirm your mail");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Your code is : "+random);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(UserActivity.this,"There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
*/

}
