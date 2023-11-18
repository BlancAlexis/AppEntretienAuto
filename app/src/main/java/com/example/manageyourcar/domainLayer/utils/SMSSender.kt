package com.example.manageyourcar.domainLayer.utils

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import com.example.manageyourcar.UIlayer.AppApplication
import com.google.android.material.snackbar.Snackbar

object SmsSender {
    fun sendSMS(message : String, phoneNumber : String) =
        try {
            val smsManager : SmsManager
            if (Build.VERSION.SDK_INT>=23) {
               smsManager =
                    AppApplication.instance.getSystemService(SmsManager::class.java) as SmsManager
            }else{
                smsManager = SmsManager.getDefault()
            }
            smsManager?.sendTextMessage("0781708641", null, message, null, null)
         //   Snackbar.make(AppApplication.instance.applicationContext as View, "Erreur lors de l'envoi du message", Snackbar.LENGTH_LONG).setTextColor(Color.RED).show()
        } catch (e: Exception) {
         //   Snackbar.make(AppApplication.instance.applicationContext as View, "Erreur lors de l'envoi du message", Snackbar.LENGTH_LONG).setTextColor(Color.RED).show()
        Log.i(e.toString(),e.localizedMessage)
        }
}