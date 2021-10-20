package com.example.app.api

data class PersonalDataResponse(
    val name: String,
    val surname: String,
    val birthday: String,
    val bloodtype: String,
    val amka: String,
    val familydoctor: String
)