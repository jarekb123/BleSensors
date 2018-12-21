package com.butajlo.blesensors.ext

fun <T : Any> T.addTo(iterable: MutableList<T>) {
    iterable.add(this)
}