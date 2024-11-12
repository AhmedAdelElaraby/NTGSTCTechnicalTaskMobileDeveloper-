package com.workdev.data.repo

import com.workdev.data.remote.ApiService

import com.workdev.domain.repo.getAllRepo

class getAllRepoImpL(private val apiService: ApiService):getAllRepo {

    override suspend fun Comics(ts:String,apikey:String,hash:String,offset:Int)=apiService.Comics(ts,apikey,hash,offset)
    override suspend fun getComicsDetails(id:Int,ts:String,apikey:String,hash:String) = apiService.ComicsDetails(id,ts,apikey,hash)


}