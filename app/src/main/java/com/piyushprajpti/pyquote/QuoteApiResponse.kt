package com.piyushprajpti.pyquote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteApiResponse(

    @SerialName("code")
    val code: Int = 200,

    @SerialName("message")
    val message: String = "",

    @SerialName("count")
    val count: Int,

    @SerialName("totalCount")
    val totalCount: Long,

    @SerialName("page")
    val page: Int,

    @SerialName("totalPages")
    val totalPages: Int,

    @SerialName("lastItemIndex")
    val lastItemIndex: Int,

    @SerialName("results")
    val results: List<QuoteData>
)

@Serializable
data class QuoteData(

    @SerialName("_id")
    val _id: String,

    @SerialName("author")
    val author: String,

    @SerialName("content")
    val content: String,

    @SerialName("tags")
    val tags: List<String>,

    @SerialName("authorSlug")
    val authorSlug: String,

    @SerialName("length")
    val length: Int,

    @SerialName("dateAdded")
    val dateAdded: String,

    @SerialName("dateModified")
    val dateModified: String,
)

data class QuoteApiResponseResult(
    val quoteData: List<QuoteData>? = emptyList(),
    val errorMessage: String? =null
)