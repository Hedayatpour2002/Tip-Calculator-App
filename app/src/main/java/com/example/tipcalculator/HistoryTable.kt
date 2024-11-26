package com.example.tipcalculator

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryTable(historyList: List<History>, onDelete: (History) -> Unit, onDeleteAll: () -> Unit) {

    val titles = listOf("Bill Amount", "Tip Amount", "Total Amount", "Per Person")
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White),
    ) {
        if (historyList.isEmpty()) {
            Text(
                text = "You don't have any history 🫤",
                modifier = Modifier
                    .padding(vertical = 40.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
            return
        }
        Row(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            titles.forEach {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                )
            }
            Text(
                text = "X",
                color = Color.Red,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .width(40.dp)
                    .padding(vertical = 8.dp)
                    .clickable { onDeleteAll() },

                )
        }

        historyList.forEach {
            Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(
                    text = "${it.billAmount}${it.currency}",
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${it.tipAmount}${it.currency}",
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${it.totalAmount}${it.currency}",
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${it.perPerson}${it.currency}",
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )

                Column(
                    modifier = Modifier
                        .width(40.dp)
                        .padding(vertical = 8.dp)
                        .clickable {
                            onDelete(it)
                            Toast
                                .makeText(context, "item deleted", Toast.LENGTH_SHORT)
                                .show()
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete icon",
                        tint = Color.Red,
                    )
                }
            }
        }
    }
}



