package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HomeFragmentTest {
    @Rule
    public ActivityScenarioRule<ActivityHome> activityRule =
            new ActivityScenarioRule<ActivityHome>(ActivityHome.class);

    @Test
    public void testAddButton() {

        onView(withId(R.id.addButtonPro)).perform(click());
        onView(withId(R.id.editTextSystolic)).perform(ViewActions.typeText("130"));
        onView(withId(R.id.editTextDiastolic)).perform(ViewActions.typeText("80"));
        onView(withId(R.id.editTextHeartRate)).perform(ViewActions.typeText("80"));
        Espresso.pressBack();
        onView(withId(R.id.editTextDate)).perform(ViewActions.typeText("12-07-21"));
        Espresso.pressBack();
        onView(withId(R.id.editTextTime)).perform(ViewActions.typeText("11:00"));
        Espresso.pressBack();
        onView(withId(R.id.editTextComment)).perform(ViewActions.typeText("Lying"));
        Espresso.pressBack();
        onView(withId(R.id.Add_button)).perform(click());

    }

}