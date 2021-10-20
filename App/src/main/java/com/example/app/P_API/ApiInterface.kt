package com.example.app.P_API

import com.example.app.Models.Appointments.*
import com.example.app.Models.Appointments.Field
import com.example.app.Models.Vaccinations.Vaccination
import com.example.app.Models.Vaccinations.Vaccine
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET


interface ApiInterface {

    //BOOK APPOINTMENT API CALLS

    @GET("field/")
    fun getFields(): Call<List<Field>>

    @GET("doctor/{field}")
    fun getDoctors(
        @Path(
            value = "field",
            encoded = true
        ) fullUrl: String?
    ): Call<List<Doctor>>

    @POST("/appointment/bookedslots")
    fun getBookedSlots(@Body info: Info): Call<List<TimeSlot>>

    @POST("/appointment/add")
    fun addBooking(@Body appointment: Appointment): Call<Appointment>


    //COUNTERS API CALLS

    @GET("appointment/count/{user_id}")
    fun getAppointmentsCounter(
        @Path(
            value = "user_id",
            encoded = true
        ) fullUrl: String?
    ): Call<String>

    @GET("prescription/count/{user_id}")
    fun getPrescriptionsCounter(
        @Path(
            value = "user_id",
            encoded = true
        ) fullUrl: String?
    ): Call<String>

    @GET("diagnose/count/{user_id}")
    fun getDiagnosesCounter(
        @Path(
            value = "user_id",
            encoded = true
        ) fullUrl: String?
    ): Call<String>


    //VACCINATIONS API CALLS

    @GET("vaccine/")
    fun getVaccines(): Call<List<Vaccine>>

    @GET("vaccination/{user_id}")
    fun getVaccinations(
        @Path(
            value = "user_id",
            encoded = true
        ) fullUrl: String?
    ): Call<List<Vaccination>>

    companion object {
        var BASE_URL = "http://192.168.1.5:5000/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}