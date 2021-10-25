package com.example.app

import com.example.app.Models.Appointments.*
import com.example.app.Models.Appointments.Field
import com.example.app.Models.Vaccinations.Vaccination
import com.example.app.Models.Vaccinations.Vaccine
import com.example.app.api.AppointmentResponse
import com.example.app.api.PrescriptionResponse
import com.example.app.api.RegisterResponse
import com.example.dummyappointmentsapp.Models.LoginInfo
import com.example.dummyappointmentsapp.Models.LoginResponse
import com.example.dummyappointmentsapp.Models.User
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


    //LOGIN API CALLS

    @POST("/user/login")
    fun login(@Body loginInfo: LoginInfo): Call<LoginResponse>


    //REGISTER API CALLS

    @FormUrlEncoded
    @POST("user/register/")
    fun addUser(
        @retrofit2.http.Field("name") name: String,
        @retrofit2.http.Field("surname") surname: String,
        @retrofit2.http.Field("email") email: String,
        @retrofit2.http.Field("phone") phone: String,
        @retrofit2.http.Field("birthday") birthday: String,
        @retrofit2.http.Field("bloodtype") bloodtype: String,
        @retrofit2.http.Field("amka") amka: String,
        @retrofit2.http.Field("familydoctor") familydoctor: String,
        @retrofit2.http.Field("address") address: String,
        @retrofit2.http.Field("city") city: String,
        @retrofit2.http.Field("postalcode") postalcode: String
    ): Call<RegisterResponse>


    // PRESCRIPTION API CALL
    @GET("prescription/{user_id}")
    fun getUsersPrescriptions(
        @Path(
            value = "user_id",
            encoded = true
        ) fullUrl: String?
    ): Call<List<PrescriptionResponse>>


    //PERSONAL DATA API CALLS

    @GET("user/{user_id}")
    fun getPersonalData(
        @Path(
            value = "user_id",
            encoded = true
        ) fullUrl: String?
    ): Call<List<User>>

    // Appointment API CALL
    @GET("appointment/{user_id}")
    fun getUsersAppointments(
        @Path(
            value = "user_id",
            encoded = true
        ) fullUrl: String?
    ): Call<List<AppointmentResponse>>

    companion object {
        var BASE_URL = "http://192.168.1.5:5000/"
        //var BASE_URL = "http://192.168.1.3:5000/"

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}