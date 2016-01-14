package com.example.apple.swordplayer.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by apple on 1/14/16.
 */
public class Util {

    static final String TAG = "UTIL";

    public static ArrayList<Mp3Info> getMusicFromDevice(Context context){
        ArrayList<Mp3Info> mp3Infos = new ArrayList<Mp3Info>();

        ContentResolver resolver = context.getContentResolver();

       Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
               null, null, null, null);

       while(cursor.moveToNext()){
           Mp3Info mp3Info = new Mp3Info();
//           int id = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
//           String music_ID =  cursor.getString(id);


           int name = cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
             mp3Info.name =  cursor.getString(name);
           int singer = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
           mp3Info.singer = cursor.getString(singer);
           mp3Infos.add(mp3Info);

           Log.i(TAG,"Name:" + name + "Singer:" + singer);


       }

        return mp3Infos;


    }

}
