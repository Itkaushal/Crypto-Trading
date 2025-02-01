package com.app.kaushalprajapati.myapplication.cryptoApi
import retrofit2.http.GET
import retrofit2.http.Query

data class CryptoCoin(
    val id: String,
    val symbol: String,
    val name: String,
    val current_price: Double,
    val previous_price: Double,
    val image: String,
    val market_cap: Long,
    val price_change_percentage_24h: Double
)

interface CryptoApi{
    @GET("coins/markets")
    suspend fun getCryptoCoins(
        @Query("vs_currency") currency: String = "inr",
    ): List<CryptoCoin>

}
