package com.jef4tech.tranzmeo.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class UserData(
    @SerializedName("address")
    val address: Address,
    @SerializedName("age")
    val age: Int,
    @SerializedName("bank")
    val bank: Bank,
    @SerializedName("birthDate")
    val birthDate: String,
    @SerializedName("bloodGroup")
    val bloodGroup: String,
    @SerializedName("company")
    val company: Company,
    @SerializedName("domain")
    val domain: String,
    @SerializedName("ein")
    val ein: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("eyeColor")
    val eyeColor: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("hair")
    val hair: Hair,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("ip")
    val ip: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("macAddress")
    val macAddress: String,
    @SerializedName("maidenName")
    val maidenName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("ssn")
    val ssn: String,
    @SerializedName("university")
    val university: String,
    @SerializedName("userAgent")
    val userAgent: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("weight")
    val weight: Double
) {
    @Keep
    data class Address(
        @SerializedName("address")
        val address: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("coordinates")
        val coordinates: Coordinates,
        @SerializedName("postalCode")
        val postalCode: String,
        @SerializedName("state")
        val state: String
    ) {
        @Keep
        data class Coordinates(
            @SerializedName("lat")
            val lat: Double,
            @SerializedName("lng")
            val lng: Double
        )
    }

    @Keep
    data class Bank(
        @SerializedName("cardExpire")
        val cardExpire: String,
        @SerializedName("cardNumber")
        val cardNumber: String,
        @SerializedName("cardType")
        val cardType: String,
        @SerializedName("currency")
        val currency: String,
        @SerializedName("iban")
        val iban: String
    )

    @Keep
    data class Company(
        @SerializedName("address")
        val address: Address,
        @SerializedName("department")
        val department: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("title")
        val title: String
    ) {
        @Keep
        data class Address(
            @SerializedName("address")
            val address: String,
            @SerializedName("city")
            val city: String,
            @SerializedName("coordinates")
            val coordinates: Coordinates,
            @SerializedName("postalCode")
            val postalCode: String,
            @SerializedName("state")
            val state: String
        ) {
            @Keep
            data class Coordinates(
                @SerializedName("lat")
                val lat: Double,
                @SerializedName("lng")
                val lng: Double
            )
        }
    }

    @Keep
    data class Hair(
        @SerializedName("color")
        val color: String,
        @SerializedName("type")
        val type: String
    )
}