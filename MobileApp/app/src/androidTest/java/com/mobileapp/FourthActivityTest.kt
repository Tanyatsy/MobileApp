package com.mobileapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FourthActivityTest : TestCase() {

    @Test
    fun buttonsClickTest() {
        val activityScenario = ActivityScenario.launch(FourthActivity::class.java)

        onView(withId(R.id.translateButton))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withId(R.id.saveButton))
            .check(matches(isDisplayed()))
            .perform(click())
    }

    @Test
    fun textViewTest() {
        val activityScenario = ActivityScenario.launch(FourthActivity::class.java)

        onView(withId(R.id.textView))
            .check(matches(withText("Translate your word")))
    }

    @Test
    fun editTextViewTest() {
        val activityScenario = ActivityScenario.launch(FourthActivity::class.java)

        onView(withId(R.id.inputTextWord))
            .check(matches(withHint("Enter your word")))

        onView(withId(R.id.inputTextTranslated))
            .check(matches(withHint("translation")))

        onView(withId(R.id.inputTextWord))
            .perform(click())
            .perform(replaceText("Test"))
            .check(matches(withText("Test")))
    }
}