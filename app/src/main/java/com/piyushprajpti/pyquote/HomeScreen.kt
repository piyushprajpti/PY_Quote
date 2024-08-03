package com.piyushprajpti.pyquote

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(onSavedQuotesClick: () -> Unit) {


    val localContext = LocalContext.current

    val linktreeIntent = remember {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://linktr.ee/piyushprajpti")
        )
    }

    fun shareApp() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                "Checkout this wonderful app developed by Piyush - \nhttps://play.google.com/store/apps/details?id=com.piyushprajpti.todo_app \n\nConnect with developer - \nhttps://linktr.ee/piyushprajpti"
            )
        }
        localContext.startActivity(Intent.createChooser(intent, "Share Via"))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF618985)),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround

        ) {
            Text(
                text = "Welcome",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 50.sp,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextButton(
                    onClick = {
                    onSavedQuotesClick()
                }, modifier = Modifier.weight(0.5f)) {
                    Text(
                        text = "Saved Quotes",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF618985),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .padding(vertical = 15.dp)
                            .fillMaxWidth()
                    )
                }

                TextButton(
                    onClick = { shareApp() },
                    modifier = Modifier.weight(0.5f)
                ) {
                    Text(
                        text = "Share App",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF618985),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .padding(vertical = 15.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Swipe left and start scrolling ➡",
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            TextButton(
                onClick = { localContext.startActivity(linktreeIntent) },
            ) {
                Text(
                    text = "Developed by Piyush \uD83D\uDD17",
                    fontSize = 18.sp,
                    color = Color(0xFFC8DFDC),
                )
            }
        }
    }
}