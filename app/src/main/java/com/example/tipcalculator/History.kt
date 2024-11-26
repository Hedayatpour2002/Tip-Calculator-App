package com.example.tipcalculator

data class History(
    val id: Int,
    val currency: String,
    val billAmount: String,
    val tipAmount: String,
    val totalAmount: String,
    val perPerson: String
)