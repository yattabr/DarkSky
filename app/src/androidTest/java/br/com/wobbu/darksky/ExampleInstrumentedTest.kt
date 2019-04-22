package br.com.wobbu.darksky

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.wobbu.darksky.activities.MainActivity
import br.com.wobbu.darksky.model.Currently
import br.com.wobbu.darksky.model.Daily
import br.com.wobbu.darksky.model.ForecastResponse
import br.com.wobbu.darksky.model.Hourly
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)
    private lateinit var forecast: ForecastResponse

    @Before
    fun setup() {
        forecast = ForecastResponse(Currently("", "73"), Hourly(""), Daily(""))
    }

    @Test
    fun convertCelsiusToFahrenheit() {
        activityTestRule.activity.mainViewModel.forecastObserver.postValue(forecast)

        Thread.sleep(500)
        Espresso.onView(allOf(withId(R.id.text_temperature))).perform(click())

        fahrenheit("73")
    }

    @Test
    fun convertFahrenheitToCelsius() {
        activityTestRule.activity.mainViewModel.forecastObserver.postValue(forecast)
        celsius("23")

        Thread.sleep(500)
        Espresso.onView(allOf(withId(R.id.text_temperature))).perform(click())

        fahrenheit("73")
        Thread.sleep(500)
        Espresso.onView(allOf(withId(R.id.text_temperature))).perform(click())

        celsius("23")
    }

    private fun celsius(value: String) {
        Espresso.onView(allOf(withId(R.id.text_temperature), withText(value)))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        Espresso.onView(allOf(withId(R.id.text_temperature_type), withText("˚C")))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

    }

    private fun fahrenheit(value: String) {
        Espresso.onView(allOf(withId(R.id.text_temperature), withText(value)))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        Espresso.onView(allOf(withId(R.id.text_temperature_type), withText("˚F")))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

    }
}
