package com.example.messenger.data

import kotlinx.serialization.Serializable


@Serializable
data class ApiResponse(
    val name: String? = null,
    val culture: String? = null,
    val born: String? = null,
    val titles: List<String>? = null,
    val aliases: List<String>? = null,
    val playedBy: List<String>? = null
)

