package com.example.babylistapp_missionandroid124.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.babylistapp_missionandroid124.Model.BabyItems;
import com.example.babylistapp_missionandroid124.R;
import com.example.babylistapp_missionandroid124.Util.Util;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create table

        String CREATE_TABLE = "CREATE TABLE "+ Util.TABLE_NAME +"("
                + Util.COLUMN_ID+" INTEGER PRIMARY KEY,"
                + Util.COLUMN_ITEM_NAME+" TEXT,"
                + Util.COLUMN_QUANTITY+ " INTEGER,"
                + Util.COLUMN_COLOR +" TEXT,"
                + Util.COLUMN_SIZE + " INTEGER,"
                + Util.COLUMN_DATE_ADDED_ON + " LONG" +")";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE = String.valueOf((R.string.drop_table));
        sqLiteDatabase.execSQL(DROP_TABLE,new String[]{Util.DATABASE_NAME});

        //create database
        onCreate(sqLiteDatabase);
    }

    //Crud- create, read, update, delete

    //create--add--insert
    public void addBabyItem(BabyItems babyItems){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.COLUMN_ITEM_NAME,babyItems.getItemName());
        contentValues.put(Util.COLUMN_QUANTITY,babyItems.getQuantity());
        contentValues.put(Util.COLUMN_COLOR,babyItems.getColor());
        contentValues.put(Util.COLUMN_SIZE,babyItems.getSize());
        contentValues.put(Util.COLUMN_DATE_ADDED_ON,java.lang.System.currentTimeMillis());//current time stamp of system

        sqLiteDatabase.insert(Util.TABLE_NAME,null,contentValues);

        //close connection
        sqLiteDatabase.close();
    }

    //read--singleEntry-cursor-query
    public BabyItems getSingleBabyItem(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(Util.TABLE_NAME,
                new String[]{
                        Util.COLUMN_ID,
                        Util.COLUMN_ITEM_NAME,
                        Util.COLUMN_QUANTITY,
                        Util.COLUMN_COLOR,
                        Util.COLUMN_SIZE,
                        Util.COLUMN_DATE_ADDED_ON
                },
                Util.COLUMN_ID+"=?",
                new String[]{String.valueOf(id)},
                null,null,null);

        if(cursor!=null)
            cursor.moveToFirst();
            BabyItems babyItems = new BabyItems();
        if (cursor != null) {
            babyItems.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Util.COLUMN_ID))));
            babyItems.setItemName(cursor.getString(cursor.getColumnIndex(Util.COLUMN_ITEM_NAME)));
            babyItems.setQuantity(cursor.getInt(cursor.getColumnIndex(Util.COLUMN_QUANTITY)));
            babyItems.setColor(cursor.getString(cursor.getColumnIndex(Util.COLUMN_COLOR)));
            babyItems.setSize(cursor.getInt(cursor.getColumnIndex(Util.COLUMN_SIZE)));

            //convert time to something readable
            DateFormat dateFormat = DateFormat.getDateInstance();
            String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Util.COLUMN_DATE_ADDED_ON)))
                    .getTime()); //April 18,2020

            babyItems.setDateAddedOn(formattedDate);

        }
            return babyItems;
    }

    //read-all-cursor-rawQuery

    public List<BabyItems> getAllBabyItems(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        List<BabyItems> babyItemsList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(Util.TABLE_NAME,
                new String[]{
                        Util.COLUMN_ID,
                        Util.COLUMN_ITEM_NAME,
                        Util.COLUMN_QUANTITY,
                        Util.COLUMN_COLOR,
                        Util.COLUMN_SIZE,
                        Util.COLUMN_DATE_ADDED_ON},
                null,null,
                null,null,
                Util.COLUMN_DATE_ADDED_ON + " DESC");
        if (cursor.moveToFirst()){
            do {
                BabyItems babyItems = new BabyItems();

                babyItems.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Util.COLUMN_ID))));
                babyItems.setItemName(cursor.getString(cursor.getColumnIndex(Util.COLUMN_ITEM_NAME)));
                babyItems.setQuantity(cursor.getInt(cursor.getColumnIndex(Util.COLUMN_QUANTITY)));
                babyItems.setColor(cursor.getString(cursor.getColumnIndex(Util.COLUMN_COLOR)));
                babyItems.setSize(cursor.getInt(cursor.getColumnIndex(Util.COLUMN_SIZE)));

                //convert time to something readable
                DateFormat dateFormat = DateFormat.getDateInstance();
                String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Util.COLUMN_DATE_ADDED_ON)))
                        .getTime()); //April 18,2020

                babyItems.setDateAddedOn(formattedDate);

                babyItemsList.add(babyItems);
            }while (cursor.moveToNext());
        }
        return babyItemsList;
    }

    //update
    public int updateBabyItem(BabyItems babyItems){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.COLUMN_ITEM_NAME,babyItems.getItemName());
        contentValues.put(Util.COLUMN_COLOR,babyItems.getColor());
        contentValues.put(Util.COLUMN_SIZE,babyItems.getSize());
        contentValues.put(Util.COLUMN_DATE_ADDED_ON,babyItems.getDateAddedOn());

        return sqLiteDatabase.update(Util.TABLE_NAME,
                contentValues,Util.COLUMN_ID+"=?",
                new String[]{String.valueOf(babyItems.getId())});
    }

    //delete
    public void deleteBabyItem(BabyItems babyItems){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.delete(Util.TABLE_NAME,
                Util.COLUMN_ID+"=?",
                new String[]{String.valueOf(babyItems.getId())});

        //close connection
        sqLiteDatabase.close();
    }

    //get count of total entries
    public int getCount(){
        SQLiteDatabase sqLiteDatabase =  getReadableDatabase();
        String COUNT_QUERY = "SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(COUNT_QUERY,null);

        return cursor.getCount();
    }
}
