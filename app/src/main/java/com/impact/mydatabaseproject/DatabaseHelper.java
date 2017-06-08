package com.impact.mydatabaseproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by pra...deep on 5/8/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public long result;
    public static final String Database_Name = "Student.db";
    public static final String Table_Name = "student_table";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";

    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);
    }

//    ArrayList<Module> myReadData = new ArrayList<>();

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table_Name + "(ID INTEGER PRIMARY KEY AUTOiNCREMENT" +
                ",NAME TEXT," +
                "SURNAME TEXT," +
                "MARKS TEXT)");
Log.d("Database operations","Table created......");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + Table_Name);
        onCreate(db);


    }

    public boolean insertData(String name, String surname, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, marks);


        result = db.insert(Table_Name, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;


    }

//public Cursor getInformation(){
//    SQLiteDatabase db = this.getWritableDatabase();
//    String[] projection={COL_1,COL_2,COL_3,COL_4};
//    Cursor cursor = db.query(Table_Name,projection,null,null,null,null,null );
//    return cursor;
//}

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name, null);
        return res;
    }

}
