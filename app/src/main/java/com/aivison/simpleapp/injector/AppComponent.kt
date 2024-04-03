package com.aivison.simpleapp.injector

import com.aivison.simpleapp.data.viewmodel.AudioViewModel
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(viewModel: AudioViewModel)
}
