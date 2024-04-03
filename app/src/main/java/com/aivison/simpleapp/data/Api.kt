package com.aivison.simpleapp.data

import com.aivison.simpleapp.model.Audio
import com.aivison.simpleapp.model.GetDataDetails
import retrofit2.http.GET

interface Api {

    @GET("/dev")
    suspend fun getAudioList(): List<Audio>


    @GET("")
    suspend fun getAllData(): GetDataDetails
}
