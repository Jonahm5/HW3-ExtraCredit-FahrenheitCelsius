package com.example.fahrenheitcelsius

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp(){
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }
    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun tempCheckCold(){
        scenario.onActivity {activity ->
            activity.checkTemperature(20.0)
        }
        onView(withText("I wish it were warmer")).check(matches(isDisplayed()))
    }
    @Test
    fun tempCheckWarm(){
        scenario.onActivity {activity ->
            activity.checkTemperature(25.0)
        }
        onView(withText("I wish it were colder")).check(matches(isDisplayed()))
    }
}