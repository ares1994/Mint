package com.arepadeobiri.mint

import com.arepadeobiri.mint.cardDetailsDataModel.CardDetailsResponse
import javax.inject.Inject

class MintRepository @Inject constructor(
    private val binList: BinList
) {

    suspend fun getCardDetails(cardNumber: String): CardDetailsResponse =
        binList.getCardDetails(cardNumber)

}