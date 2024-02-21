package com.example.manageyourcar.UIlayer

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UIUtil constructor(
    private val appContext: Context
) {
    fun displayToast(message: String) {
        Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
    }

    suspend fun displayToastSuspend(message: String) {
        withContext(Dispatchers.Main) {
            Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}