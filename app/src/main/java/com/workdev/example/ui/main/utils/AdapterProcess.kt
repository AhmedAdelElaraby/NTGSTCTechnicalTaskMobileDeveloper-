package com.workdev.example.ui.main.utils
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
import com.workdev.example.R

import com.workdev.example.databinding.ItemProcessBinding


class AdapterProcess(private val  onClick: OnClick): RecyclerView.Adapter<AdapterProcess.ViewHolder>() {
    lateinit var  binding: ItemProcessBinding





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      binding= ItemProcessBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     binding.text.text = differ.currentList[position].title
       val fullUrl: String  = "${differ.currentList[position].thumbnail.path}.${differ.currentList[position].thumbnail.extension}"
       Picasso.get().load(fullUrl).fit().placeholder(R.drawable.skewed_background)
      .error(R.drawable.ic_launcher_foreground).into(holder.binding.image)

            binding.root.setOnClickListener{
               onClick.OnClick(differ.currentList[position].id)
            }


    }





    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(var binding: ItemProcessBinding) : RecyclerView.ViewHolder(binding.root)


    private val differCallback = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return  oldItem == newItem
        }


        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {

            return oldItem == newItem

        }

    }
   val differ = AsyncListDiffer(this,differCallback)








}