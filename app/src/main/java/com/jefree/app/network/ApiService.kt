package com.jefree.app.network

import com.jefree.app.model.LoginResponse
import com.jefree.app.model.Order
import com.jefree.app.model.Profile.EditProfileResponse
import com.jefree.app.model.Profile.ProfileResponse
import com.jefree.app.model.Sale.CalculateResponse
import com.jefree.app.model.UserResponse
import com.jefree.app.model.order.AllOrderResponse
import com.jefree.app.model.order.OrderResponse
import com.jefree.app.model.riwayatpenjualan.RiwayatResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("order")
    @Headers("Content-Type:application/json")
    fun getOrders(): Call<AllOrderResponse>

    //getUsers
    @GET("users")
    @Headers("Content-Type:application/json")
    fun getUsers(): Call<List<Order>>

    //register
    @Headers("Content-Type:application/json")
    @POST("auth/register")
    fun register(@Body userMap: Map<String, String>): Call<UserResponse>

    //login user
    @Headers("Content-Type:application/json")
    @POST("auth/login")
    fun login(
        @Body loginMap: Map<String, String>
    ): Call<LoginResponse>

    @PUT("users/{id}")
    @Multipart
    fun editUser(
        @Path("id") id: Int,
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part avatar : MultipartBody.Part?
    ): Call<EditProfileResponse>

    @Headers("Content-Type:application/json")
    @GET("users/{id}")
    fun getSingleUser(@Path("id") id: Int): Call<ProfileResponse>

    //calculate
    @Headers("Content-Type:application/json")
    @POST("order/calculate")
    fun calculate(
            @Body map: Map<String, String>
    ): Call<CalculateResponse>

    //create order
    @Headers("Content-Type:application/json")
    @POST("order")
    fun createOrder(
            @Body map: Map<String, String>
    ): Call<OrderResponse>

    //get riwayat
    @Headers("Content-Type:application/json")
    @GET("order/history/{id}")
    fun getRiwayat(@Path("id") id: Int): Call<RiwayatResponse>
}