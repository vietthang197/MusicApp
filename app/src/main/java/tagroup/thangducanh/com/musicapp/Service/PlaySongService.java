package tagroup.thangducanh.com.musicapp.Service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.R;
import android.util.Log;

import java.io.IOException;

import tagroup.thangducanh.com.musicapp.Media.MyMediaPlayer;

public class PlaySongService extends Service {

    private MyMediaPlayer myMediaPlayer;

    public PlaySongService() {
        myMediaPlayer = new MyMediaPlayer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myMediaPlayer = new MyMediaPlayer();
        try {
//            int resID = getResources().getIdentifier("tobu_ncs.mp3", "raw", getPackageName());
            Log.d("CUONGNT", "start load mp3 file");
            myMediaPlayer.setDataSource("http://cdl12.convert2mp3.net/download.php?id=youtube_q1ULJ92aldE&key=aL3Kx5yMa5gV&d=y");
            Log.d("CUONGNT", "Loading is done");
            myMediaPlayer.prepare();
            startPlayingSong();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class MyBinder extends Binder{
        public PlaySongService getService(){
            return PlaySongService.this;
        }
    }

    public void startPlayingSong(){
        if (myMediaPlayer != null){
            myMediaPlayer.start();
        }
    }

    public void stopPlayingSong(){
        if ( myMediaPlayer != null && myMediaPlayer.isPlaying()){
            myMediaPlayer.pause();
        }
    }

    public void continuePlayingSong(){
        if ( myMediaPlayer != null && !myMediaPlayer.isPlaying()){
            myMediaPlayer.start();
        }
    }

    public boolean playForwardto(int pos){
        if (myMediaPlayer!= null){
            myMediaPlayer.seekTo(pos);
            return true;
        }
        return false;
    }
}
