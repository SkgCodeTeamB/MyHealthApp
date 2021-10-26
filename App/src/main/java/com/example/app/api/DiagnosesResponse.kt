package com.example.app.api

import com.example.app.Models.Appointments.Doctor

data class DiagnosesResponse(
    val date: String,
    val text: String,
    val doctor: Doctor
)