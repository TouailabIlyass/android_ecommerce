package com.lollipop.deve.commerce;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;
import com.lollipop.deve.commerce.Models.Clients;
import com.lollipop.deve.commerce.Models.ValideMailActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private static Context appContext;
private static DrawerLayout drawerLayout;
private static int etat;
private   LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Database database = new Database(getApplicationContext());
       //SQLiteDatabase db = database.getWritableDatabase();
       //database.deleteClient(null);
       //database.Del();
        //database.onUpgrade(db,0,1);
        Clients c= database.getClient(1);
        Res.setClient(c);
        if (c == null)
        {
            Intent i = new Intent(MainActivity.this,LoginActivity.class);
            startActivityForResult(i,1);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
            */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ////////
        appContext = getApplicationContext();
        etat=0;
        //selectFrame(R.id.nav_Allproduits);

        /////////
        TextView home = findViewById(R.id.btnhome);
        TextView search = findViewById(R.id.btnsearch);
        TextView viewsearch = findViewById(R.id.viewsearch);
        final EditText txtsearch = findViewById(R.id.txtsearch);
        linearLayout = findViewById(R.id.layoutsearch);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectFrame(R.id.nav_Allproduits);
            }
        });
        viewsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str= txtsearch.getText().toString();
                if(str.equals(""))
                    return;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, Allproduits.newInstance(2,str)).commit();
                linearLayout.setVisibility(View.GONE);
                txtsearch.setText("");
            }
        });
        if(c!=null)
        {   selectFrame(R.id.nav_Allproduits);
            setUser();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        {
            if(requestCode == 1)
            {
                Database database = new Database(getApplicationContext());
                Clients c=database.getClient(1);
                Res.setClient(c);
                setUser();
                //Toast.makeText(getApplicationContext(),c.getUsername()+"|"+c.getId()+"ccc",Toast.LENGTH_SHORT).show();
                this.onRestart();
            }
            else if(requestCode == 2)
            {
               // Intent i = new Intent(MainActivity.this,ValideMailActivity.class);
               // startActivityForResult(i,2);
            }


        }
    }

    @Override
    public void onBackPressed() {

        if(linearLayout.getVisibility() == View.VISIBLE)
        {
            linearLayout.setVisibility(View.GONE);
            return;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (etat == 0) {
                drawer.openDrawer(GravityCompat.START);
                etat = 1;
                return;
            }
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

            selectFrame(item.getItemId());

       // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void selectFrame(int id) {
        Fragment fragment = null;
        setBarBottom(true);
        if (id == R.id.nav_Allproduits) {
            fragment =  Allproduits.newInstance(0);
        }
       else if (id == R.id.nav_vetements) {
            fragment = Allproduits.newInstance(1);
        }
        else if (id == R.id.nav_search) {
            fragment = Allproduits.newInstance(2, "hp");
        }
        else if (id == R.id.nav_sous_vetements) {
            fragment = Allproduits.newInstance(3);
        }
        else if (id == R.id.nav_achatsenligne) {
            fragment = Allproduits.newInstance(4);
        }
        else if (id == R.id.nav_sourie) {
            fragment = Allproduits.newInstance(2,"sourie");
        }
        else if(id == R.id.nav_panier)
        {
            fragment = new PanierFragment();
            setBarBottom(false);
        }
        else if(id == R.id.nav_cmd)
        {
            fragment = new MesCmdFragment();
        }
        else if (id == R.id.nav_deconnection) {
               deconnection();
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
        }
        //((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
       Closedrawer();
    }
    public static Context getAppContext()
    {
        return  appContext;
    }

    public static void Opendrawer()
    {
        drawerLayout.openDrawer((int) GravityCompat.START);
    }
    public static void Closedrawer()
    {
        drawerLayout.closeDrawer((int) GravityCompat.START);
        etat=0;
    }
    private void deconnection()
    {
        Database database = new Database(getApplicationContext());
        database.deleteClient(null);
        System.exit(0);

    }
    private void  setUser()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername =  headerView.findViewById(R.id.nav_header_user);
        TextView navMail =  headerView.findViewById(R.id.nav_header_mail);
        navUsername.setText(Res.getClients().getUsername());
        navMail.setText(Res.getClients().getMail());

        //navHeaderView= navigationView.inflateHeaderView(R.layout.nav_header_main);
        //tvHeaderName= (TextView) navHeaderView.findViewById(R.id.tvHeaderName);
        //tvHeaderName.setText("Saly");
    }
    public  void setBarBottom(boolean status)
    {
        LinearLayout bar = findViewById(R.id.layoutBarBottom);
        if(status)
        {
            bar.setVisibility(View.VISIBLE);
            return;
        }
        bar.setVisibility(View.GONE);
    }
    protected void onResume()
    {
        Database database = new Database(getApplicationContext());
        //SQLiteDatabase db = database.getWritableDatabase();
        //database.deleteClient(null);
        //database.Del();
        //database.onUpgrade(db,0,1);
        Clients c= database.getClient(1);
        Res.setClient(c);
        if(c!=null)
        {   selectFrame(R.id.nav_Allproduits);
            setUser();
        }
        super.onResume();
    }

}
