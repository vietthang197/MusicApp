package tagroup.thangducanh.com.musicapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import tagroup.thangducanh.com.musicapp.Service.PlaySongService;

public class PlaySongActivity extends AppCompatActivity {

    private Button btn_play, btn_stop, btn_continue;

    private ServiceConnection connection;
    private PlaySongService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        btn_play = findViewById(R.id.btn_play);
        btn_stop = findViewById(R.id.btn_stop);
        btn_continue = findViewById(R.id.btn_continue);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d("CUONGNT", "Connected service");
                PlaySongService.MyBinder myBinder = (PlaySongService.MyBinder) iBinder;
                service = myBinder.getService();
            }
            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d("CUONGNT", "Disconnected service");
            }
        };
        bindService(new Intent(getBaseContext(), PlaySongService.class), connection, Context.BIND_AUTO_CREATE);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.startPlayingSong();
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.stopPlayingSong();
            }
        });
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.continuePlayingSong();
            }
        });
    }
}
