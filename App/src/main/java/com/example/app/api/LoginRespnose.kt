package com.example.app.api


data class LoginResponse(
    val token: String,
    val _id: String,
    val name: String,
    val surname: String,
    val email: String,
    val phone: String,
    val birthday: String,
    val bloodtype: String,
    val amka: String,
    val familydoctor: String,
    val address: String,
    val city: String,
    val postalcode: String
)