package com.example.foodly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper {
    public static final String db = "foodly.db";
    public static final String id = "id";
    public static final String table1 = "users";
    public static final String table2 = "items";
    public static final String table3 = "orders";
    public databaseHelper(@Nullable Context context) {
        super(context, db, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table "+table1+ "("+id+" INTEGER PRIMARY KEY AUTOINCREMENT,type INTEGER, fName TEXT, lName TEXT, email TEXT, password TEXT)");
            db.execSQL("create table "+table2+ "("+id+" INTEGER PRIMARY KEY AUTOINCREMENT, itemName TEXT, itemPrice INTEGER, status INTEGER)");
            db.execSQL("create table "+table3+ "("+id+" INTEGER PRIMARY KEY AUTOINCREMENT, itemId INTEGER, cusId INTEGER, quantity INTERGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table1);
        db.execSQL("DROP TABLE IF EXISTS "+table2);
        db.execSQL("DROP TABLE IF EXISTS "+table3);
        onCreate(db);
    }
    public boolean addFood(String name, Integer price, Integer status)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("itemName",name);
        value.put("itemPrice",price);
        value.put("status",status);
        long res = db.insert(table2,null,value);
        if (res == -1)
            return false;
        else
            return true;
    }

    public boolean addUser(Integer type, String fname, String lname, String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("type",type);
        value.put("fName",fname);
        value.put("lName",lname);
        value.put("email",email);
        value.put("password",password);
        long res = db.insert(table1,null, value);
        if (res == -1)
            return false;
        else
            return true;
    }

    public boolean placeOrder(Integer cusID, Integer iID, Integer quantity)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("itemId", iID);
        values.put("cusId", cusID);
        values.put("quantity", quantity);
        long res = db.insert(table3, null, values);
        if (res == -1)
            return false;
        else
            return true;
    }

    public Cursor getItems()
    {
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+table2, null);
        return res;
    }

    public Cursor getOrders()
    {
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+table3, null);
        return res;
    }

    public Cursor getUser(Integer id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+table1+" where id = "+id, null);
        return res;
    }
    public Cursor getItem(Integer id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+table2+" where id = "+id, null);
        return res;
    }

    public Cursor doLogin(String email, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from users where email=\""+email+"\" AND password=\""+password+"\"", null);
        return res;
    }

    public boolean checkExistingUser(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from users where email=\""+email+"\"", null);
        if (res.getCount() != 0)
        {
            //user exists
            return true;
        }
        else
        {
            return false;
        }
    }
}
