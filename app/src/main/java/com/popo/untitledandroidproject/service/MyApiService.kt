package com.popo.untitledandroidproject.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.popo.untitledandroidproject.model.OpenDataModels.OpenDataResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://data.opendatasoft.com/api/records/1.0/search/?dataset=cats-in-movies%40public&facet=produced_by&facet=directed_by&facet=year"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface MyApiService {
    @GET(BASE_URL)
    fun getPropertiesAsync(): Deferred<OpenDataResponse>
}
object MyApi {
    val retrofitService : MyApiService by lazy {
        retrofit.create(MyApiService::class.java) }
}
data class MarsProperty(
    val records: MovieMars
)

data class MovieMars(
    val title: String,
    val url_poster: String,
    val year: Int,
    val directed_by: String,
    val wikiUrl: String
)