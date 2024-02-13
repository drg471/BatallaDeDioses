package com.drg.rustandrevolt.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.retrofit.RetrofitService
import com.drg.rustandrevolt.sharedpreferences.MusicPreferences
import com.drg.rustandrevolt.sound.FX
import com.drg.rustandrevolt.sound.MusicPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var retrofitService: RetrofitService

    lateinit var context: Context


    fun initRetrofit(){
        //******************************************************
        //RETROFIT
        viewModelScope.launch() {
            val movies = retrofitService.listPopularMovies("5d7c3467997b5cebca56a598a9a37d67", "US")
            var iter: Int = 1
            for (movie in movies.results){
                Log.i("movie top ${iter}", movie.original_title)
                iter++
            }
        }
        //******************************************************
    }

    fun buttonSound(){
        if (MusicPreferences.isMusicEnabledCompanion) {
            val musicPlayer = MusicPlayer(context)
            musicPlayer.playFX(FX.FxButton1)
        }
    }


}