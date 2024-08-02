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
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color(0xFF618985))
//                .padding(start = 8.dp, top = 25.dp, end = 8.dp, bottom = 8.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(onClick = { onBackClick() }) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                    contentDescription = "back",
//                    modifier = Modifier.size(25.dp)
//                )
//            }
//
//            Text(text = "Saved Quotes (${quotesList.value.size})", fontSize = 20.sp)
//        }

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