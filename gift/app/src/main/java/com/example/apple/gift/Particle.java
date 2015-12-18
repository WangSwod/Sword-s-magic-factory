package com.example.apple.gift;

/**
 * Created by apple on 12/16/15.
 */
public abstract  class Particle {

    float x, y;
    int color;
    int time = 0 ;

    public Particle(int color, float x, float y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }
    public Particle(){}

    protected void incrementTime(){
        time++;
    }

    public abstract void advance();

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
