package com.example.ChildrenRecords.UI;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ChildrenRecords.ChildrenRecord.R;
import com.example.ChildrenRecords.Model.DatabaseHelper;

/**
 * Created by Mayank Sharma
 * @Date - 06-05-2016
 *
 * It allows User to search the child by first name based on which update or add info tasks will be performed
 */
public class SearchForChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_child);
        // to add back button to the toolbar which take user to main activity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * will search for child in DB and if present will call activity_update_info OR activity_add_info accordingly depending upon what user has selected
     * @param v holds view for activity_search_for_child
     */
    public void updatechild(View v) {
        // used the below if-else for activity reuse
        if (((String) getIntent().getExtras().get("nextscreen")).contains("updateinfo")) { // calling the screen to update information


            if (v.getId() == R.id.submit) {

                EditText p1 = (EditText) findViewById(R.id.fname);
                // validating that fields are filled by the user
                if (TextUtils.isEmpty((p1.getText().toString()))) {
                    Toast temp = Toast.makeText(SearchForChildActivity.this, "please dont leave any field blank", Toast.LENGTH_SHORT);
                    temp.show();
                } else {
                    String c = p1.getText().toString();
                    // connecting to the database to check whether the child entered by the user exists or not
                    Cursor res = new DatabaseHelper(this).getchildfomfname(c);
                    EditText et1, et2, et3, et4;
                    String a, b, d, e, id;
                    a = null;
                    b = null;
                    d = null;
                    e = null;
                    id = null;
                    int count = 0;
                    while (res.moveToNext()) {
                        if (res.getString(1).matches(c)) {
                            count = 1;
                            id = res.getString(0);
                            a = res.getString(1);
                            b = res.getString(2);
                            d = res.getString(3);
                            e = res.getString(4);
                        }
                    }
                    // if child exists then moving to the new screen
                    if (count == 1) { // passing the values recieved from the database in oreder to be displayed in the update screen
                        Intent i = new Intent(SearchForChildActivity.this, UpdateInfoActivity.class);
                        i.putExtra("oldvalues", new String[]{a, b, d, e, id});
                        startActivity(i);
                    }// if child does not exists then giving a respective message
                    else {
                        Toast temp
                                = Toast.makeText(SearchForChildActivity.this, "child does not exist", Toast.LENGTH_SHORT);
                        temp.show();
                    }
                }
            }
        }
    else{ // calling the screen to add information about child


            if (v.getId() == R.id.submit) {

                EditText p1 = (EditText) findViewById(R.id.fname);
                // validating that fields are filled by the user
                if(TextUtils.isEmpty((p1.getText().toString())))
                {
                    Toast temp = Toast.makeText(SearchForChildActivity.this,"please dont leave any field blank",Toast.LENGTH_SHORT);
                    temp.show();
                }
                else {

                    // first name of the child entered by the user
                    String c = p1.getText().toString();
                    // checking whether that firstname exists in the database so call to database helper class is made
                    Cursor res = new DatabaseHelper(this).getchildfomfname(c);
                    int count =0;
                    while(res.moveToNext()){
                        if(res.getString(1).matches(c)) {
                            count=1;
                        }
                    }
                    if(count ==1)
                    {
                        Intent i = new Intent(SearchForChildActivity.this, AddInfoActivity.class);
                        //passing the first name of child to the next activity using putExtra as the first name of the child have to be displayed on the top of next activity
                        i.putExtra("oldvalues",new String[]{c});
                        startActivity(i);
                    }
                    else{
                        // if child does not exists in the database then information about him/her cannot be added
                        Toast temp = Toast.makeText(SearchForChildActivity.this,"child does not exist....add child first",Toast.LENGTH_SHORT);
                        temp.show();}
                }
            }
    }
}}
