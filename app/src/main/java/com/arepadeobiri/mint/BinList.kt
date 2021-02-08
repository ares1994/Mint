package com.arepadeobiri.mint

import com.arepadeobiri.mint.cardDetailsDataModel.CardDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface BinList {


    @GET("{cardNumber}")
    suspend fun getCardDetails(
        @Path("cardNumber") cardNumber: String
    ): CardDetailsResponse
}