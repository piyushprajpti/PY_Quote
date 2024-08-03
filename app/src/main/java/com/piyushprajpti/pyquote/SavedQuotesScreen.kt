package com.piyushprajpti.pyquote

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SavedQuotesScreen(db: QuoteDatabase, onBackClick: () -> Unit) {

    val quotesList = remember {
        mutableStateOf<List<QuoteEntity>>(emptyList())
    }

    val quoteDao = db.quoteDao()

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            quotesList.value = quoteDao.getAllSavedQuotes()
        }
    }

    val pagerState = rememberPagerState {
        quotesList.value.size
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (quotesList.value.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF808690)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0x1E000000))
                        .padding(start = 8.dp, top = 30.dp, end = 8.dp, bottom = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back",
                            modifier = Modifier.size(28.dp),
                            tint = Color.White
                        )
                    }

                    Text(
                        text = "Saved Quotes (0)",
                        fontSize = 22.sp,
                        color = Color.White
                    )
                }

                Text(
                    text = "No saved quotes found",
                    color = Color.White,
                    fontSize = 40.sp,
                    lineHeight = 50.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp, top = 250.dp, end = 20.dp)
                )
            }
        } else {
            VerticalPager(state = pagerState) {
                QuoteScreen(
                    quoteData = quotesList.value[it],
                    db = db,
                    savedQuoteListSize = quotesList.value.size,
                    onBackClick = onBackClick
                )
            }
        }
    }
}