package com.arepadeobiri.mint.cardDetailsDataModel

import com.google.gson.annotations.SerializedName

data class CardDetailsResponse(

	@field:SerializedName("number")
	val number: Number? = null,

	@field:SerializedName("country")
	val country: Country? = null,

	@field:SerializedName("bank")
	val bank: Bank? = null,

	@field:SerializedName("scheme")
	val scheme: String? = null,

	@field:SerializedName("prepaid")
	val prepaid: Boolean? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("brand")
	val brand: String? = null
)

data class Bank(

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Country(

	@field:SerializedName("emoji")
	val emoji: String? = null,

	@field:SerializedName("latitude")
	val latitude: Int? = null,

	@field:SerializedName("alpha2")
	val alpha2: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("numeric")
	val numeric: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("longitude")
	val longitude: Int? = null
)

data class Number(

	@field:SerializedName("length")
	val length: Int? = null,

	@field:SerializedName("luhn")
	val luhn: Boolean? = null
)
