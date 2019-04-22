package br.com.wobbu.darksky

import br.com.wobbu.darksky.viewModel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ExampleUnitTest {

    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun celsiusToFahrenheit_isCorrect() {
        val temperature = viewModel.convertCelciusToFahrenheit("23")
        assertEquals(temperature, "73")
    }

    @Test
    fun fahrenheitToCelsius_isCorrect() {
        val temperature = viewModel.convertFahrenheitToCelcius("73")
        assertEquals(temperature, "23")
    }
}
