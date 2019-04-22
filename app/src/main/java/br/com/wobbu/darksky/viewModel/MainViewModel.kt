package br.com.wobbu.darksky.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.wobbu.darksky.repository.APIClient

class MainViewModel : ViewModel() {

    var forecastObserver = MutableLiveData<Any>()
    var isCelsius = false

    // Call API from Repository
    // I'll put the Latitude and Longitude hard coded to avoid use Google API to retrieve the real LatLong
    fun getForecast(latitude: String, longitude: String) {
        APIClient.getAPIInstance.getForecast(latitude, longitude, forecastObserver)
    }

    // Converts to celcius
    fun convertFahrenheitToCelcius(fahrenheit: String): String {
        isCelsius = true
        val number = (fahrenheit.toFloat() - 32) * 5 / 9
        return "%.0f".format(number)
    }

    // Converts to fahrenheit
    fun convertCelciusToFahrenheit(celsius: String): String {
        isCelsius = false
        val number = celsius.toFloat() * 9 / 5 + 32
        return "%.0f".format(number)
    }

}