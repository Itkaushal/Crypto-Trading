package com.app.kaushalprajapati.myapplication.cryptoApi

import android.icu.util.Currency
import retrofit2.http.GET
import retrofit2.http.Query

data class CryptoCoin(
    val id: String,
    val symbol: String,
    val name: String,
    val current_price: Double,
    val previous_price: Double?=null,
    val image: String,
)

interface CryptoApi{
    @GET("coins/markets")
    suspend fun getCryptoCoins(
        @Query("vs_currency") currency: String = "usd",
    ): List<CryptoCoin>

}