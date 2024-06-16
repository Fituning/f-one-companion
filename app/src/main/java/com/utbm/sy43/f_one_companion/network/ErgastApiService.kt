package com.utbm.sy43.f_one_companion.network


import retrofit2.Retrofit
import retrofit2.http.GET
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.utbm.sy43.f_one_companion.data.model.serializable_model.MRData
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

private const val BASE_ERGAST_URL = "https://ergast.com"

private val json = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_ERGAST_URL)
    .build()


interface ErgastApiService {
    @GET("api/f1/current/drivers.json")
    suspend fun getDrivers() : MRData

    @GET("api/f1/current/driverStandings.json")
    suspend fun getDriversStandings() : MRData
    @GET("api/f1/current/constructorStandings.json")
    suspend fun getTeamStandings() : MRData

}

object ErgastApi {
    val retrofitService : ErgastApiService by lazy {
        retrofit.create(ErgastApiService::class.java)
    }
}