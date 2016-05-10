package com.example.ChildrenRecords.UI;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ChildrenRecords.ChildrenRecord.R;

import java.util.ArrayList;

/**
 * Created by Mayank Sharma
 * @Date - 06-05-2016
 *
 * Used to View Information of all events for a particular Child
 */
public class ViewInfo4SpecificChildActivity extends AppCompatActivity {
    ArrayList<String> a = new ArrayList<String>();
    ArrayList<String> b = new ArrayList<String>();
    ListView mylist;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_info4_specific_child);
        // to add back button to the toolbar which take user to main activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // code to display information related to a specific child
        a.clear();b.clear();
        a =(ArrayList<String>) getIntent().getExtras().get("oldvalues");
        TextView nameview1 = (TextView)findViewById(R.id.fname);
        nameview1.setText("Displaying Information for "+a.get(0));
        for(int i=0; i <a.size();i=i+6){
            b.add(0,"DATE (yyyy-mm-dd) : "+a.get(i+1)+"\n \nWENT TO BED : "+a.get(i+2)+"\n \nWOKE UP : "+a.get(i+3)+"\n \nTIME SPENT IN FRONT OF TV OR PLAYING VIDEO GAMES : "+a.get(i+4)+" hours"+"\n \nTIME SPENT WITH FAMILY : "+a.get(i+5)+" hours");
        }
        mylist = (ListView)findViewById(R.id.mylist);
        mylist.setBackgroundColor(Color.CYAN);
        adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,b);
        mylist.setAdapter(adapter);
    }


}
