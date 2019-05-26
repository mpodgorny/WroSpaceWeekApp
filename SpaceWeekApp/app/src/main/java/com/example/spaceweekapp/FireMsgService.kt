package com.example.spaceweekapp
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.widget.RemoteViews

class FireMsgService : FirebaseMessagingService() {

    lateinit var builder : Notification.Builder
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel

    private val channelId = "com.example.spaceweekapp"
    private val description = "Test notification"

   override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
     //  println("------------------------------------")
       //     println("Firebase MSG"+ "" + remoteMessage.notification!!.body)
      // val napis = remoteMessage.notification!!.body

       notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

       val intent = Intent(this,MainActivity::class.java)
       val pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)

       val contentView = RemoteViews(packageName, R.layout.notification_layout)
       contentView.setTextViewText(R.id.tv_title,remoteMessage.notification!!.title)
       contentView.setTextViewText(R.id.tv_content,remoteMessage.notification!!.body )

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
           notificationChannel.enableLights(true)
           notificationChannel.lightColor = Color.GREEN
           notificationChannel.enableVibration(false)
           notificationManager.createNotificationChannel(notificationChannel)

           builder = Notification.Builder(this,channelId)
               .setContent(contentView)
               .setSmallIcon(R.drawable.logo_small_purple)
               .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.logo_small_purple))
               .setContentIntent(pendingIntent)
       }else{

           builder = Notification.Builder(this)
               .setContent(contentView)
               .setSmallIcon(R.drawable.logo_small_purple)
               .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.logo_small_purple))
               .setContentIntent(pendingIntent)
       }
       notificationManager.notify(1234,builder.build())

   }
}


