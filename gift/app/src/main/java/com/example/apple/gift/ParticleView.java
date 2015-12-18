package com.example.apple.gift;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by apple on 12/17/15.
 */
public class ParticleView extends View {

    Particle[][] particles;

    Paint p ;
    float x, y;


    public ParticleView(Context context, Particle[][] particles) {
        super(context);
        this.particles = particles;
    }


    public ParticleView(Context context, AttributeSet attrs, Particle[][] particles) {
        super(context, attrs);
        this.particles = particles;
    }


    public ParticleView(Context context, AttributeSet attrs, int defStyleAttr, Particle[][] particles) {
        super(context, attrs, defStyleAttr);
        this.particles = particles;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p = new Paint();
//        Log.i("MAINACTIVITY",particles.length + "/" +particles[0].length);

        for(int i = 0 ; i< particles.length ; i++){

            for(int j=0 ; j < particles[i].length;j++){
                p.setColor(particles[i][j].getColor());
                Particle particle = particles[i][j];
                x = particle.getX();
                y = particle.getY();

                particle.advance();
                canvas.drawRect(x, y, x + Util.particle_Length, y + Util.particle_Length, p);
           }

        }

    }


}
