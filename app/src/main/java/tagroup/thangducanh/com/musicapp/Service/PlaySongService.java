package tagroup.thangducanh.com.musicapp.Service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

import tagroup.thangducanh.com.musicapp.Media.MyMediaPlayer;

public class PlaySongService extends Service {
    private final IBinder mBinder = new MyBinder();
    private MyMediaPlayer myMediaPlayer;

    public PlaySongService() {
//        this.mBinder = mBinder;
        myMediaPlayer = new MyMediaPlayer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        mBinder = new MyBinder();
        return mBinder;
    }

    @Override
    public void onCreate() {
        try {
//            int resID = getResources().getIdentifier("tobu_ncs.mp3", "raw", getPackageName());
            Log.d("CUONGNT", "start load mp3 file");
            myMediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/fir-cdcb7.appspot.com/o/music%2FEmmE-Em-Toi-Dat-G-DuUyen.mp3?alt=media&token=5a15941d-0747-421c-95cf-790b141c0116");
            Log.d("CUONGNT", "Loading is done");
            myMediaPlayer.prepare();
//            startPlayingSong();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * Mybinder
     */
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
