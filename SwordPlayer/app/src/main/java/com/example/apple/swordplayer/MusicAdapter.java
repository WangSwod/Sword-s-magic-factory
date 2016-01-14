package com.example.apple.swordplayer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apple.swordplayer.util.Mp3Info;
import com.example.apple.swordplayer.util.Util;

import java.util.ArrayList;

/**
 * Created by apple on 1/14/16.
 */
public class MusicAdapter extends BaseAdapter {

    static final String TAG = "MUSICADAPTER";

    String[] testStrings = {"Test1","Test2","Test3","Test4","Test5"};

    ViewHolder viewHolder;
    Context context;

    ArrayList<Mp3Info> mp3Infos = new ArrayList<Mp3Info>();


    public MusicAdapter(Context context) {
        this.context = context;
        mp3Infos = Util.getMusicFromDevice(context);

    }


    @Override
    public int getCount() {
        return mp3Infos.size();
    }


    @Override
    public Object getItem(int position) {
        return mp3Infos.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Mp3Info mp3Info = mp3Infos.get(position);
        String name = mp3Info.getName();
        String singer = mp3Info.getSinger();
//        String name = "name";
//        String singer = "singer";
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listview_expandable,parent,false);

            viewHolder.singer_Name = (TextView)convertView.findViewById(R.id.singer_name);
            viewHolder.song_Name = (TextView)convertView.findViewById(R.id.song_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();

        }

        viewHolder.singer_Name.setText(singer);
        viewHolder.song_Name.setText(name);



        return convertView;
    }

    private class ViewHolder{
        TextView song_Name, singer_Name;
    }
}
