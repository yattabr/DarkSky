package br.com.wobbu.darksky

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BuildConfig.PAID_VERSION) {
            view_ad.visibility = View.GONE
            view_extra_info.visibility = View.VISIBLE
        } else {
            view_ad.visibility = View.VISIBLE
            view_extra_info.visibility = View.GONE
        }
    }
}
