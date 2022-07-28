package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@LargeTest

public class GetStartTest {


    @Rule
    public ActivityScenarioRule<ActivityGetStarted> ActivityGetStartedActivityScenarioRule = new ActivityScenarioRule<>(ActivityGetStarted.class);

    @Test
    public void testAddButton() {

        /*
        add
         */
        onView(withId(R.id.logo)).check(matches(isDisplayed()));
        onView(withId(R.id.GetStartedbutton)).perform(click());

}}
