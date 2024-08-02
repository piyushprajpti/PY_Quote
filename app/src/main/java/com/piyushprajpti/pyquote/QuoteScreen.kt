package com.piyushprajpti.pyquote

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.util.Calendar

@Composable
fun QuoteScreen(
    quoteData: QuoteEntity,
    db: QuoteDatabase,
    savedQuoteListSize: Int = 0,
    onBackClick: () -> Unit
) {
    val (backColor, textColor) = remember { randomColorGenerator() }

    val showShareDialogBox = remember {
        mutableStateOf(false)
    }

    Log.d("TAG", "QuoteScreen: ${showShareDialogBox.value}")

    val saveState = remember {
        mutableStateOf(false)
    }

    val saveDao = db.saveDao()

    LaunchedEffect(quoteData.id) {
        saveState.value = withContext(Dispatchers.IO) {
            saveDao.findIdInSaveTable(quoteData.id)
        }
    }

    val coroutineScope = rememberCoroutineScope()

    fun onSaveClick() {
        coroutineScope.launch(Dispatchers.IO) {
            if (saveState.value) {
                saveDao.deleteQuote(SaveEntity(quoteData.id))
            } else {
                saveDao.insertQuote(SaveEntity(quoteData.id))
            }
            withContext(Dispatchers.Main) {
                saveState.value = !saveState.value
            }
        }
    }

    val saveIcon = if (saveState.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder
    val saveText = if (saveState.value) "Saved" else "Save"

    val localContext = LocalContext.current
    val clipboardContext = LocalClipboardManager.current

    fun shareAsText() {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "${quoteData.content} \n\n~ ${quoteData.author}")
        }
        localContext.startActivity(Intent.createChooser(intent, "Share Via"))
        showShareDialogBox.value = false
    }

    fun shareAsImage() {

        val composeView = ComposeView(localContext).apply {
            setContent {
                ShareImagePrototype(
                    quoteData = quoteData,
                    backColor = backColor,
                    textColor = textColor
                )
            }
        }

        val bitmap = captureBitmapFromComposeView(composeView)

        shareImage(bitmap = bitmap, context = localContext)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backColor),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (savedQuoteListSize > 0) {
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
                            tint = textColor
                        )
                    }

                    Text(
                        text = "Saved Quotes (${savedQuoteListSize})",
                        fontSize = 22.sp,
                        color = textColor
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = quoteData.content,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.W500,
                        fontFamily = FontFamily(Font(R.font.playwrite)),
                        lineHeight = 35.sp,
                        color = textColor
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "~ " + quoteData.author,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.playwrite)),
                        color = textColor
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextButton(
                        onClick = {
                            onSaveClick()
                        },
                        modifier = Modifier
                            .border(1.dp, textColor, RoundedCornerShape(20.dp))
                            .weight(1f)
                    ) {
                        Icon(
                            imageVector = saveIcon,
                            contentDescription = saveText,
                            tint = textColor
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = saveText,
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    TextButton(
                        onClick = {
                            Log.d("TAG", "QuoteScreen: here")
                            showShareDialogBox.value = true
                        },
                        modifier = Modifier
                            .border(1.dp, textColor, RoundedCornerShape(20.dp))
                            .weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = textColor
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "Share",
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }

        if (showShareDialogBox.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x8A000000)),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .background(backColor, RoundedCornerShape(16.dp))
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Choose an option",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            color = textColor
                        )
                        IconButton(
                            onClick = { showShareDialogBox.value = false },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "close",
                                tint = textColor
                            )
                        }
                    }

                    HorizontalDivider(color = Color(0x31FFFFFF))

                    TextButton(
                        onClick = {
                            clipboardContext.setText(AnnotatedString(quoteData.content + "\n\n~ " + quoteData.author))
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Copy as Text",
                            color = textColor,
                            fontSize = 16.sp
                        )
                    }

                    HorizontalDivider(color = Color(0x31FFFFFF))

                    TextButton(onClick = { shareAsText() }, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Share as Text",
                            color = textColor,
                            fontSize = 16.sp
                        )
                    }

                    HorizontalDivider(color = Color(0x31FFFFFF))

                    TextButton(onClick = { shareAsImage() }, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Share as Image",
                            color = textColor,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

fun captureBitmapFromComposeView(composeView: ComposeView): Bitmap {
    return composeView.drawToBitmap()
}

@Composable
fun captureBitmap(content: @Composable () -> Unit): () -> Bitmap {
    val context = LocalContext.current

    val composeView = remember {
        ComposeView(context)
    }

    fun captureBitmap(): Bitmap {
        return composeView.drawToBitmap()
    }

    AndroidView(
        factory = {
            composeView.apply {
                setContent { content.invoke() }
            }
        }
    )

    return ::captureBitmap
}

fun Context.getUriFromBitmap(bitmap: Bitmap): Uri {
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes)
    val path: String = MediaStore.Images.Media.insertImage(
        this.contentResolver,
        bitmap,
        "${Calendar.getInstance().time}_image",
        "image ${Calendar.getInstance().time}"
    )
    return Uri.parse(path)
}


fun shareImage(bitmap: Bitmap, context: Context) {
    val uri = context.getUriFromBitmap(bitmap)

    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/*"
    }

    context.startActivity(Intent.createChooser(sendIntent, "Share Quote"))
}
