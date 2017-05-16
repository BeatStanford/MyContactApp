package com.example.jaeyoung.mycontactapp;

import android.database.Cursor;
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
            //Insert Toast message here to indicate data was inserted successfully:
            Toast.makeText(this, "Contact added", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d("My Contact", "Error inserting data");
            //Create toast message saying it failed.
            Toast.makeText(this, "Error inserting Data", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewData (View v) {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            //Output a message using log.d and Toast
            return;
        }
        StringBuffer buffer = new StringBuffer();
        /*setup a loop with the cursor (res), using method moveToNext
        *inside the loop, append each column to the buffer
        *look for a method in the string buffer class that lets you append id, name, email, etc.
        * display the message using the showMessage
        */
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String error, String s) {
        //AlertDialog.Builder
    }


}
