package com.example.jaeyoung.mycontactapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.jaeyoung.mycontactapp.R.id.editText_address;
import static com.example.jaeyoung.mycontactapp.R.id.editText_email;
import static com.example.jaeyoung.mycontactapp.R.id.editText_name;
import static com.example.jaeyoung.mycontactapp.R.id.editText_phoneNumber;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    //tasks
    EditText editName;
    EditText editEmail;
    EditText editphoneNumber;
    EditText editAddress;
    EditText editSearch;
    Button btnAddData; //use this for the add contact button
    Button btnViewData; //use this for the view contact button
    String[] fields;
    //public static final String EXTRA_MESSAGE = "com.example.mycontactapp2.MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        //Add the layout variables:
        editName = (EditText) findViewById(R.id.editText_name);
        editEmail = (EditText) findViewById(R.id.editText_email);
        editphoneNumber = (EditText) findViewById(R.id.editText_phoneNumber);
        editAddress = (EditText) findViewById(R.id.editText_address);
        editSearch = (EditText) findViewById(R.id.editText_search);
        fields = new String[] {"Name: ", "Address: ", "Email: ", "phoneNumber: "};
    }

    public void addData (View v) {
        Log.d("MyContact", " addData() is adding contact"); //only name field is required
        boolean isInserted = myDb.insertData(editName.getText().toString(), editEmail.getText().toString(), editphoneNumber.getText().toString(), editAddress.getText().toString());
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
        editName.setText("");
        editAddress.setText("");;
        editEmail.setText("");
        editphoneNumber.setText("");
    }

    public void viewData (View v) {

        Log.d("My Contact", "viewData is used");
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            //Output a message using log.d and Toast
            return;
        }
        StringBuffer buffer = new StringBuffer();
        res.moveToFirst();
        for (int i = 0; i < res.getCount(); i++) {
            for (int j = 0; j < 4; j++) {
                buffer.append(fields[j-1]);
                buffer.append(res.getString(j));
                buffer.append("\n");
            }
            buffer.append("\n");
            res.moveToNext();
        }
        //display the message using showMessage() call
        showMessage("My Contacts", buffer.toString());
    }


    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void searchEntry(View v) {
        Log.d("My Contact app", "Search method used");

        String search = editSearch.getText().toString();
        StringBuffer buffer = new StringBuffer();
        Cursor res = myDb.getAllData();
        res.moveToFirst();

        for (int i = 0; i < res.getCount(); i++) {
            if (res.getString(1).equals(search)) {
                for (int j = 1; j <=4; j++) {
                    buffer.append(fields[j-1]);
                    buffer.append(res.getString(j));
                    buffer.append("\n");
                }
            }
            buffer.append("\n");
            res.moveToNext();
        }

        if (buffer.toString().equals("")) {
            showMessage("No match found", "");
        } else {
            showMessage(" Search Results", buffer.toString());
        }
        editSearch.setText("");
    }


}
