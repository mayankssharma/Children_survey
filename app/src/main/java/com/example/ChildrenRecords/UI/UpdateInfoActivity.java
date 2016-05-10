package com.example.ChildrenRecords.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ChildrenRecords.ChildrenRecord.R;
import com.example.ChildrenRecords.Model.DatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Mayank Sharma
 * @Date - 06-05-2016
 *
 * It allows user to update basic information of a child
 */
public class UpdateInfoActivity extends AppCompatActivity {
    String id =null; String oldfname=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        // to add back button to the toolbar which take user to main activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String a[] = (String[])getIntent().getExtras().get("oldvalues");
        // setting the old values on the screen which can be changed by the user to enter updated values
        String fname = a[0]; String lname = a[1]; String gender=a[2]; String dob=a[3]; id=a[4]; oldfname=a[0];
        TextView nameview1 = (TextView)findViewById(R.id.fname);
        nameview1.setText(fname);
        TextView nameview2 = (TextView)findViewById(R.id.lname);
        nameview2.setText(lname);
        TextView nameview4 = (TextView)findViewById(R.id.dob);
        nameview4.setText(dob);
        if(gender.matches("male")){
            RadioButton b = (RadioButton) findViewById(R.id.male);
            b.setChecked(true);
        }
        else{
            RadioButton b = (RadioButton) findViewById(R.id.female);
            b.setChecked(true);
        }
    }

    /**
     * It is used to update Basic Information of a child like fname , lname , Date of Birth , gender
     * @param v holds view for activity_update_info
     * @throws ParseException
     */
    public void updatechild(View v) throws ParseException {
        RadioButton male;
        // getting the values entered by the user
        if (v.getId() == R.id.submit) {
            Log.d("database operstion", "in updatechild method 1");
            EditText p1 = (EditText) findViewById(R.id.fname);
            EditText p2 = (EditText) findViewById(R.id.lname);
            EditText p4 = (EditText) findViewById(R.id.dob);
            String p3 = null;
            String dobcheck = p4.getText().toString();
            // checking twhether the date is entered in proper format
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            String currentdate = 1900 + d.getYear() + "-" + (int)(1 +(int) d.getMonth()) + "-" + d.getDate();
            long d1 = formater.parse(currentdate).getTime();
            long d2 = formater.parse(dobcheck).getTime();
            if (!(dobcheck.contains("-"))) {
                Toast temp = Toast.makeText(UpdateInfoActivity.this, "please enter date properly in YYYY-MM-DD format", Toast.LENGTH_SHORT);
                temp.show();
            } else {
                //checking that the age of child lies between 7 and 12 years
                if ((Math.abs((d1 - d2) / (1000 * 60 * 60 * 24))) <= 2557 || (Math.abs((d1 - d2) / (1000 * 60 * 60 * 24))) >= 4383) {
                    Toast temp = Toast.makeText(UpdateInfoActivity.this, "your child's age does not lies in between the age group", Toast.LENGTH_SHORT);
                    temp.show();
                } else {
                    male = (RadioButton) findViewById(R.id.male);
                    int selectedId = ((RadioGroup) findViewById(R.id.radiogroup)).getCheckedRadioButtonId();
                    if (selectedId == male.getId()) {
                        p3 = "male";
                    } else {
                        p3 = "female";
                    }
                    String fname = p1.getText().toString();
                    String lname = p2.getText().toString();
                    String gender = p3;
                    String dob = p4.getText().toString();
                    // connecting to the database to update child info
                    new DatabaseHelper(this).updatechild(fname, lname, p3, dob, id, oldfname);

                    Toast temp = Toast.makeText(UpdateInfoActivity.this, " Child Updated ", Toast.LENGTH_SHORT);
                    temp.show();
                    Intent i = new Intent(UpdateInfoActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        }
    }
}
