package com.example.ChildrenRecords.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ChildrenRecords.ChildrenRecord.R;

/**
 * Created by Mayank Sharma
 * @Date - 06-05-2016
 *
 * The Main Activity. It Has Button which Directs to the respective screens
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
// call to respective activities are made depending upon the button pressed

    /**
     * It takes to the activity_add_child
     * @param v holds value for activity_main
     */
    public void addchild(View v){

        if(v.getId() == R.id.addchild){

            Intent i = new Intent(MainActivity.this,AddChildActivity.class);

            startActivity(i);
        }
    }

    /**
     * It takes to the activity_search_for_child
     * @param v holds value for activity_main
     */
    public void updateinfo(View v){

        if(v.getId() == R.id.updateinfo){

            Intent i = new Intent(MainActivity.this,SearchForChildActivity.class);
            i.putExtra("nextscreen","updateinfo");
            startActivity(i);
        }
    }

    /**
     * It takes to the activity_search_for_child (activity  reuse)
     * @param v holds value for activity_main
     */
    public void addinfo(View v){

        if(v.getId() == R.id.addinfo){

            Intent i = new Intent(MainActivity.this,SearchForChildActivity.class);
            i.putExtra("nextscreen","addinfo");
            startActivity(i);
        }
    }

    /**
     * It takes to the activity_select_info_view
     * @param v holds value for activity_main
     */
    public void viewinfo(View v){

        if(v.getId() == R.id.viewinfo){

            Intent i = new Intent(MainActivity.this,SelectInfoViewActivity.class);

            startActivity(i);
        }
    }
}
