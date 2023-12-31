package dev.androidbroadcast.sample.paging3.data.model

import com.test.testpaging2.model.Source

data class Article(
    val source: Source?,
    val title: String,
    val url: String?,
    val description: String?,
    val author: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
)
