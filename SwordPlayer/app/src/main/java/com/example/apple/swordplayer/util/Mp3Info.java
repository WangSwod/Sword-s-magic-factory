package com.example.apple.swordplayer.util;

/**
 * Created by apple on 1/14/16.
 */
public class Mp3Info {
    String name;
    String singer;
    String album;

    Long duration ;


    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Mp3Info{" +
                "album='" + album + '\'' +
                ", name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                ", duration=" + duration +
                '}';
    }
}
