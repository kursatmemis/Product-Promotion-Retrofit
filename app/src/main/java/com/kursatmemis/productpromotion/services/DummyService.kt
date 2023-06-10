package com.kursatmemis.productpromotion.services

import com.kursatmemis.productpromotion.models.Product
import com.kursatmemis.productpromotion.models.User
import com.kursatmemis.productpromotion.models.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DummyService {

    @POST("auth/login")
    fun login(@Body user: User): Call<UserResponse>

    @GET("products?limit=10")
    fun getProducts(): Call<Product>
}