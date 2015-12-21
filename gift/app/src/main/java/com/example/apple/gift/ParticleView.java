package com.example.apple.gift;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by apple on 12/17/15.
 */
public class ParticleView extends View {

    Particle[][] particles;

    Paint p ;



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

        for(int i = particles.length-1 ; i >= 0  ; i--){

            for(int j=0 ; j < particles[i].length;j++){
                Particle particle = particles[i][j];
                float x = particle.getX();
                float y = particle.getY();
                int color = particle.getColor();
                float alpha = particle.getAlpha();

                p.setColor(color);
                p.setAlpha((int) (Color.alpha(color) * alpha));

                particle.advance();
                canvas.drawRect(x, y, x + Util.particle_Length, y + Util.particle_Length, p);
           }

        }

    }


}
