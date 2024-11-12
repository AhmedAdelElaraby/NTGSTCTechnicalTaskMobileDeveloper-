package com.workdev.example.ui.main.View

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mg_group.womniz.ResponseDataClass.SealedClass.ApiStateSubCats
import com.workdev.domain.entity.aa.Result

import com.workdev.example.R
import com.workdev.example.databinding.ActivityMainBinding
import com.workdev.example.ui.ComicsDetails.View.ComicsDetails


import com.workdev.example.ui.main.ViewModel.ViewModel
import com.workdev.example.ui.main.utils.AdapterProcess
import com.workdev.example.ui.main.utils.OnClick
import com.workdev.example.utils.Const
import com.workdev.example.utils.generateMd5Hash
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class MainActivity : AppCompatActivity() ,OnClick {
    lateinit var binding: ActivityMainBinding

    private var isLoading = false
    var Page:Int=1
    private val viewModel: ViewModel by viewModels()
    val Array=ArrayList<Result>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        fetchMarvelCharacters(Page)
        val adapters = AdapterProcess(this)
        binding.rec.apply {
            layoutManager =LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            adapter =adapters
        }








        viewModel.getAllCatsLiveData.observe(this) {
            when (it) {
                is ApiStateSubCats.Loading -> {

                }

                is ApiStateSubCats.Failure -> {

                    Toast.makeText(this, "" + it.e?.message.toString(), Toast.LENGTH_LONG).show()
                }

                is ApiStateSubCats.Success -> {
                    Toast.makeText(this, "" + it.data.status, Toast.LENGTH_LONG).show()
                    it.data.data.results.forEach{
                        Array.add(it)
                    }
                    binding.view.visibility =View.GONE
                    adapters.differ.submitList(Array)
                    viewModel.total=it.data.data.total
                    viewModel.offset=it.data.data.offset
                    adapters.notifyDataSetChanged()
                    isLoading=false

                }

                is ApiStateSubCats.Empty -> {

                }

                else -> {

                }
            }


        }









        binding.rec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                // Check if we are at the bottom of the list
                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    if (viewModel.offset == viewModel.total){
                        // Toast.makeText(context,"End Page ", Toast.LENGTH_LONG).show()

                    }
                    else {
                        Page++
                        binding.view.visibility =View.VISIBLE
                        fetchMarvelCharacters(Page)
                        isLoading = true
                    }
                }
            }
        })















    }








    fun fetchMarvelCharacters(page:Int){
        val publicKey = "ae268157a2da57e5aee06cd79d6d5b6a"
        val privateKey = "d12b971ea3a2bfce00ae74930edda44b591b67b3"
        val ts = System.currentTimeMillis().toString()
        val Hash= generateMd5Hash(ts + privateKey + publicKey)

       viewModel.getAllCats(ts,publicKey,Hash,page)

    }

    override fun OnClick(id: Int) {

        val intent =Intent(this,ComicsDetails::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }

}




