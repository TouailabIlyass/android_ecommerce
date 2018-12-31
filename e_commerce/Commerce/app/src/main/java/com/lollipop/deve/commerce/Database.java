package com.lollipop.deve.commerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.lollipop.deve.commerce.Models.Clients;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "clients.db";
    private static final String TABLE_CLIENTS = "client";
    private static final String KEY_ID = "id";
    private static final String KEY_IDC = "id_c";
    private static final String KEY_NOM = "nom";
    private static final String KEY_PRENOM = "prenom";
    private static final String KEY_VILLE = "ville";
    private static final String KEY_ADDR = "adresse";
    private static final String KEY_CIN = "cin";
    private static final String KEY_TEL = "tel";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_MAIL = "mail";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create = "create table " + TABLE_CLIENTS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"+KEY_IDC +" INTEGER ," + KEY_NOM + " TEXT" +
                "," + KEY_PRENOM + " TEXT" +
                "," + KEY_CIN + " TEXT"+
                "," + KEY_ADDR + " TEXT"+
                "," + KEY_VILLE + " TEXT"+
                "," + KEY_TEL + " TEXT"+
                "," + KEY_USERNAME + " TEXT"+
                "," + KEY_MAIL + " TEXT"+ ")";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTS);
        onCreate(db);
    }
        public void Del()
        { SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTS);

        }

    public  void addClient(Clients clients) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_IDC,clients.getId());
        values.put(KEY_NOM, clients.getNom());
        values.put(KEY_PRENOM, clients.getPrenom());
        values.put(KEY_CIN, clients.getCin());
        values.put(KEY_ADDR, clients.getAddr());
        values.put(KEY_VILLE, clients.getVille());
        values.put(KEY_TEL, clients.getTel());
        values.put(KEY_USERNAME, clients.getUsername());
        values.put(KEY_MAIL, clients.getMail());
        db.insert(TABLE_CLIENTS, null, values);
        db.close();
    }

    public Clients getClient(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from "+TABLE_CLIENTS, null);
        try{

        if (cursor.moveToFirst())
        {

            Clients clients = new Clients(Integer.parseInt(cursor.getString(1))
                    , cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),cursor.getString(8),"",cursor.getString(9),false);
            return clients;
        }
        }catch(Exception e)
        {
            Toast.makeText(MainActivity.getAppContext(),"ex",Toast.LENGTH_SHORT).show();
        }

      return null;
    }
    public void deleteClient(Clients client) {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_CLIENTS,  null,null);
        db.close();
    }

  /*  public List<Clients> getAllClients()
    {
        List<Clients> clientList= new ArrayList<Clients>();

        String  selectQuery= "SELECT * FROM " + TABLE_CLIENTS;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor= db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
        {
            do {
                Clients client= new Clients();
                client.setId(Integer.parseInt(cursor.getString(0)));
                client.setName(cursor.getString(1));
                client.setPhone(cursor.getString(2));
                clientList.add(client);
            }while(cursor.moveToNext());
        }

        return clientList;
    }
    */
/*
    int updateClient (Clients client) {
        SQLiteDatabase db= this.getWritableDatabase();
   ContentValues values = new ContentValues();
    values.put(KEY_NAME, client.getName());
    values.put(KEY_PH, client.getPhone());
    return db.update(TABLE_CLIENTS, values, KEY_ID + " = ?",new String[] { String.valueOf(client.getId()) });
    }

    public void deleteClient(Clients client) {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_CLIENTS, KEY_ID + " = ?",new String[] { String.valueOf(client.getId()) });
        db.close();
    }
        */
}