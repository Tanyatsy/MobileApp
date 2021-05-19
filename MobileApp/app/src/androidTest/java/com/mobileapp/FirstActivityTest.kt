package com.mobileapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirstActivityTest : TestCase() {

    @Test
    fun testMainScreen() {
        val activityScenario = ActivityScenario.launch(FirstActivity::class.java)

        onView(withId(R.layout.activity_main)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun testNavigationToSecondActivity(){
        val activityScenario = ActivityScenario.launch(FirstActivity::class.java)

        onView(withId(R.id.add)).perform(click())
        onView(withId(R.layout.activity_second)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun testNavigationToFourthActivity(){
        val activityScenario = ActivityScenario.launch(FirstActivity::class.java)

        onView(withId(R.id.translate)).perform(click())
        onView(withId(R.layout.activity_fourth)).check(ViewAssertions.matches(isDisplayed()))
    }
}