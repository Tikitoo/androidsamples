package me.tikitoo.androiddemo;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity mMainActivity;
    private Button mButton;

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMainActivity = getActivity();
        mButton = (Button) mMainActivity.findViewById(R.id.anim_btn);

        testPreconditions();
    }

    private void testPreconditions() {
        assertNotNull("mMainActivity is null", mMainActivity);
        assertNotNull("mButton is null", mButton);
    }


}
