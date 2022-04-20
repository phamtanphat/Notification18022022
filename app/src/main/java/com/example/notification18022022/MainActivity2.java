package com.example.notification18022022;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity2 extends AppCompatActivity {

    Button mBtnOpen,mBtnClose;
    String CHANNEL_ID = "My_channel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mBtnOpen = findViewById(R.id.buttonOpenNotification);
        mBtnClose = findViewById(R.id.buttonCloseNotification);

        mBtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity2.class);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity2.this);
                stackBuilder.addNextIntentWithParentStack(intent);

                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity2.this, CHANNEL_ID);
                builder.setContentTitle("Thông báo");
                builder.setContentText("Bạn có 5 voucher sắp hết hạn. Hãy vào app kiểm tra!!");
                builder.setShowWhen(true);
                builder.setSmallIcon(android.R.drawable.btn_star);
                builder.setVibrate(new long[]{1000, 200, 2000, 200});
//                builder.setContentIntent(pendingIntent);
                builder.addAction(android.R.drawable.star_big_on,"Open",resultPendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Admob", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(notificationChannel);
                }

                notificationManager.notify(1, builder.build());

            }
        });
    }
}