package com.workdev.domain.entity.aa

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<com.workdev.domain.entity.aa.ItemXX>,
    val returned: Int
)