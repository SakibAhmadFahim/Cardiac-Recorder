package com.example.cardiacrecorder;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiSelector;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivitySplashScreenTest {

    @Rule
    public ActivityScenarioRule<ActivitySplashScreen> activityRule;
    {
        activityRule = new ActivityScenarioRule<>(ActivitySplashScreen.class);
    }

    @Test
    public void TestAddMeasurement(){
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.GetStartedbutton)).perform(click());
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.addButtonPro)).perform(click());
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.editTextSystolic)).perform(ViewActions.typeText("120"));
        Espresso.onView(withId(R.id.editTextDiastolic)).perform(ViewActions.typeText("90"));
        Espresso.onView(withId(R.id.editTextHeartRate)).perform(ViewActions.typeText("95"));
        Espresso.pressBack(); //Back button
        Espresso.onView(withId(R.id.editTextDate)).perform(ViewActions.typeText("12-12-16"));
        Espresso.pressBack(); //Back button
        Espresso.onView(withId(R.id.editTextTime)).perform(ViewActions.typeText("16:12"));
        Espresso.pressBack(); //Back button
        Espresso. onView(withId(R.id.editTextComment)).perform(ViewActions.typeText("Health is good"));
        Espresso.pressBack(); //Back button
        Espresso.onView(withId(R.id.Add_button)).perform(click());
        SystemClock.sleep(5000);
        Espresso.pressBack(); //Back button

        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.addButtonPro)).perform(click());
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.editTextSystolic)).perform(ViewActions.typeText("125"));
        Espresso.onView(withId(R.id.editTextDiastolic)).perform(ViewActions.typeText("95"));
        Espresso.onView(withId(R.id.editTextHeartRate)).perform(ViewActions.typeText("75"));
        Espresso.pressBack(); //Back button
        Espresso.onView(withId(R.id.editTextDate)).perform(ViewActions.typeText("01-01-16"));
        Espresso.pressBack(); //Back button
        SystemClock.sleep(2500);
        Espresso.onView(withId(R.id.editTextTime)).perform(ViewActions.typeText("10:12"));
        Espresso.pressBack(); //Back button
        Espresso. onView(withId(R.id.editTextComment)).perform(ViewActions.typeText("Health is Not Good"));
        Espresso.pressBack(); //Back button
        SystemClock.sleep(3000);
        Espresso.onView(withId(R.id.Add_button)).perform(click());
        SystemClock.sleep(5000);
        Espresso.pressBack(); //Back button
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.records)).perform(click());
        SystemClock.sleep(5000);
    }

    @Test
    public void TestUpdateMeasurement() {

        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.GetStartedbutton)).perform(click());
        SystemClock.sleep(2500);
        Espresso.onView(withId(R.id.records)).perform(click());
        SystemClock.sleep(2500);
        Espresso.onView(withId(R.id.recycleView)).perform(click());
        SystemClock.sleep(2500);
        Espresso.onView(withId(R.id.Edit_buttonD)).perform(click());
        SystemClock.sleep(2500);

        Espresso.onView(withId(R.id.editTextSystolicED)).perform(clearText()).perform(ViewActions.typeText("150"));

        Espresso.onView(withId(R.id.editTextDiastolicED)).perform(clearText()).perform(ViewActions.typeText("100"));

        Espresso.onView(withId(R.id.editTextHeartRateED)).perform(clearText()).perform(ViewActions.typeText("105"));
        Espresso.pressBack(); //Back button
        SystemClock.sleep(2500);

        Espresso.onView(withId(R.id.editTextDateED)).perform(clearText()).perform(ViewActions.typeText("03-01-16"));
        Espresso.pressBack(); //Back button
        SystemClock.sleep(2500);

        Espresso.onView(withId(R.id.editTextTimeED)).perform(clearText()).perform(ViewActions.typeText("09:12"));
        Espresso.pressBack(); //Back button
        SystemClock.sleep(2500);

        Espresso. onView(withId(R.id.editTextCommentED)).perform(clearText()).perform(ViewActions.typeText("High blood Pressure"));
        Espresso.pressBack(); //Back button
        SystemClock.sleep(2500);

        Espresso.onView(withId(R.id.Update_buttonED)).perform(click());
        Espresso.pressBack(); //Back button
        SystemClock.sleep(2500);

        Espresso.onView(withId(R.id.home)).perform(click());
        SystemClock.sleep(2500);
        Espresso.onView(withId(R.id.records)).perform(click());
    }

    @Test
    public void TestDeleteMeasurement() {

        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.GetStartedbutton)).perform(click());
        SystemClock.sleep(2500);
        Espresso.onView(withId(R.id.records)).perform(click());
        SystemClock.sleep(2500);
        Espresso.onView(withId(R.id.recycleView)).perform(click());
        SystemClock.sleep(2500);
        Espresso.onView(withId(R.id.Delete_buttonD)).perform(click());

        Espresso.onView(withText("Yes")).perform(click());
        SystemClock.sleep(2500);
        Espresso.onView(withId(R.id.home)).perform(click());
        SystemClock.sleep(2500);
        Espresso.onView(withId(R.id.records)).perform(click());
        SystemClock.sleep(3000);

    }


}