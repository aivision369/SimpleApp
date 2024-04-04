package com.aivison.simpleapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aivison.simpleapp.MyApplication
import com.aivison.simpleapp.R
import com.aivison.simpleapp.data.ContextViewModelFactory
import com.aivison.simpleapp.data.repository.AudioRepository
import com.aivison.simpleapp.data.viewmodel.AudioViewModel
import com.aivison.simpleapp.databinding.ActivityMainBinding
import com.aivison.simpleapp.injector.NetworkModule
import com.aivison.simpleapp.model.GetDataDetails
import com.aivison.simpleapp.model.SubCategoryModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AudioViewModel
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var audioAdapter: AudioPlayerAdapter
    private var arraySubCategory = ArrayList<SubCategoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        try {
            val module = NetworkModule()
            val repository = AudioRepository(module.provideApi(module.provideRetrofit(applicationContext, cacheDir)))
            viewModel = ViewModelProvider(
                this, ContextViewModelFactory(
                    this@MainActivity, repository
                )
            )[AudioViewModel::class.java]


            (application as MyApplication).appComponent.inject(viewModel)
            viewModel.list.observe(this) {
                //retrieveData(it.appCenter)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }




  /*  private fun retrieveData(arrayAppCenter: List<GetDataDetails.AppCenter>) {
        try {
            if (arrayAppCenter.isNotEmpty()) {
                for (i in arrayAppCenter.indices) {
                    val arrSubCategory = arrayAppCenter[i].subCategory
                    for (j in arrSubCategory.indices) {
                        arraySubCategory.add(
                            SubCategoryModel(
                                arrSubCategory[j].name,
                                arrSubCategory[j].icon,
                                arrSubCategory[j].star,
                                arrSubCategory[j].installedRange,
                                arrSubCategory[j].appLink,
                            )
                        )
                    }
                }
            }

            mBinding.rvAudio.layoutManager = LinearLayoutManager(this)
            audioAdapter = AudioPlayerAdapter(this@MainActivity, arraySubCategory)
            mBinding.rvAudio.adapter = audioAdapter
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/
}
