package com.example.ChildrenRecords.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ChildrenRecords.ChildrenRecord.R;
import com.example.ChildrenRecords.Model.AddChildPojo;
import com.example.ChildrenRecords.Model.DatabaseHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mayank Sharma
 * @Date - 06-05-2016
 *
 * Used to add activity for a child
 */
public class AddChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        // to add back button to the toolbar which take user to main activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    /**
     * allows you to add a child
     * @param v holds view for activity_add_child
     * @throws ParseException
     */
    public void addchild(View v) throws ParseException {
        RadioButton male;

        if (v.getId() == R.id.submit) {

            EditText e1 = (EditText) findViewById(R.id.fname);
            EditText e2 = (EditText) findViewById(R.id.lname);
            EditText e3 = (EditText) findViewById(R.id.dob);
            String dobcheck = e3.getText().toString();
            if (!(dobcheck.contains("-"))) {
                // check whether date is entered in proper format YYYY-MM-DD
                Toast temp = Toast.makeText(AddChildActivity.this, "please enter date properly in YYYY-MM-DD format", Toast.LENGTH_SHORT);
                temp.show();
            } else{
                // radio buttons to select the gender of the child
                String e4 = String.valueOf(((RadioGroup) findViewById(R.id.radiogroups)).getCheckedRadioButtonId());
            if (TextUtils.isEmpty((e1.getText().toString())) || TextUtils.isEmpty((e2.getText().toString())) || TextUtils.isEmpty((e3.getText().toString())) || e4.matches("-1")) {
                Toast temp = Toast.makeText(AddChildActivity.this, "please dont leave any field blank", Toast.LENGTH_SHORT);
                temp.show();
            } else {
                // checking whether the age of the child lies between 7 and 12 years
                SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
                Date d = new Date();
                String currentdate = 1900 + d.getYear() + "-" + (int)(1 +(int) d.getMonth()) + "-" + d.getDate();
                long d1=formater.parse(currentdate).getTime();
                long d2=formater.parse(dobcheck).getTime();
                if((Math.abs((d1-d2)/(1000*60*60*24)))<=2557 ||(Math.abs((d1-d2)/(1000*60*60*24)))>=4383){
                    Toast temp = Toast.makeText(AddChildActivity.this, "your child's age does not lies in between the age group", Toast.LENGTH_SHORT);
                    temp.show();
                }
                else {
                    EditText p1 = (EditText) findViewById(R.id.fname);
                    EditText p2 = (EditText) findViewById(R.id.lname);
                    EditText p4 = (EditText) findViewById(R.id.dob);
                    String p3 = null;


                    male = (RadioButton) findViewById(R.id.male);


                    int selectedId = ((RadioGroup) findViewById(R.id.radiogroups)).getCheckedRadioButtonId();

                    if (selectedId == male.getId()) {
                        p3 = "male";
                    } else {
                        p3 = "female";
                    }

                    AddChildPojo addchild = new AddChildPojo();
                    addchild.fname = p1.getText().toString();
                    addchild.lname = p2.getText().toString();
                    addchild.gender = p3;
                    addchild.dob = p4.getText().toString();
                    // passing the values recieved from the user to database helper class to persist them in database
                    new DatabaseHelper(this).addchild(addchild);

                    Toast temp = Toast.makeText(AddChildActivity.this, p1.getText().toString() + "  added", Toast.LENGTH_SHORT);
                    temp.show();

                    Intent i = new Intent(AddChildActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        }
        }
    }

}