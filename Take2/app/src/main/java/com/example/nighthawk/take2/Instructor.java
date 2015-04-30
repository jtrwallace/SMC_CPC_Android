package com.example.nighthawk.take2;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Instructor extends ActionBarActivity {
    DatabaseHelper myDb;
    EditText editInstructor, editSection, editDepartment, editAvg;
    Button btnAddData;
    Button btnViewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);

        myDb = new DatabaseHelper(this);
        btnViewAll = (Button)findViewById(R.id.button_viewAll);
        viewAll();

        editInstructor = (EditText)findViewById(R.id.editText_instructor);
        editSection = (EditText)findViewById(R.id.editText_section);
        editDepartment = (EditText)findViewById(R.id.editText2_department);
        editAvg = (EditText)findViewById(R.id.editText3_avg);

        btnAddData = (Button)findViewById(R.id.button_insert);

        addData();

    }

    public void addData()
    {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Boolean isInserted = myDb.insertData("None", "None",
                                Integer.parseInt(editSection.getText().toString()),editInstructor.getText().toString(),
                                1,0,0,0,0,0,
                                Integer.parseInt(editAvg.getText().toString()));
                        if (isInserted == true)
                        {
                            Toast.makeText(Instructor.this,"Data Added",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(Instructor.this,"Data Add Not Successful",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();

                        if (res.getCount() == 0){
                            //insert message later if the database is empty
                            showMessage("Empty", "No data found");
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
