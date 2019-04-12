package com.www.app.utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.navigateTo(target: Class<*>) {
    startActivity(Intent(this, target))
}
