package com.example.assignment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun DrinkDetailScreen(category: DrinkCategory){
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = category.strDrink, textAlign = TextAlign.Center)
        Image(
            painter = rememberAsyncImagePainter(model = category.strDrinkThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(text = "Drink Description Here")
    }
}