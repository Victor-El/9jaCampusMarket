package com.chainremita.a9jacampusmarket;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private static final String ID = "1337";
    private NotificationManager notificationManager;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MyService.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, i, 0);
                final NotificationCompat.Builder builder = new NotificationCompat.Builder(MyService.this, NotificationChannel.DEFAULT_CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("Check out the latest items for sale")
                        .setContentText("What would you like buy?")
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setColor(Color.GREEN)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Log.d("DebugService", "Service running");
                        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        //startForeground(1, builder.build());
                        notificationManager.notify(1, builder.build());
                    }
                }, 5000 * 60 * 60 * 4, 5000 * 60 * 60 * 4);

            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
