package com.spiderman.calnories.reminder;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.spiderman.calnories.R;

/**
 * Created by Biekaeksa on 5/29/2017.
 */

public class AlarmService extends IntentService {
    private NotificationManager alarmNotificationManager;

    public AlarmService() {
        super("AlarmService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e("Hai ", "Service");
        sendNotification("Makan Pagi ! Makan Pagi");
    }

    private void sendNotification(String msg) {
        Log.e("Notofication otw ", msg);
        alarmNotificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,1, new Intent(this, ReminderFragment.class ), 1);

        NotificationCompat.Builder alarmNotifBuilder = new NotificationCompat.Builder(this).
                setContentTitle("Alarm").setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);

        alarmNotifBuilder.setContentIntent(pendingIntent);
        alarmNotificationManager.notify(1, alarmNotifBuilder.build());
        Log.e("Notofication ", "terkirim");
    }
}
