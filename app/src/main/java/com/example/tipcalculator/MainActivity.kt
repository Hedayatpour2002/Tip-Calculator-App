package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import com.example.tipcalculator.ui.theme.purpleGradient
import com.example.tipcalculator.ui.theme.white

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainUi(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainUi(modifier: Modifier = Modifier) {

    var billAmount by remember { mutableStateOf("") }
    var percentage by remember { mutableFloatStateOf(15f) }
    var selectedTab by remember { mutableIntStateOf(2) }
    val percentageButtons = listOf(5, 10, 15, 20, 25, 30, 40, 50, 60, 80, 100)

    var tipAmount by remember { mutableStateOf("") }
    var totalAmount by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    val currencies = listOf("Dollar", "Pound", "Euro")
    val currenciesSymbolTable = listOf("$", "£", "€")

    var selectedCurrency by remember { mutableStateOf(currencies[0]) }
    var selectedCurrencySymbol by remember { mutableStateOf("$") }

    var persons by remember { mutableFloatStateOf(1f) }
    var perPersonShare by remember { mutableStateOf(totalAmount) }

    perPersonShare =
        "%.2f".format((totalAmount.toFloatOrNull() ?: 0f) / "%.0f".format(persons).toFloat())


    tipAmount = "%.2f".format((billAmount.toFloatOrNull() ?: 0f) * percentage.toInt() / 100)
    totalAmount =
        "%.2f".format((tipAmount.toFloatOrNull() ?: 0f) + (billAmount.toFloatOrNull() ?: 0f))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(purpleGradient),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            text = "Tip Calculator",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 6.sp,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier.padding(vertical = 50.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(5.dp)) {
                Text(
                    text = "Bill :"
                )
                OutlinedTextField(
                    value = billAmount,
                    onValueChange = {
                        billAmount = it
                    },
                    modifier = Modifier
                        .background(white)
                        .padding(8.dp),
                    shape = RoundedCornerShape(50),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Column {
                    Button(onClick = { expanded = true }) {
                        Text(text = selectedCurrency, fontSize = 12.sp)
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = {
                        expanded = false
                    }) {
                        currencies.forEachIndexed { index, it ->
                            DropdownMenuItem(text = { Text(text = it) }, onClick = {
                                selectedCurrencySymbol = currenciesSymbolTable[index]
                                selectedCurrency = it
                                expanded = false
                            })
                        }
                    }
                }

            }
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Tip : "
                )
                Text(
                    text = "${percentage.toInt()}%"
                )
            }
            Slider(
                value = percentage,
                valueRange = 1f..100f,
                onValueChange = {
                    percentage = it
                },
            )
            ScrollableTabRow(
                selectedTabIndex = selectedTab, modifier = Modifier.padding(8.dp, 18.dp)
            ) {
                percentageButtons.forEachIndexed() { index, item ->
                    Button(
                        onClick = {
                            percentage = item.toFloat()
                            selectedTab = index

                        }, modifier = Modifier
                            .padding(4.dp)
                            .width(100.dp)
                    ) {
                        Text(text = "$item%")
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Split : "
                )
                Text(
                    text = "%.0f".format(persons)
                )
            }
            Slider(
                value = persons,
                onValueChange = { persons = it },
                valueRange = 1f..10f,
                modifier = Modifier.padding(5.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Tip Amount : "
                )
                Text(
                    text = "$tipAmount$selectedCurrencySymbol"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Amount : "
                )
                Text(
                    text = "$totalAmount$selectedCurrencySymbol"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Per Person : "
                )
                Text(
                    text = "$perPersonShare$selectedCurrencySymbol"
                )
            }

            Button(
                onClick = {
                    billAmount = ""
                    selectedCurrency = currencies[0]
                    percentage = 15f
                    persons = 1f

                },
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp, vertical = 10.dp)
            ) {
                Text(text = "Clear")
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipCalculatorTheme {
        MainUi()
    }
}