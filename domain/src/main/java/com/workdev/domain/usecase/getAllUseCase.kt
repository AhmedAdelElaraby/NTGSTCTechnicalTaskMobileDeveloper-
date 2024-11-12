package com.workdev.domain.usecase


 import com.workdev.domain.entity.aa.aa
import com.workdev.domain.repo.getAllRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class getAllUseCase(private val  Cats: getAllRepo) {


    suspend fun Comics(ts:String,apikey:String,hash:String,offset:Int) = flow<aa> {
        emit(Cats.Comics(ts,apikey,hash,offset))
    }.flowOn(Dispatchers.IO)



    suspend fun getComicsDetails(id:Int,ts:String,apikey:String,hash:String) = flow<aa> {
        emit(Cats.getComicsDetails(id,ts,apikey,hash))
    }.flowOn(Dispatchers.IO)




}