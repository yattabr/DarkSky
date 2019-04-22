package br.com.wobbu.darksky.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson


class ForecastResponse(var currently: Currently, var hourly: Hourly, var daily: Daily) {
    class Deserializer : ResponseDeserializable<ForecastResponse> {
        override fun deserialize(content: String): ForecastResponse? {
            val gson = Gson().newBuilder().serializeNulls().create()
            return gson.fromJson(content, ForecastResponse::class.java)
        }
    }
}

data class Currently(var summary: String, var temperature: String)
data class Hourly(var summary: String)
data class Daily(var summary: String)