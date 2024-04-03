package com.aivison.simpleapp.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aivison.simpleapp.data.repository.AudioRepository
import com.aivison.simpleapp.model.Audio
import com.aivison.simpleapp.model.GetDataDetails
import kotlinx.coroutines.launch
import javax.inject.Inject

class AudioViewModel @Inject constructor(private val repository: AudioRepository) : ViewModel() {


    private val _posts = MutableLiveData<List<Audio>>()
    val posts: LiveData<List<Audio>> get() = _posts

    fun fetchPosts() {
        viewModelScope.launch {
            val data = repository.getAudioList()
            _posts.postValue(data)
        }
    }



    private val _list = MutableLiveData<GetDataDetails>()
    val list: MutableLiveData<GetDataDetails> get() = _list


    init {
        getAllData()
    }

    fun getAllData() {
        viewModelScope.launch {//data.body()!!.data
            list.postValue(repository.getAllData())
            //_list.value = repository.getAllData()
        }
    }

}
