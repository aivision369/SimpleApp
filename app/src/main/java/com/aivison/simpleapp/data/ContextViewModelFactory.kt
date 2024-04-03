package com.aivison.simpleapp.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aivison.simpleapp.data.repository.AudioRepository
import com.aivison.simpleapp.data.viewmodel.AudioViewModel

class ContextViewModelFactory(
    private val context: Context,
    private val repository: AudioRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AudioViewModel::class.java)) {
            AudioViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ContextViewModelFactory, Error:Unknown ViewModel class::")
        }
    }
}