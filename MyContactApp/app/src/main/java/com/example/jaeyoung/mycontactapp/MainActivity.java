package com.example.jaeyoung.mycontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    //add some more fields
    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        //Add the layout variables:
        editName = (EditText) findViewById(R.id.editText_name);
    }

    public void addData (View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString());
        if(isInserted == true) {
            Log.d("My Contact", "Successfully inserted data");
            //Insert Toast message here:
            Toast.makeText(this, "Contact added", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d("My Contact", "Error inserting data");
        }
    }


}
