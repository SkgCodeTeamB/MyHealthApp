package com.example.app.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface Hospital {
    @GET("hospital/")
    fun getHospitals() : Call<List<HospitalResponseItem>>
}