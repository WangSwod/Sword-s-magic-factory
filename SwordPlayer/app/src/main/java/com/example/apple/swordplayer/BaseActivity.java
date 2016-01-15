package com.example.apple.swordplayer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.playservice.PlayService;

/**
 * Created by apple on 1/15/16.
 */
public class BaseActivity extends AppCompatActivity {

    static final String TAG = "BASEACTIVITY";

    PlayService playService ;

    SwordServiceConnection cnn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    protected void bindPlayService(){
        Log.i(TAG, "play service， where are you");
        Intent intent = new Intent(this, PlayService.class);
        cnn = new SwordServiceConnection();

        bindService(intent, cnn, BIND_AUTO_CREATE);

    }
    protected  void unBindPlayService(){
        Log.i(TAG,"unBind");
        unbindService(cnn);
    }

    private class SwordServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            playService = ((PlayService.MusicBinder)service).getPlayService();
            if(playService == null ){
                Log.i(TAG,"play service is null");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "service disconnected");

        }
    }
}
