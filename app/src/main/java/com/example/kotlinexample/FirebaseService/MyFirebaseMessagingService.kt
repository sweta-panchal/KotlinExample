package com.example.kotlinexample.FirebaseService

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.nfc.Tag
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.kotlinexample.MainActivity
import com.example.kotlinexample.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {


        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("TAG", "From: " + remoteMessage.from)


        // Check if message contains a data payload.
         remoteMessage?.data?.isNotEmpty()?.let {
             Log.d("this","Message data payload: " + remoteMessage.data)

             if (!remoteMessage.data.isNullOrEmpty()){
                 val msg:String = remoteMessage.data.get("message").toString()
                 val body : String = remoteMessage.data.get("body").toString()
                 sendNotification(msg,body)
             }
         }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null && remoteMessage.data.size > 0) {

            val title = remoteMessage.data["title"]
            val body = remoteMessage.data["body"]
              sendNotification(title,body)
        }
        else
        {
            val title = remoteMessage.notification!!.title
            val body = remoteMessage.notification!!.body
            sendNotification(title,body)
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.e("Token","New Token")
    }

    private fun sendNotification(title : String?,messageBody: String?){

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)
        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this,channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
              val channel = NotificationChannel(channelId, "Channel human title", NotificationManager.IMPORTANCE_HIGH)
               notificationManager.createNotificationChannel(channel)
          }

          notificationManager.notify(0, notificationBuilder.build())

    }


}