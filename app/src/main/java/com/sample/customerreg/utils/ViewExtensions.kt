package com.sample.customerreg.utils

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.sample.customerreg.BuildConfig
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.showToast(message: CharSequence) = Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()

fun Activity.showToast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


fun printData(msg: String) {
    if (BuildConfig.DEBUG) Log.e("Print_LOG ", msg)
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    })
}

fun EditText.validate(message: String, inputLayout: TextInputLayout, validator: (String) -> Boolean) {
    this.afterTextChanged {
        inputLayout.error = if (validator(it)) null else message
    }
    inputLayout.error = if (validator(this.text.toString())) null else message
}

fun String.isValid(): Boolean = this.isNotEmpty()

/**
 * Show a snackbar with [message], execute [f] and show it
 */
inline fun View.snack(message: String,  length: Int = Snackbar.LENGTH_SHORT, f: Snackbar.() -> Unit?) {
    val snack = Snackbar.make(this, message, length)
    snack.setAction("Settings",{

    })
    snack.f()
    snack.show()
}

fun Snackbar.action( actionRes: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(actionRes, listener)
    color?.let { setActionTextColor(color) }
}

/**
 * Show a snackbar with [messageRes], execute [f] and show it
 */
inline fun View.snack(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_SHORT, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, messageRes, length)
    snack.f()
    snack.show()
}

fun View.snackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
    }.show()
}