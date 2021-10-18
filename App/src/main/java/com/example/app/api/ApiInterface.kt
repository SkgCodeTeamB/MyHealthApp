package com.example.app.api



import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("user/register/")
    fun addUser(
        @Field("name") name: String,
        @Field("surname") surname: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("birthday") birthday: String,
        @Field("bloodtype") bloodtype: String,
        @Field("amka") amka: String,
        @Field("familydoctor") familydoctor: String,
        @Field("address") address: String,
        @Field("city") city: String,
        @Field("postalcode") postalcode: String
    ): Call<RegisterResponse>



    @FormUrlEncoded
    @POST("user/login/")
    fun loginUser(@Field("amka") amka:String): Call<LoginResponse>



}