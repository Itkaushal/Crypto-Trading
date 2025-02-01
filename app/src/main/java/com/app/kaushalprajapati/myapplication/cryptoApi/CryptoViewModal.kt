package com.app.kaushalprajapati.myapplication.cryptoApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class CryptoViewModal: ViewModel() {
    private val crypto_list = MutableLiveData<List<CryptoCoin>>()
    val cryptoList : MutableLiveData<List<CryptoCoin>> = crypto_list

    init {
        fetchCryptoData()
        startLiveUpdates()

    }

    fun fetchCryptoData(){
        viewModelScope.launch {
            try {

                val response = RetrofitInstance.api.getCryptoCoins()
                cryptoList.postValue(response)

            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    private fun startLiveUpdates() {
        val ws = URI("wss://stream.coingecko.com")
        val client = object : WebSocketClient(ws) {
            override fun onOpen(handshakedata: ServerHandshake?) {}
            override fun onMessage(message: String?) {
                val updatedData = Gson().fromJson(message, CryptoCoin::class.java)
                cryptoList.value = cryptoList.value?.map { if (it.id == updatedData.id) updatedData else it }
            }
            override fun onClose(code: Int, reason: String?, remote: Boolean) {}
            override fun onError(ex: Exception?) {}
        }
        client.connect()
    }
}