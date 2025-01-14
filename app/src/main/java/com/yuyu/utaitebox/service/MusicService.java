package com.yuyu.utaitebox.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.yuyu.utaitebox.R;
import com.yuyu.utaitebox.activity.MainActivity;
import com.yuyu.utaitebox.activity.RegisterActivity;
import com.yuyu.utaitebox.rest.RestUtils;
import com.yuyu.utaitebox.utils.Constant;

import java.util.ArrayList;

public class MusicService extends Service {

    private final String TAG = MusicService.class.getSimpleName();

    public static MediaPlayer mediaPlayer;

    public static boolean isLoading, isShuffle;
    public static ArrayList<Integer> positions;

    private int sid;
    private String key, cover, title, utaite;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            sid = intent.getIntExtra(getString(R.string.music_sid), -1);
            key = intent.getStringExtra(getString(R.string.music_key));
            cover = intent.getStringExtra(getString(R.string.music_cover));
            title = intent.getStringExtra(getString(R.string.music_title));
            utaite = intent.getStringExtra(getString(R.string.music_utaite));
        }

        RestUtils.getRetrofit()
                .create(RestUtils.MusicPlayApi.class)
                .musicPlayApi(MainActivity.TOKEN, key)
                .subscribe(o -> {
                        },
                        e -> Log.e(TAG, e.toString()));

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(RestUtils.BASE + getString(R.string.rest_stream) + key);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        mediaPlayer.setOnPreparedListener(mp -> mediaPlayer.start());
        mediaPlayer.prepareAsync();
        sendNotification();
        return START_STICKY;
    }

    public void sendNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class)
                .setAction(getString(R.string.service_noti))
                .putExtra(getString(R.string.music_sid), sid)
                .putExtra(getString(R.string.music_key), key)
                .putExtra(getString(R.string.music_cover), cover)
                .putExtra(getString(R.string.music_title), title)
                .putExtra(getString(R.string.music_utaite), utaite);

        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentIntent(pendingIntent)
                .setContentText("- " + utaite);

        Notification noti = builder.build();
        startForeground(Constant.NOTI_ID, noti);
        manager.notify(Constant.NOTI_ID, noti);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
