package br.com.wobbu.darksky.utils

class GlobalMethods {
    companion object {
        val isRunningTest: Boolean by lazy {
            try {
                Class.forName("android.support.test.espresso.Espresso")
                true
            } catch (e: ClassNotFoundException) {
                false
            }
        }
    }
}