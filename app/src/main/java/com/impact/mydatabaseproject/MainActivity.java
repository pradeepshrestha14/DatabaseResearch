package com.impact.mydatabaseproject;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public DatabaseHelper myDb;
    EditText editName, editSurname, editMarks;
    Button btnAddData, btnViewAll,btnViewAll_inFragment;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editName = (EditText) findViewById(R.id.id_name);
        editSurname = (EditText) findViewById(R.id.id_surname);
        editMarks = (EditText) findViewById(R.id.id_marks);
        btnAddData = (Button) findViewById(R.id.id_adddata);
        btnViewAll_inFragment= (Button) findViewById(R.id.id_viewdata_inFragment);
        btnViewAll = (Button) findViewById(R.id.id_viewdata);



        staticLocalData(); //static data only when app is installed.resource===(https://stackoverflow.com/questions/4636141/determine-if-android-app-is-the-first-time-used)
        addData();
        viewAll();
    }


    private void staticLocalData() {
        final String PREFS_NAME = "MyPrefsFile";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getBoolean("my_first_time", true)) {
            String[] name = {"Ram", "Shyam", "hari"};
            String[] surname = {"Shrestha", "Pradhan", "Rajsharma"};
            String[] marks = {"70", "80", "90"};
            for (int i = 0; i < name.length; i++) {
                myDb.insertData(name[i], surname[i], marks[i]);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("my_first_time", false);
                editor.commit();
            }


        }
    }


    public void addData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editName.length() != 0 && editSurname.length() != 0 && editMarks.length() != 0) {

                    boolean isInserted = myDb.insertData(editName.getText().toString(), editSurname.getText().toString(), editMarks.getText().toString());

                    if (isInserted = true)
                        Toast.makeText(MainActivity.this, "Data inserted.", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this, "No Data inserted.", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(MainActivity.this, "invalid inputs", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void viewAll() {
        btnViewAll_inFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ViewAll_Activity.class);
                startActivity(intent);

            }
        });



        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    //show message
                    showMessage("Error", "No data found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id:" + res.getString(0) + "\n");
                    buffer.append("Name:" + res.getString(1) + "\n");
                    buffer.append("Surname:" + res.getString(2) + "\n");
                    buffer.append("Marks:" + res.getString(3) + "\n\n\n");
                }
//show all data
                showMessage("Data", buffer.toString());
            }
        });




    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();


    }

}
