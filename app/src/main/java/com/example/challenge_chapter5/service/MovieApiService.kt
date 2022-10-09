package com.example.challenge_chapter5.service

import com.example.challenge_chapter5.model.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieApiService {

    @GET("3/list/1?api_key=c1bc659015ac6e62beb57434d9793ed9&language=en-US")
    fun getDetailFilm() : Call<Movie>

    companion object {
        const val  BASE_URL = "https://api.themoviedb.org/"

        val instance: MovieApiService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(MovieApiService::class.java)
        }
    }
}