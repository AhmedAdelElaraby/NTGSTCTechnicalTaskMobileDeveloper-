package com.workdev.example.ui.ComicsDetails.View

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.mg_group.womniz.ResponseDataClass.SealedClass.ApiStateSubCats
import com.squareup.picasso.Picasso
import com.workdev.example.R
import com.workdev.example.databinding.ActivityComicsDetailsBinding
import com.workdev.example.ui.ComicsDetails.Utils.AdapterDetails
import com.workdev.example.ui.ComicsDetails.ViewModel.ViewModelComicsDetails
import com.workdev.example.utils.generateMd5Hash
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsDetails : AppCompatActivity() {
    lateinit var binding:ActivityComicsDetailsBinding
    val viewModel :ViewModelComicsDetails by viewModels()
     var id:Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=DataBindingUtil.setContentView(this,R.layout.activity_comics_details)

        var id= intent.getIntExtra("id",0)
        fetchMarvelCharacters(id)

    val adapters= AdapterDetails()
        binding.rec.apply {
            layoutManager=LinearLayoutManager(this@ComicsDetails,LinearLayoutManager.HORIZONTAL,false)
            adapter=adapters
        }




        viewModel.ComicsDetailsLiveData.observe(this) {
            when (it) {
                is ApiStateSubCats.Loading -> {

                }

                is ApiStateSubCats.Failure -> {

                    Toast.makeText(this, "" + it.e?.message.toString(), Toast.LENGTH_LONG).show()
                }

                is ApiStateSubCats.Success -> {
                    Toast.makeText(this, "" + it.data.data.results.first().title, Toast.LENGTH_LONG).show()
                    val fullUrl: String  = "${it.data.data.results.first().thumbnail.path}.${it.data.data.results.first().thumbnail.extension}"
                    Picasso.get().load(fullUrl).fit().placeholder(R.drawable.skewed_background)
                        .error(R.drawable.ic_launcher_foreground).into(binding.image)
                    binding.valname.text = it.data.data.results.first().title
                    if (it.data.data.results.first().description.isNullOrEmpty()){
                        binding.valDescription.visibility = View.GONE
                    }else{
                        binding.valDescription.text = it.data.data.results.first().description
                    }

                    adapters.differ.submitList(it.data.data.results.first().variants)
                }

                is ApiStateSubCats.Empty -> {

                }

                else -> {

                }
            }


        }













    }




















    fun fetchMarvelCharacters(id:Int){
        val publicKey = "ae268157a2da57e5aee06cd79d6d5b6a"
        val privateKey = "d12b971ea3a2bfce00ae74930edda44b591b67b3"
        val ts = System.currentTimeMillis().toString()
        val Hash= generateMd5Hash(ts + privateKey + publicKey)

        viewModel.getComicsDetails(id,ts,publicKey,Hash)

    }





}