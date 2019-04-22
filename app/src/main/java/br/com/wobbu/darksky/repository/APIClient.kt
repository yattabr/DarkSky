package br.com.wobbu.darksky.repository

import android.arch.lifecycle.MutableLiveData
import br.com.wobbu.darksky.model.ForecastResponse
import br.com.wobbu.darksky.utils.GlobalMethods
import com.github.kittinunf.fuel.Fuel

class APIClient {

    object getAPIInstance {
        private val isRunningTest: Boolean = GlobalMethods.isRunningTest
        private val url = "https://api.darksky.net/forecast/360df1e319e95044fe7efd7c859c85b8"

        fun getForecast(latitude: String, longitude: String, observer: MutableLiveData<Any>) {
            if (!this.isRunningTest) {
                Fuel.get("$url/$latitude,$longitude").responseObject(ForecastResponse.Deserializer()) { _, _, result ->
                    val (data, error) = result
                    if (data == null) {
                        observer.postValue(error)
                    } else {
                        observer.postValue(data)
                    }
                }
            }
        }
    }
}