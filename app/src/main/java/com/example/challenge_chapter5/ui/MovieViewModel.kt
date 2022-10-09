package com.example.challenge_chapter5.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challenge_chapter5.model.Item
import com.example.challenge_chapter5.model.Movie
import com.example.challenge_chapter5.service.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private val liveDataMovieDatabase : MutableLiveData<Movie?> = MutableLiveData()
    private val itemLiveData : MutableLiveData<Item> = MutableLiveData()

    fun getLiveDataMovie() : MutableLiveData<Movie?> = liveDataMovieDatabase
    fun getLiveDataItem () : MutableLiveData<Item> = itemLiveData

    fun showItemListData(){
        MovieApiService.instance.getDetailFilm()
            .enqueue(object : Callback<Movie> {
                override fun onResponse(
                    call: Call<Movie>,
                    response: Response<Movie>
                ) {
                    if (response.isSuccessful){
                        liveDataMovieDatabase.postValue(response.body())
                    }else{
                        liveDataMovieDatabase.postValue(null)
                        Log.d("notSuccess", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    liveDataMovieDatabase.postValue(null)
                    Log.d("Failed",t.message.toString())
                }

            })
    }

}