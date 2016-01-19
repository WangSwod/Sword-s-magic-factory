package com.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

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

        int columnIndex = -1 ;

//        if (cursor != null) {
//            Log.i(TAG,"cursor" +cursor.getCount());
//        }

        while(cursor.moveToNext()){
           Mp3Info mp3Info = new Mp3Info();
//           int id = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
//           String music_ID =  cursor.getString(id);

            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);
            String name = cursor.getString(columnIndex);
            if(name.endsWith(".mp3")){

                mp3Info.setName(cursor.getString(columnIndex));


                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST);
                mp3Info.setSinger(cursor.getString(columnIndex));

                columnIndex =  cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);
                mp3Info.setDuration(cursor.getInt(columnIndex));

                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
                String uri = cursor.getString(columnIndex);
                mp3Info.setUri(uri);

                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM);
                String album = cursor.getString(columnIndex);

                columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
                String data = cursor.getString(columnIndex);




//                Log.i(TAG, album);
//                Log.i(TAG, data);


                mp3Infos.add(mp3Info);


            }else{
                continue;
            }







       }
        cursor.close();
        return mp3Infos;


    }
    //未完成，暂时没有用处
    public static String parseTime(int time){
        StringBuffer sb = new StringBuffer();

        return time + "";

    }

}
