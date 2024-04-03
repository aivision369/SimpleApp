package com.aivison.simpleapp.data.repository

import com.aivison.simpleapp.data.Api
import com.aivison.simpleapp.model.Audio
import com.aivison.simpleapp.model.GetDataDetails

class AudioRepository constructor(private val api: Api) {

    suspend fun getAudioList(): List<Audio> {
        return api.getAudioList()
    }

    suspend fun getAllData(): GetDataDetails {
        return api.getAllData()
    }
}
