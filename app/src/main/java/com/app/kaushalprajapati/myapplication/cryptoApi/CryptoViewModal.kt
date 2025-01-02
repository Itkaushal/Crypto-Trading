package com.app.kaushalprajapati.myapplication.cryptoApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CryptoViewModal: ViewModel() {
    private val crypto_list = MutableLiveData<List<CryptoCoin>>()
    val cryptoList : MutableLiveData<List<CryptoCoin>> = crypto_list


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
}