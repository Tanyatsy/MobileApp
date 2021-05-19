package com.mobileapp

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mobileapp.views.FirstActivity
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest : TestCase() {
    @Test
    fun testNavigationToInGameScreen() {
        val activityScenario = ActivityScenario.launch(FirstActivity::class.java)

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        onView(withId(R.id.bottomNavigation)).perform(click())
        onView(withId(R.id.secondFragment)).perform(click())
        onView(withId(R.layout.activity_second)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.layout.activity_main)).check(matches(isDisplayed()))
    }
}