package com.example.ChildrenRecords.BasicInstrumentTest;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;

import com.example.ChildrenRecords.ChildrenRecord.R;
import com.example.ChildrenRecords.UI.MainActivity;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 *Created by Mayank Sharma
 * @Date - 06-05-2016
 *
 * Used to test whether all button properly appear on the main activity
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {
    public ApplicationTest() {
        super(MainActivity.class);
    }

    @Override
    protected  void setUp() throws Exception{
        super.setUp();
    }
    @SmallTest
    public void testAddChildButton() throws Exception {
        Button bt = (Button)getActivity().findViewById(R.id.addchild);
        assertNotNull(bt);
    }

    @Override
    protected  void tearDown() throws Exception{
        super.tearDown();
    }




    @SmallTest
    public void testUpdateInfoButton() throws Exception {
        Button bt = (Button)getActivity().findViewById(R.id.updateinfo);
        assertNotNull(bt);
    }

    @SmallTest
    public void testAddInfoButton() throws Exception {
        Button bt = (Button)getActivity().findViewById(R.id.addinfo);
        assertNotNull(bt);
    }

    @SmallTest
    public void testViewInfoButton() throws Exception {
        Button bt = (Button)getActivity().findViewById(R.id.info);
        assertNull(bt);
    }

}