package com.example.nighthawk.take2;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Instructor extends ActionBarActivity {
    DatabaseHelper myDb;
    Button btnViewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        myDb = new DatabaseHelper(this);
        btnViewAll = (Button)findViewById(R.id.button_viewAll);
        viewAll();
    }

    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();

                        if (res.getCount() == 0){
                            //insert message later if the database is empty
                            showMessage("ERROR", "No data found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();

                        //go through each part of the DB
                        while(res.moveToNext())
                        {
                            buffer.append("Id : "+ res.getString(0) + "\n");
                            buffer.append("Instructor : "+ res.getString(4) + "\n");
                            buffer.append("Section : "+ res.getString(3) + "\n");
                            buffer.append("Subject : "+ res.getString(1) + "\n\n");

                        }
                        showMessage("DATA", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instructor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}