package com.util;

/**
 * Created by apple on 1/14/16.
 */
public class Mp3Info {
    String name;
    String singer;
    String album;

    int duration ;

    String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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
