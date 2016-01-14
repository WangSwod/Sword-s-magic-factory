package com.example.apple.swordplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] testStrings = {"Test1","Test2","Test3","Test4","Test5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= (ListView)findViewById(R.id.sword_listview);

        MusicAdapter musicAdapter = new MusicAdapter(this);


        listView.setAdapter(musicAdapter);




    }
}
