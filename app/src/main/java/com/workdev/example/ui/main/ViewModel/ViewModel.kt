package com.workdev.example.ui.main.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mg_group.womniz.ResponseDataClass.SealedClass.ApiStateSubCats

import com.workdev.domain.usecase.getAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ViewModel @Inject constructor (private val getAllCats: getAllUseCase): ViewModel() {

    private val AllCat : MutableLiveData<ApiStateSubCats> = MutableLiveData(ApiStateSubCats.Empty)

    val getAllCatsLiveData : LiveData<ApiStateSubCats> = AllCat
    var offset =1
     var total = 0

    fun getAllCats (ts:String,apikey:String,hash:String,offset:Int){
         viewModelScope.launch {
            AllCat.value = ApiStateSubCats.Loading
            getAllCats.Comics(ts,apikey,hash,offset).catch {
                AllCat.value=ApiStateSubCats.Failure(it)
            }.collect{data->
                AllCat.value=ApiStateSubCats.Success(data)
            }

        }





    }







}