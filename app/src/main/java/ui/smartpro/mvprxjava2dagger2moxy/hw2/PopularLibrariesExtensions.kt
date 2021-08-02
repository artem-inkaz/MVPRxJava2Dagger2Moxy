package ui.smartpro.mvprxjava2dagger2moxy.hw1

import android.view.View

fun View.click(click: () -> Unit) = setOnClickListener { click() }