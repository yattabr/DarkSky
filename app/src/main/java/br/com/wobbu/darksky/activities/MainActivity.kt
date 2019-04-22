package br.com.wobbu.darksky.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.wobbu.darksky.BuildConfig
import br.com.wobbu.darksky.R
import br.com.wobbu.darksky.model.ForecastResponse
import br.com.wobbu.darksky.viewModel.MainViewModel
import com.github.kittinunf.fuel.core.FuelError
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initObserver()

        mainViewModel.getForecast("51.507391", "-0.126963")

        if (BuildConfig.PAID_VERSION) {
            view_ad.visibility = View.GONE
            view_extra_info.visibility = View.VISIBLE
        } else {
            view_ad.visibility = View.VISIBLE
            view_extra_info.visibility = View.GONE
        }

    }

    fun initObserver() {
        mainViewModel.forecastObserver.observe(this, Observer {
            if (it is ForecastResponse) {
                initUI(it)
            } else if (it is FuelError) {
                //TODO show ErrorMessage
            }
        })
    }

    private fun initUI(response: ForecastResponse) {
        convertTemperature(response.currently.temperature)
        text_temperature.setOnClickListener(temperatureClick)
        text_hourly.text = response.hourly.summary
        text_daily.text = response.daily.summary
        pb_loading.visibility = View.GONE
    }

    val temperatureClick = View.OnClickListener {
        convertTemperature(text_temperature.text.toString())
    }

    private fun convertTemperature(temperature: String) {
        val temperatureType: String
        val convertedTemperature: String
        if (!mainViewModel.isCelsius) {
            convertedTemperature = mainViewModel.convertFahrenheitToCelcius(temperature)
            temperatureType = "˚C"
        } else {
            convertedTemperature = mainViewModel.convertCelciusToFahrenheit(temperature)
            temperatureType = "˚F"
        }
        text_temperature.text = convertedTemperature
        text_temperature_type.text = temperatureType
    }
}
