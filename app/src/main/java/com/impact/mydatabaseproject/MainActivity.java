package com.impact.mydatabaseproject;

import android.database.Cursor;
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
    Button btnAddData, btnViewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editName = (EditText) findViewById(R.id.id_name);
        editSurname = (EditText) findViewById(R.id.id_surname);
        editMarks = (EditText) findViewById(R.id.id_marks);
        btnAddData = (Button) findViewById(R.id.id_adddata);

        btnViewAll = (Button) findViewById(R.id.id_viewdata);
        addData();
        viewAll();
    }

    public void addData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(editName.getText().toString(), editSurname.getText().toString(), editMarks.getText().toString());
                if (isInserted = true)
                    Toast.makeText(MainActivity.this, "Data inserted.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "No Data inserted.", Toast.LENGTH_LONG).show();

            }
        });
    }


    public void viewAll() {
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    //show message
                    showMessage("Error","No data found");
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
                showMessage("Data",buffer.toString());
            }
        });
    }

    public  void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();



    }

}
