package com.piyushprajpti.pyquote

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

val httpClient = HttpClient(Android) {
    expectSuccess = true
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

suspend fun getQuotes(callBack: (QuoteApiResponseResult) -> Unit) {
    try {
        val response = httpClient.get("https://api.quotable.io/quotes?limit=150")
        callBack(QuoteApiResponseResult(quoteData = response.body<QuoteApiResponse>().results))
    } catch (e: Exception) {
        callBack(QuoteApiResponseResult(errorMessage = e.message))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainFeed(
    db: QuoteDatabase,
    onSavedQuotesClick: () -> Unit
) {

    val context = LocalContext.current

    val quoteList = remember {
        mutableStateOf<List<QuoteEntity>>(emptyList())
    }

    val quoteDao = db.quoteDao()

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {

            val dbQuotes = quoteDao.getAllQuotesList()

            if (dbQuotes.isEmpty()) {
//                Toast.makeText(context, "getQuotes called", Toast.LENGTH_LONG).show()
                getQuotes { result ->
                    result.quoteData?.let {
                        quoteDao.insertQuotes(
                            it.map {
                                QuoteEntity(
                                    id = it._id,
                                    content = it.content,
                                    author = it.author
                                )
                            }
                        )
                        quoteList.value = quoteDao.getAllQuotesList().shuffled()
                    }
                    result.errorMessage?.let {
                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                quoteList.value = dbQuotes.shuffled()
            }
        }
    }

    val horizontalPagerState = rememberPagerState { 2 }
    val verticalPagerState = rememberPagerState { quoteList.value.size }


    HorizontalPager(
        state = horizontalPagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> {
                HomeScreen(onSavedQuotesClick = onSavedQuotesClick)
            }

            1 -> {
                VerticalPager(state = verticalPagerState) {
                    QuoteScreen(
                        quoteData = quoteList.value[it],
                        db = db,
                        onBackClick = {}
                    )
                }
            }
        }
    }


}