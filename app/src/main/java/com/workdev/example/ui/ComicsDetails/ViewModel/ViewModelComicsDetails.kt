package com.workdev.example.ui.ComicsDetails.ViewModel

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
class ViewModelComicsDetails @Inject constructor (private val getAllCats: getAllUseCase):ViewModel() {


    private val ComicsDetails : MutableLiveData<ApiStateSubCats> = MutableLiveData(ApiStateSubCats.Empty)

    val ComicsDetailsLiveData : LiveData<ApiStateSubCats> = ComicsDetails


    fun getComicsDetails (id:Int,ts:String,apikey:String,hash:String ){
        viewModelScope.launch {
            ComicsDetails.value = ApiStateSubCats.Loading
            getAllCats.getComicsDetails(id,ts,apikey,hash ).catch {
                ComicsDetails.value= ApiStateSubCats.Failure(it)
            }.collect{data->
                ComicsDetails.value= ApiStateSubCats.Success(data)
            }

        }





    }












}