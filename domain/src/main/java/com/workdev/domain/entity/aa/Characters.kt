package com.workdev.domain.entity.aa

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<com.workdev.domain.entity.aa.Item>,
    val returned: Int
)