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

        listView= (ListView)findViewById(R.id.sword_listview);

        MusicAdapter musicAdapter = new MusicAdapter(this);

        listView.setAdapter(musicAdapter);


//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//             parent is list view, view is expandable_layout
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Log.i(TAG, "View1:" + view.getId());
//                Log.i(TAG, "View2:" + R.id.expandable_layout);
//                Log.i(TAG, "id: "+ id);
//
//                if (playService == null) {
//                    Log.i(TAG, "play service is null");
//                    return;
//                }
//                playService.start(position);
//            }
//        });

        bindPlayService();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"destroy");
        unBindPlayService();
        super.onDestroy();

    }
}
