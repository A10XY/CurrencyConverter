package com.ahmed.currencyconverter.util

import android.content.Context
import android.widget.Toast

fun showShortToast(context: Context, message: String) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

fun getJsonFromAssets(context: Context, fileName: String): String {
    val inputStream = context.assets.open(fileName)
    val buffer = ByteArray(inputStream.available())
    inputStream.read(buffer)
    inputStream.close()
    return String(buffer, Charsets.UTF_8)
}