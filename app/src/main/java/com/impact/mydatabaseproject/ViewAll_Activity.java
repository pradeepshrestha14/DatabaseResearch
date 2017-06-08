package com.impact.mydatabaseproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewAll_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
     ArrayList<Module> moduleItems=new ArrayList<Module>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_layout);
        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        DatabaseHelper dataBaseHelper=new DatabaseHelper(this);


        Cursor reso = dataBaseHelper.getAllData();
        if (reso.getCount() == 0) {
            //show message
            Toast.makeText(this, "no data found", Toast.LENGTH_SHORT).show();
            return;
        }
         while (reso.moveToNext()) {
             Module module=new Module(reso.getInt(0),reso.getString(1),reso.getString(2),reso.getString(3));
             moduleItems.add(module);
            }


//        Cursor cursor=dataBaseHelper.getInformation();
//        cursor.moveToFirst();
//        do {
//            Module module=new Module(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
//moduleItems.add(module);
//
//        }while(cursor.moveToNext());
//        dataBaseHelper.close();

        adapter = new NewAdapter(moduleItems);
        recyclerView.setAdapter(adapter);

    }
}