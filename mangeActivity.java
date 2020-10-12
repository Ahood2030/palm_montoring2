package com.example.palm_montoring.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.palm_montoring.Activity.HomeActivity;
import com.example.palm_montoring.Activity.MainActivity;
import com.example.palm_montoring.AddActivity;
import com.example.palm_montoring.Notification;
import com.example.palm_montoring.R;
import com.example.palm_montoring.SQlite.EditeActivity;
import com.example.palm_montoring.UpdateActivity;

import java.util.Calendar;

public class mangeActivity extends AppCompatActivity {
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;


    Button add,update,delete,view ,search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mange);
        add = findViewById(R.id.Add);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        view = findViewById(R.id.view);
        Button okButton = (Button) findViewById(R.id.button_noti);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleNotification(getNotification("Watring palms now ... "));

                Intent i = new Intent(getBaseContext(), MainActivity.class);
                getBaseContext().startActivity(i);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddActivity.class);
                getBaseContext().startActivity(intent);

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), UpdateActivity.class);
                getBaseContext().startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditeActivity.class);
                getBaseContext().startActivity(intent);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                getBaseContext().startActivity(intent);

            }
        });
    }
    // function fot notification
    AlarmManager alarmManager = null;
    PendingIntent pendingIntent = null;
    public void scheduleNotification (android.app.Notification notification ) {

        Intent notificationIntent = new Intent( getBaseContext(), Notification. class ) ;
        notificationIntent.putExtra(Notification. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(Notification. NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( getBaseContext(), 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
        int delay=0;
        long futureInMillis = SystemClock. elapsedRealtime () + delay ;
        AlarmManager alarmManager = (AlarmManager) getBaseContext().getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                10080, pendingIntent);
    }

    //Notification Style
    public android.app.Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), default_notification_channel_id);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_mange, container, false);
    }
}
