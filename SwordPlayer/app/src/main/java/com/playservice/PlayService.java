package com.playservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.expandablelayout.SwordExpandableLayout;
import com.util.Mp3Info;
import com.util.Util;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by apple on 1/15/16.
 */
public class PlayService extends Service {

    static final String TAG = "PLAYSERVICE";

    MediaPlayer mediaPlayer;

    MusicBinder musicBinder;

    ArrayList<Mp3Info> mp3Infos ;

    SwordExpandableLayout swordExpandableLayout,oldrSwordExpandableLayout;

    private volatile Thread myThread;

    private  int currentPosition = 0 ;

    private  int current_Progress_Of_Song;

    private boolean hasStart  = false;
    

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "service destroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        musicBinder = new MusicBinder(this);
        return musicBinder ;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        musicBinder = null;
        swordExpandableLayout = null;

        return super.onUnbind(intent);
    }

    private void init(){

        mp3Infos = Util.getMusicFromDevice(getApplicationContext());
        mediaPlayer = new MediaPlayer();






    }

    public  void start(int position){
        if(!hasStart){
            play(position);
            currentPosition = position;
            hasStart = true;

        }else {
            if(position != currentPosition){
                stop(currentPosition);
                oldrSwordExpandableLayout.startCollapseAnimation();
                play(position);
                currentPosition = position;
            }else{

                resume();
            }


        }

    }
    public  void play(int position){


        Mp3Info mp3Info = mp3Infos.get(position);
        String uri = mp3Info.getUri();
        Log.i(TAG, mediaPlayer.getCurrentPosition()+"");
        swordExpandableLayout.setDuration(mp3Info.getDuration()/1000);
        myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    swordExpandableLayout.updateSeekBar(mediaPlayer.getCurrentPosition()/1000);
                    Log.i(TAG,"thread is running");
                    try {
                        myThread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(this, Uri.parse(uri));
            mediaPlayer.prepare();
            mediaPlayer.start();
            myThread.start();


        } catch (IOException e) {
            e.printStackTrace();
        }




    }
    public void pause(){

    }
    public  void resume(){
        mediaPlayer.seekTo(current_Progress_Of_Song);
        mediaPlayer.start();

    }
    public void next(){

    }
    public void previous(){

    }
    public void stop(int position){
        current_Progress_Of_Song =  mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();
        stopMyThread();



    }

    public void connectToView (View v ){
      if ( v instanceof SwordExpandableLayout){
          oldrSwordExpandableLayout = swordExpandableLayout;
          swordExpandableLayout = (SwordExpandableLayout)v;
      }
    }


    public void stopMyThread() {
        Thread tmpThread = myThread;
        myThread = null;
        if (tmpThread != null) {
            tmpThread.interrupt();
        }
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
