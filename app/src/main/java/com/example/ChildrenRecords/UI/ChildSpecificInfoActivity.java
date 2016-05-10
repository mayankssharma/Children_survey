package com.example.ChildrenRecords.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ChildrenRecords.ChildrenRecord.R;
import com.example.ChildrenRecords.Model.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Mayank Sharma
 * @Date - 06-05-2016
 *
 * Used to get retrieve Information related to a specific Child
 */
public class ChildSpecificInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_child_by_fname);
        // to add back button to the toolbar which take user to main activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * fetches information about activities related to a particular child based on fname
     * @param v holds view for activity_view_info4_specific_child
     */
    public void specificchildinfo(View v) {
        EditText p1 = (EditText) findViewById(R.id.fname);
        // get the child's name whose information you want to see
        if(TextUtils.isEmpty((p1.getText().toString())))
        {
            Toast temp = Toast.makeText(ChildSpecificInfoActivity.this,"please dont leave fname field blank",Toast.LENGTH_SHORT);
            temp.show();
        }
        else {
            // connect to the database to get the information related to specific child
            ArrayList<String> a =new DatabaseHelper(this).childspecificinfo((p1.getText().toString()));
            if(a.size()<2) {
                Toast temp = Toast.makeText(ChildSpecificInfoActivity.this,"child does not exist's or you have not entered child's information ",Toast.LENGTH_SHORT);
                temp.show();
            }else{
            for(int i=0; i <a.size();i++)
                Log.d("check","   "+a.get(i));
                // pass the control to the other activity for display the information and the information to be displayed is passed along with the intent
            Intent i = new Intent(ChildSpecificInfoActivity.this, ViewInfo4SpecificChildActivity.class);
                i.putExtra("oldvalues",a);
            startActivity(i);}
        }

    }
}
