package com.mg_group.womniz.ResponseDataClass.SealedClass

  import com.workdev.domain.entity.aa.aa


sealed class ApiStateSubCats {
    object Loading : ApiStateSubCats()
    class  Success(val data: aa) : ApiStateSubCats()
     class Failure(val e: Throwable?) : ApiStateSubCats()
   object Empty : ApiStateSubCats()

}
