package com.example.ChildrenRecords.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ChildrenRecords.ChildrenRecord.R;
import com.example.ChildrenRecords.Model.DatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Mayank Sharma
 * @Date - 06-05-2016
 *
 * It allows User to select based on what Factor will he like to retrieve the Information
 */


public class SelectInfoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_info_view);
        // to add back button to the toolbar which take user to main activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    public void viewinfo(View v) {
// to view all the info
        ArrayList<String> a=null;
        if (v.getId() == R.id.allinfo) {
            a = new DatabaseHelper(this).allinfo();
            for(int i=0; i <a.size();i++)
            Log.d("check","   "+a.get(i));
        }
        Intent i = new Intent(SelectInfoViewActivity.this, ViewAllInfoActivity.class);
        i.putExtra("oldvalues",a);
        startActivity(i);
    }


    public void specificchildinfo(View v) {
        if (v.getId() == R.id.specificchildinfo) {
            // to view information related to a specific child using first name
            Intent i = new Intent(SelectInfoViewActivity.this, ChildSpecificInfoActivity.class);

            startActivity(i);
        }
    }

    public void past30daysonly(View v) throws ParseException {
        if (v.getId() == R.id.past30daysonly) {
            // to view information of the past 30 days
            ArrayList<String> a=null;ArrayList<String> b= new ArrayList<>();
                // connecting to the database to get all the information
                a = new DatabaseHelper(this).allinfo();
                for(int i=1; i <a.size();i=i+6) {
                    SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = new Date();
                    String currentdate = 1900 + d.getYear() + "-" + (int)(1 +(int) d.getMonth()) + "-" + d.getDate();
                    long d1 = formater.parse(currentdate).getTime();
                    long d2 = formater.parse(a.get(i)).getTime();
                    // if the date lies within past 30 days then add it to new array
                    if ((Math.abs((d1 - d2) / (1000 * 60 * 60 * 24))) <= 30) {
                        b.add(a.get(i-1));b.add(a.get(i));b.add(a.get(i+1));b.add(a.get(i+2));b.add(a.get(i+3));b.add(a.get(i+4));
                    }
                } // if information exists , then send to the display view screen along with the values passed through intent
            if(b != null){
            Intent i = new Intent(SelectInfoViewActivity.this, ViewAllInfoActivity.class);
            i.putExtra("oldvalues",b);
            startActivity(i);}
            // if information does not exists, then give a message
            else{
                Toast temp = Toast.makeText(SelectInfoViewActivity.this, "no entry in past 30 days", Toast.LENGTH_SHORT);
                temp.show();
            }
        }
    }
}

