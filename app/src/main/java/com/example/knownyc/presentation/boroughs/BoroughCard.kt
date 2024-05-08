package com.example.knownyc.presentation.boroughs

import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.knownyc.R
import com.example.knownyc.domain.models.Borough

@Composable
fun BoroughCard(
    name: String,
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},

    )  {
    val shape = RoundedCornerShape(8.dp)
    val height = 148.dp
    Card(
    elevation = CardDefaults.elevatedCardElevation(8.dp),
     shape = shape,
     modifier = modifier
         .padding(6.dp)
         .shadow(
             elevation = 6.dp,
             spotColor = MaterialTheme.colorScheme.surfaceTint
         )
         .requiredHeight(height)
         .clickable {
             onClick()
         }
 ) {
    // Box, Column or a Row
     Box(
         modifier = modifier
             .fillMaxSize()
     ) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = modifier
                .rotate(-90f)
                .fillMaxWidth(0.2f)
                .align(Alignment.CenterStart)
        )
         Image(
             painter = painter,
             contentDescription = contentDescription,
             contentScale = ContentScale.Crop,
             modifier = modifier
                 .fillMaxWidth(0.8f)
                 .align(Alignment.CenterEnd)
         )
     }
 }
}

@Preview
@Composable
fun BoroughCardPreview() {
    BoroughCard(
        name = "The Bronx",
        painter = painterResource(id = R.drawable.bronx),
        contentDescription =""
    )
}