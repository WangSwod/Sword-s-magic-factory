package com.example.apple.gift;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by apple on 12/17/15.
 */
public class ParticleView extends ImageView {

    static final String TAG = "PARTICLEVIEW";

    Particle[][] particles;

    Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ParticleView(Context context) {
        super(context);

    }


    public ParticleView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    public ParticleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public Particle[][] getParticles() {
        return particles;
    }

    public void setParticles(Particle[][] particles) {
        this.particles = particles;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(particles!=null){
            setImageDrawable(null);

                    Log.i(TAG, ((int) (Color.alpha(particles[30][30].getColor()))) + "alpha");
                    Log.i(TAG, particles[0][0].getAlpha() + "alpha111");

            for(int i = particles.length-1 ; i >= 0  ; i--){

                for(int j=0 ; j < particles[i].length;j++){
                    Particle particle = particles[i][j];
                    float x = particle.getX();
                    float y = particle.getY();
                    int color = particle.getColor();
                    float alpha = particle.getAlpha();

                    p.setColor(color);
                    p.setAlpha((int) (Color.alpha(color) * alpha));
//                    p.setAlpha((int)alpha);

                    canvas.drawRect(x, y, x + Util.particle_Length, y + Util.particle_Length, p);
                    particle.advance();
                }
            }
        }
    }
}
