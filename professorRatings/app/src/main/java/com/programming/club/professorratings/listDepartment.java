package com.programming.club.professorratings;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class listDepartment extends ActionBarActivity {

    //temp json string, later this will load from a file
    private String jsonString = "{\"professors\":[{\"professor_name\":\"professor1\",\"department\":\"Math\"},{\"professor_name\":\"professor2\",\"department\":\"Math\"},{\"professor_name\":\"professor3\",\"department\":\"English\"},"+
            "{\"professor_name\":\"professor4\",\"department\":\"English\"},{\"professor_name\":\"professor5\",\"department\":\"Chemistry\"},{\"professor_name\":\"professor6\",\"department\":\"Math\"},"+
            "{\"professor_name\":\"professor7\",\"department\":\"CS\"},{\"professor_name\":\"professor8\",\"department\":\"Physics\"},{\"professor_name\":\"professor9\",\"department\":\"Art\"},"+
            "{\"professor_name\":\"professor10\",\"department\":\"English\"},{\"professor_name\":\"professor11\",\"department\":\"Math\"},{\"professor_name\":\"professor12\",\"department\":\"History\"}]}";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_department);
        initList();
        ListView listView = (ListView) findViewById(R.id.listView2);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, proffesorList, android.R.layout.simple_list_item_1, new String[] {"professors"}, new int[] {android.R.id.text1});
        listView.setAdapter(simpleAdapter);
    }

    List<Map<String,String>> proffesorList = new ArrayList<Map<String,String>>();
    private void initList(){

        try{
            JSONObject jsonResponse = new JSONObject(jsonString);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("professors");

            for(int i = 0; i<jsonMainNode.length();i++){
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String name = jsonChildNode.optString("professor_name");
                String number = jsonChildNode.optString("department");
                String outPut = number;
                proffesorList.add(createEmployee("professors", outPut));
            }

        }
        catch(JSONException e){
            Toast.makeText(getApplicationContext(), "Error" + e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
    private HashMap<String, String> createEmployee(String name,String number){
        HashMap<String, String> employeeNameNo = new HashMap<String, String>();
        employeeNameNo.put(name, number);
        return employeeNameNo;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_department, menu);
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
