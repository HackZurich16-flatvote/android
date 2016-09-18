package com.hackzurich.flatvote.flatvote.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.hackzurich.flatvote.flatvote.Constants;
import com.hackzurich.flatvote.flatvote.R;
import com.hackzurich.flatvote.flatvote.YesNoActivity;

/**
 * Created by longstone on 17/09/16.
 */
public class AnotherFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        String from = remoteMessage.getFrom();
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }
        String messageBody = "";
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Not Body: " + remoteMessage.getNotification().getBody());
            messageBody = remoteMessage.getNotification().getBody();
        }


        Intent intent = new Intent(this, YesNoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Constants.KEY_ADVERTISMENT, remoteMessage.getData().get("advertisementId"));
// use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

// build notification
// the addAction re-use the same intent to keep the example short
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(messageBody)
//                .setContentText("Subject")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true);

        Notification n = builder.getNotification();


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }

}
