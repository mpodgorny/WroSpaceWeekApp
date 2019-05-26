package com.example.spaceweekapp

import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.support.v4.content.ContextCompat.getSystemService
import android.R
import android.app.Notification
import android.app.NotificationChannel
import android.os.Build
import android.support.v4.app.NotificationManagerCompat
import java.security.AccessController.getContext


class FireMsgService : FirebaseMessagingService() {


   override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
       println("------------------------------------")
            println("Firebase MSG"+ "" + remoteMessage.notification!!.body)

       //TODO: make msg into notification

   }
}

