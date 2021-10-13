package com.example.app.api



import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiRegisterInterface {

    @FormUrlEncoded
    @POST("user/add/")
    fun adduser(
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
    ): Call<List<RegisterResponse>>



    @POST("user/login")
    @FormUrlEncoded
    fun loginUser(@Field("amka") amka:String?
    ): Call<ResponseBody?>?



}