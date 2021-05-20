package com.mobileapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mobileapp.views.ThirdActivity
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ThirdActivityTest : TestCase() {

    @Test(expected = PerformException::class)
    fun testRecycleView() {
        val activityScenario = ActivityScenario.launch(ThirdActivity::class.java)

        onView(ViewMatchers.withId(R.id.recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.recyclerView)).perform(ViewActions.swipeUp())
        onView(ViewMatchers.withId(R.id.recyclerView)).perform(ViewActions.swipeDown())
    }
}