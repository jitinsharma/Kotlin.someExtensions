package com.amadeus.mercimdp.utils

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

/**
 * Visibility modifiers and check functions
 */

fun View.isVisibile(): Boolean = this.visibility == View.VISIBLE

fun View.isGone(): Boolean = this.visibility == View.GONE

fun View.isInvisible(): Boolean = this.visibility == View.INVISIBLE

fun View.makeVisible() { this.visibility = View.VISIBLE }

fun View.makeGone() { this.visibility = View.GONE }

fun View.makeInvisible() { this.visibility = View.INVISIBLE }

/**
 * Sets text and content description using same string
 */
fun TextView.setTextWithContentDescription(value : String?) {
    text = value
    contentDescription = value
}

/**
 * AppCompatActivity's toolbar visibility modifiers
 */

fun AppCompatActivity.hideToolbar() {
    supportActionBar?.hide()
}

fun AppCompatActivity.showToolbar() {
    supportActionBar?.show()
}

/**
 * Button enabling/disabling modifiers
 */

fun Button.disableButton() {
    isEnabled = false
    alpha = 0.3f
}

fun Button.enableButton() {
    isEnabled = true
    alpha = 1.0f
}