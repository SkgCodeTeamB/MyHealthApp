package com.example.app.api

import com.example.app.Models.Appointments.Doctor

data class AppointmentResponse(
    val date: String,
    val time: String,
    val doctor: Doctor
)