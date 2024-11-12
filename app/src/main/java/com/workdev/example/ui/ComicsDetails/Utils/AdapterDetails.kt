package com.workdev.example.ui.ComicsDetails.Utils
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.workdev.domain.entity.aa.Result
import com.workdev.domain.entity.aa.Variant
import com.workdev.example.R
import com.workdev.example.databinding.ItemBodyBinding

import com.workdev.example.databinding.ItemProcessBinding


class AdapterDetails( ): RecyclerView.Adapter<AdapterDetails.ViewHolder>() {
    lateinit var  binding: ItemBodyBinding





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      binding= ItemBodyBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     binding.name.text = differ.currentList[position].name
//       val fullUrl: String  = "${differ.currentList[position].resourceURI.path}.${differ.currentList[position].thumbnail.extension}"
//       Picasso.get().load(fullUrl).fit().placeholder(R.drawable.skewed_background)
//      .error(R.drawable.ic_launcher_foreground).into(holder.binding.image)




    }





    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(var binding: ItemBodyBinding) : RecyclerView.ViewHolder(binding.root)


    private val differCallback = object : DiffUtil.ItemCallback<Variant>(){
        override fun areItemsTheSame(oldItem: Variant, newItem: Variant): Boolean {
            return  oldItem == newItem
        }


        override fun areContentsTheSame(oldItem: Variant, newItem: Variant): Boolean {

            return oldItem == newItem

        }

    }
   val differ = AsyncListDiffer(this,differCallback)








}