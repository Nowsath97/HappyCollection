package com.example.happycollection.view.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.happycollection.R
import org.junit.Assert.assertNotNull
import org.junit.*

class MainActivityTest {

    @Rule
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    private var activity: MainActivity? = null

    @BeforeClass
    fun setUp() {
        activity = mActivityRule.activity
    }

    @Test
    fun checkLaunch() {
        val view = activity?.findViewById<RecyclerView>(R.id.recyclerview)
        assertNotNull(view)
    }

    @Test
    fun checkRecyclerView() {
        Espresso.onView(withId(R.id.recyclerview))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            ))
    }

    @AfterClass
    fun tearDown() {
        activity = null
    }
}