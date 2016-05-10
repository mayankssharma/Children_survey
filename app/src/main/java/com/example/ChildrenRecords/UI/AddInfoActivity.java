package com.example.ChildrenRecords.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ChildrenRecords.ChildrenRecord.R;
import com.example.ChildrenRecords.Model.AddInfoPojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.example.ChildrenRecords.Model.DatabaseHelper;


/**
 * Created by Mayank Sharma
 * @Date - 06-05-2016
 *
 * Used to add basic Information for a child
 */
public class AddInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
        // to add back button to the toolbar which take user to main activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String a[] = (String[]) getIntent().getExtras().get("oldvalues");
        String fname =a[0];
        TextView nameview1 = (TextView) findViewById(R.id.fname);
        nameview1.setText(fname);

        EditText txt1 = (EditText)findViewById(R.id.mydate);
        EditText txt2 = (EditText)findViewById(R.id.wenttobed);
        EditText txt3 = (EditText)findViewById(R.id.wokeup);
        EditText txt4 = (EditText)findViewById(R.id.tvwatch);
        EditText txt5 = (EditText)findViewById(R.id.familytime);
        // when focus is made on a particular element , information regarding what needs to be filled is displayed
        txt1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(hasFocus){
                    Toast temp = Toast.makeText(AddInfoActivity.this, "Today's Date [yyyy-mm-dd] format", Toast.LENGTH_SHORT);
                    temp.show();
                }
        }
        });

        txt2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(hasFocus){
                    Toast temp = Toast.makeText(AddInfoActivity.this, "for 8 pm enter 2000 ie 24 hour format", Toast.LENGTH_SHORT);
                    temp.show();
                }
            }
        });

        txt3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(hasFocus){
                    Toast temp = Toast.makeText(AddInfoActivity.this, "for 6 am enter 0600 ie 24 hour format", Toast.LENGTH_SHORT);
                    temp.show();
                }
            }
        });

        txt4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(hasFocus){
                    Toast temp = Toast.makeText(AddInfoActivity.this, "the number of hours - eg : 4", Toast.LENGTH_SHORT);
                    temp.show();
                }
            }
        });

        txt5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(hasFocus){
                    Toast temp = Toast.makeText(AddInfoActivity.this, "the number of hours - eg : 5", Toast.LENGTH_SHORT);
                    temp.show();
                }
            }
        });
    }

    /**
     * allows you to add information of activities about a child
     * @param v holds view for activity_add_info
     * @throws ParseException
     */
    public void addinfo(View v)throws ParseException {
        String datecheck=(((EditText) findViewById(R.id.mydate)).getText()).toString();
        if (v.getId() == R.id.submit) {
            // check whether date is entered in proper format YYYY-MM-DD
            if (!(datecheck.contains("-"))) {
                Toast temp = Toast.makeText(AddInfoActivity.this, "please enter date properly in YYYY-MM-DD format", Toast.LENGTH_SHORT);
                temp.show();
            }

            else {
                // checking whether the user is not entering any future dates
                SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
                Date d = new Date();
                String currentdate = 1900 + d.getYear() + "-" + (int)(1 +(int) d.getMonth()) + "-" + d.getDate();
                long d2 = formater.parse(datecheck).getTime();
                long d1 = formater.parse(currentdate).getTime();
                if ((d1-d2)/(1000*60*60*24) <0) {
                    Toast temp = Toast.makeText(AddInfoActivity.this, "please do not enter future dates", Toast.LENGTH_SHORT);
                    temp.show();
                }
                else{
                TextView e1 = (TextView) findViewById(R.id.fname);
                EditText e2 = (EditText) findViewById(R.id.mydate);
                EditText e3 = (EditText) findViewById(R.id.wenttobed);
                EditText e4 = (EditText) findViewById(R.id.wokeup);
                EditText e5 = (EditText) findViewById(R.id.tvwatch);
                EditText e6 = (EditText) findViewById(R.id.familytime);
                    // validating that all the values are filled by the user
                if (TextUtils.isEmpty((e1.getText().toString())) || TextUtils.isEmpty((e2.getText().toString())) || TextUtils.isEmpty((e3.getText().toString())) || TextUtils.isEmpty((e4.getText().toString())) || TextUtils.isEmpty((e5.getText().toString())) || TextUtils.isEmpty((e6.getText().toString()))) {
                    Toast temp = Toast.makeText(AddInfoActivity.this, "please dont leave any field blank", Toast.LENGTH_SHORT);
                    temp.show();
                } else {
                    AddInfoPojo ai = new AddInfoPojo();
                    ai.fname = e1.getText().toString();
                    ai.mydate = e2.getText().toString();
                    ai.wenttobed = e3.getText().toString();
                    ai.wokeup = e4.getText().toString();
                    ai.tvwatch = e5.getText().toString();
                    ai.familytime = e6.getText().toString();
                    // sending the values entered by the user to database
                    new DatabaseHelper(this).addinfo(ai);
                    Toast temp = Toast.makeText(AddInfoActivity.this, "Information added - Thanks...", Toast.LENGTH_SHORT);
                    temp.show();
                    Intent i = new Intent(AddInfoActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        }
        }
    }







}