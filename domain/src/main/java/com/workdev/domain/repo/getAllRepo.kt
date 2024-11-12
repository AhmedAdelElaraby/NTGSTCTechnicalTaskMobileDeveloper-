package com.workdev.domain.repo


 import com.workdev.domain.entity.aa.aa


interface getAllRepo {

    suspend fun Comics(ts:String,apikey:String,hash:String,offset:Int) : aa
    suspend fun getComicsDetails (id:Int,ts:String,apikey:String,hash:String) : aa

}