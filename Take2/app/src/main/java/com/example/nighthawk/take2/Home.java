package com.example.nighthawk.take2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.content.Intent;
import android.view.View;


public class Home extends ActionBarActivity {
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myDb = new DatabaseHelper(this);
    }

    //Buttons to change activites

    public void buttonDepartment(View view) {
        Intent intent = new Intent(this, DepartmentPage.class);
        startActivity(intent);
    }

    public void buttonInstructor(View view) {
        Intent intent = new Intent(this, Instructor.class);
        startActivity(intent);
    }
    /*
    public void buttonCourse(View view) {
        Intent intent = new Intent(this, Course.class);
        startActivity(intent);
            }
    public void buttonInstructor(View view) {
        //Button button4 = (Button) findViewById(R.id.button4);
        Intent intent = new Intent(this, Instructor.class);
        startActivity(intent);
    }
    */

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    //for button use to department page
    public void departmentClick (View view){


        Intent intent = new Intent(this,DepartmentPage.class);
        startActivity(intent);
    }
     // for button Subject
      public void subjectCLick ( View view ){

          Intent intent = new Intent (this,SubjectPage.class );
          startActivity(intent);
      }


}

