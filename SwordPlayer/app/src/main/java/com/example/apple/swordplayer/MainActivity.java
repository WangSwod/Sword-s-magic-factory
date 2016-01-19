package com.example.apple.swordplayer;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends BaseActivity {

    static final String TAG = "MAINACTIVITY";

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindPlayService();

        listView= (ListView)findViewById(R.id.sword_listview);


        MusicAdapter musicAdapter = new MusicAdapter(this,playService);

        listView.setAdapter(musicAdapter);


    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"destroy");
        unBindPlayService();
        super.onDestroy();

    }


}
