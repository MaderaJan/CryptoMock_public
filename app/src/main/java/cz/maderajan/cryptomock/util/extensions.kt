package cz.maderajan.cryptomock.util

import android.content.Context
import android.widget.Toast

fun Context?.toast(stringRes: Int) {
    Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
}