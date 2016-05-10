package com.example.ChildrenRecords.Model;

/**
 * Created by Mayank on 04-05-2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Mayank on 03-05-2016.
 * @Date - 06-05-2016
 *
 * Used For Interactions with the database
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static  final int DATABASE_VERSION =1;
    private static  final String DATABASE_NAME ="children.db";
    private static  final String TABLE_NAME ="children3";
    private static  final String TABLE_NAME2 ="info3";
    private static  final String COLUMN_ID = "id";
    private static  final String FNAME ="fname";
    private static  final String LNAME ="lname";
    private static  final String GENDER ="gender";
    private static  final String DOB ="dob";
    private static  final String MYDATE ="mydate";
    private static  final String WENTTOBED ="wenttobed";
    private static  final String WOKEUP ="wokeup";
    private static  final String TVWATCH ="tvwatch";
    private static  final String FAMILYTIME ="familytime";


    SQLiteDatabase db;
    // queries for the creation of 2 tables respectively
    private static final String TABLE_CREATE ="create table IF NOT EXISTS children3 (id integer primary key not null , fname text not null , lname text not null, gender text not null, dob text not null);";
    private static final String TABLE_CREATE2 ="create table IF NOT EXISTS info3 (id integer primary key not null , fname text not null , mydate text not null, wenttobed text not null, wokeup text not null, tvwatch text not null, familytime text not null);";


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    /**
     * method to add new child
     * @param addchild contains Object of AddChildPojo class which is to bed added to the DB
     */
    public void addchild(AddChildPojo addchild){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.execSQL( TABLE_CREATE);
        String query = "select * from children3";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ID,count+1);
        values.put(FNAME,addchild.fname);
        values.put(LNAME,addchild.lname);
        values.put(GENDER,addchild.gender);
        values.put(DOB,addchild.dob);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }


    public Cursor getchildfomfname(String c)
    {
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from children3",null);
        return res;
    }


    /**
     * Used to update the basic child Information
     * @param fname contains updated first name of child
     * @param lname contains updated last name of child
     * @param gender contains updated gender of child
     * @param dob contains updated date of birth of child
     * @param id contains id of child in children table
     * @param oldfname contains first name (before updation) of child
     */
    public void updatechild(String fname, String lname,String gender,String dob,String id,String oldfname){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fname",fname);
        cv.put("lname",lname);
        cv.put("gender",gender);
        cv.put("dob",dob);
        String whereargs[] = {id};
        db.update("children3",cv,"id = ?",whereargs);

       // update the first name in other table(info) also
        ContentValues cv1 = new ContentValues();
        cv1.put("fname",fname);
        String whereargs1[] = {oldfname};
        db.update("info3",cv1,"fname = ?",whereargs1);
        db.close();
    }


    /**
     * Used to add the information about the activities of a child
     * @param ai contains the object of AddInfoPojo class which contains the information about the activities of a child
     */
    public void addinfo(AddInfoPojo ai){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.execSQL( TABLE_CREATE2);
        String query = "select * from info3";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        values.put(COLUMN_ID,count+1);
        values.put("fname",ai.fname);
        values.put(MYDATE,ai.mydate);
        values.put(WENTTOBED,ai.wenttobed);
        values.put(WOKEUP,ai.wokeup);
        values.put(TVWATCH,ai.tvwatch);
        values.put(FAMILYTIME,ai.familytime);
        db.insert(TABLE_NAME2,null,values);
        db.close();
    }

    /**
     * method to get all the information from info table inorder to display
     * @return arraylist of string containing all the Information related to events add for all children
     */
    public ArrayList<String> allinfo(){
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from info3" ,null);
        ArrayList<String> a = new ArrayList();
        while(res.moveToNext()){
               a.add(res.getString(1));
            a.add(res.getString(2));
            a.add(res.getString(3));
            a.add(res.getString(4));
            a.add(res.getString(5));
            a.add(res.getString(6));
            }
        db.close();
        return a;
        }


    /**
     * method to get information based on the first name of the child
     * @param fname conatins the first name of the child
     * @return arraylist of string containing all the Information related to events add for a particular child
     */
    public ArrayList<String> childspecificinfo(String fname){
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from info3 where fname = '"+fname+"'" ,null);
        ArrayList<String> a = new ArrayList();
        while(res.moveToNext()){
            a.add(res.getString(1));
            a.add(res.getString(2));
            a.add(res.getString(3));
            a.add(res.getString(4));
            a.add(res.getString(5));
            a.add(res.getString(6));
        }
        db.close();
        return a;
    }
}
