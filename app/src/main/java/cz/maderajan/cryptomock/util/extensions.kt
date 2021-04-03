package cz.maderajan.cryptomock.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import java.text.SimpleDateFormat
import java.util.*

private val SIMPLE_DATE_FORMAT = SimpleDateFormat("dd.MM. yyyy HH:mm:ss.SSSZ")

fun Context.toast(@StringRes stringRes: Int) {
    Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
}

fun Context.toast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Long.presentableDate(): String = SIMPLE_DATE_FORMAT.format(Date(this))
