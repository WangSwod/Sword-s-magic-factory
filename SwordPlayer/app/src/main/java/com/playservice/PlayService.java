package com.playservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.util.Mp3Info;
import com.util.Util;

import java.util.ArrayList;

/**
 * Created by apple on 1/15/16.
 */
public class PlayService extends Service {


    MediaPlayer mediaPlayer;
    static final String TAG = "PLAYSERVICE";

    MusicBinder musicBinder;

    ArrayList<Mp3Info> mp3Infos ;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"service destroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        musicBinder = new MusicBinder(this);

        return musicBinder ;
    }


    private void init(){

        mp3Infos = Util.getMusicFromDevice(getApplicationContext());
        mediaPlayer = new MediaPlayer();
        if(mediaPlayer == null){
            Log.i(TAG,"media player is null");

         }else{
            Log.i(TAG,"media player is not null");
        }


    }
    public  void start(int position){
        Log.i(TAG,"start:"+position);


    }
    public void pause(){

    }
    public  void resume(){

    }
    public void next(){

    }
    public void previous(){

    }

    public class MusicBinder extends Binder{
        PlayService playService ;

        public MusicBinder(PlayService playService) {
            this.playService = playService;
        }

        public PlayService getPlayService() {
            return playService;
        }
    }
}
