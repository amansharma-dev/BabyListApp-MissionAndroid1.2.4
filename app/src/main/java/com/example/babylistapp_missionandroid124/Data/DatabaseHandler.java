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

import java.util.ArrayList;
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
                + Util.COLUMN_QUANTITY+ " TEXT,"
                + Util.COLUMN_COLOR +" TEXT,"
                + Util.COLUMN_SIZE + " TEXT,"
                + Util.COLUMN_DATE_ADDED_ON + " TEXT" +")";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DORP_TABLE = String.valueOf((R.string.drop_table));
        sqLiteDatabase.execSQL(DORP_TABLE,new String[]{Util.DATABASE_NAME});

        //close connection
        sqLiteDatabase.close();
    }

    //Crud- create, read, update, delete

    //create--add--insert
    public void addBabyItem(BabyItems babyItems){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.COLUMN_ID,babyItems.getId());
        contentValues.put(Util.COLUMN_ITEM_NAME,babyItems.getItemName());
        contentValues.put(Util.COLUMN_COLOR,babyItems.getColor());
        contentValues.put(Util.COLUMN_SIZE,babyItems.getSize());
        contentValues.put(Util.COLUMN_DATE_ADDED_ON,babyItems.getDateAddedOn());

        sqLiteDatabase.insert(Util.TABLE_NAME,null,contentValues);

        sqLiteDatabase.close();
    }

    //read--singleEntry-cursor-query
    public BabyItems getSingleBabyItem(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(Util.TABLE_NAME,
                new String[]{Util.COLUMN_ID},
                Util.COLUMN_ID+"=?",
                new String[]{String.valueOf(id)},
                null,null,null);

        if(cursor!=null)
            cursor.moveToFirst();
            BabyItems babyItems = new BabyItems();
            babyItems.setId(Integer.parseInt(cursor.getString(0)));
            babyItems.setItemName(cursor.getString(1));
            babyItems.setQuantity(cursor.getString(2));
            babyItems.setColor(cursor.getString(3));
            babyItems.setSize(cursor.getString(4));
            babyItems.setDateAddedOn(cursor.getString(5));
            return babyItems;
    }

    //read-all-cursor-rawQuery

    public List<BabyItems> getAllBabyItems(){
        List<BabyItems> babyItemsList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String GET_ALL_ENTRIES = "SELECT * FROM "+ Util.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(GET_ALL_ENTRIES,null);
        if (cursor.moveToFirst()){
            do {
                BabyItems babyItems = new BabyItems();
                babyItems.setId(Integer.parseInt(cursor.getString(0)));
                babyItems.setItemName(cursor.getString(1));
                babyItems.setQuantity(cursor.getString(2));
                babyItems.setColor(cursor.getString(3));
                babyItems.setSize(cursor.getString(4));
                babyItems.setDateAddedOn(cursor.getString(5));
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
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String COUNT_QUERY = "SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(COUNT_QUERY,null);

        return cursor.getCount();
    }
}
