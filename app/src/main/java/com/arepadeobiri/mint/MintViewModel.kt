package com.arepadeobiri.mint

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arepadeobiri.mint.cardDetailsDataModel.CardDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MintViewModel @ViewModelInject constructor(private val repo: MintRepository) : ViewModel() {


    private val _cardDetails = MutableLiveData<Response>()
    val cardDetails get() = _cardDetails


    fun getCardDetails(cardNumber: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            try {
                cardDetails.postValue(Response.Success(repo.getCardDetails(cardNumber)))
            } catch (t: Throwable) {
                cardDetails.postValue(Response.Error("Could not decipher card. Please enter the card number again"))
            }
        }
    }


    sealed class Response {
        data class Error(val message: String) : Response()
        class Success(val response: CardDetailsResponse) : Response()


    }


}