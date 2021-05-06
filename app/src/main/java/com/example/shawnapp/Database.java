package com.example.shawnapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database  extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "ShawApp.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_DATE = "Date";
                                //COLUMN_DATE = "date";
    private static final String COLUMN_NOTE = "note";
    private static final String COLUMN_AVERAGE = "average";

    private static final String TABLE_CATE = "Categories";
    private static final String COLUMN_CATE_NAME = "cate_name";

    private static final String TABLE_DATE_CATE = "Date_Cate";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_CATE = "cate";
    private static final String COLUMN_POINT = "point";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String createTableDate = "CREATE TABLE " + TABLE_DATE +
                " (" + COLUMN_DATE + " TEXT PRIMARY KEY, " +
                COLUMN_NOTE + " TEXT, " +
                COLUMN_AVERAGE + " REAL DEFAULT 0);";
        String createTableCate = "CREATE TABLE " + TABLE_CATE +
                " (" + COLUMN_CATE_NAME + " TEXT PRIMARY KEY);";
        String createTableDateCate = "CREATE TABLE " + TABLE_DATE_CATE +
                " (" + COLUMN_DATE + " TEXT, " +
                COLUMN_CATE + " TEXT, " +
                COLUMN_POINT + " INTEGER DEFAULT 0, PRIMARY KEY ("+COLUMN_DATE+", "+COLUMN_CATE+"));";

        database.execSQL(createTableDate);
        database.execSQL(createTableCate);
        database.execSQL(createTableDateCate);
        String createInitCate = "INSERT INTO " + TABLE_CATE + " ( " + COLUMN_CATE_NAME + " ) " + " VALUES " +
                "(\"Work\"), " +
                "(\"Health\"), " +
                "(\"Play\"), " +
                "(\"Love\");";
        database.execSQL(createInitCate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_DATE);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_CATE);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_DATE_CATE);

        onCreate(database);
    }

    public boolean isNewDate(String date){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_DATE +
                        " FROM " + TABLE_DATE + " WHERE " + COLUMN_DATE + " = '" + date + "';";
        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query, null);
        }
        return cursor.getCount() == 0;
    }
    public void saveEvaluatePoint(String date, String cate, int point){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_CATE, cate);
        cv.put(COLUMN_POINT, point);
        long result = database.insert(TABLE_DATE_CATE, null, cv);
        if(result == -1){
            Log.d("save_eva_point", "Failed");
        }else {
            Log.d("save_eva_point", "Save successfully");
        }
    }
    public void saveAverageAndNote(String date, Double average, String notes){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_NOTE, notes);
        cv.put(COLUMN_AVERAGE, average);
        long result = database.insert(TABLE_DATE, null, cv);
        if(result == -1){
            Log.d("save_ave_note", "Failed");
        }else {
            Log.d("save_ave_note", "Save successfully");
        }
    }
    public double showAverageInCalendar(String date){
        double average = 0.0;
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_AVERAGE + " FROM " + TABLE_DATE + " WHERE " + COLUMN_DATE + " = '" + date + "';";
        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query, null);
        }
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                average = cursor.getDouble(0);
            }
        }
        return average;
    }
    public Cursor showCate(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_CATE_NAME + " FROM " + TABLE_CATE + ";";
        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query, null);
        }
            return cursor;
    }
    public long addNewCategory(String category){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CATE_NAME, category);
        return database.insert(TABLE_CATE, null, cv);
    }

    public long updateCategory(String oldCategory, String newCategory){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CATE_NAME, newCategory);
        return database.update(TABLE_CATE, cv, COLUMN_CATE_NAME + " = '" + oldCategory + "'", null);
    }

    public void deleteCategory(String category){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_CATE, COLUMN_CATE_NAME + " = '" + category + "'", null);
    }

    public Cursor showDataCate(String date){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_CATE + ", "+COLUMN_POINT + " FROM " + TABLE_DATE_CATE + " WHERE " + COLUMN_DATE + " = '" + date + "';";
        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }
    public Cursor showAverageAndNote(String date){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_AVERAGE + ", "+COLUMN_NOTE + " FROM " + TABLE_DATE + " WHERE " + COLUMN_DATE + " = '" + date + "';";
        Cursor cursor = null;
        if(database != null){
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateEvaluatePoint(String date, String cate, int point){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_POINT, point);
        long result = database.update(TABLE_DATE_CATE, cv, COLUMN_DATE +" = '"+ date + "' and " + COLUMN_CATE + " = '" + cate + "'", null);
        if(result == -1){
            Log.d("update_eva_point", "Failed");
        }else {
            Log.d("update_eva_point", "Save successfully");
        }
    }
    public void updateAverageAndNote(String date, Double average, String notes){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOTE, notes);
        cv.put(COLUMN_AVERAGE, average);
        long result = database.update(TABLE_DATE, cv, COLUMN_DATE +" = '"+ date + "'", null);
        if(result == -1){
            Log.d("update_ave_note", "Failed");
        }else {
            Log.d("update_ave_note", "Save successfully");
        }
    }
}
