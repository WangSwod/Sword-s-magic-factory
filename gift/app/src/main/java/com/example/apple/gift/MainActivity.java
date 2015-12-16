package com.example.apple.gift;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MAINACTIVITY";
    ImageView rose;
    ImageView rose1;
    Bitmap bitmap_Rose;
    int offset = 300;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rose = (ImageView) findViewById(R.id.rose_view);
//        rose1 = (ImageView) findViewById(R.id.rose1);

        BitmapDrawable drawable_rose = (BitmapDrawable)rose.getDrawable();
        bitmap_Rose  = drawable_rose.getBitmap();

        Log.i(TAG, bitmap_Rose.getWidth() + "/" + bitmap_Rose.getHeight());


//        rose1.setImageBitmap(bitmap_Rose);
//        int color = bitmap_Rose.getPixel(offset,offset);

//        Log.i(TAG,"color" + color);

//        rose.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(event.getAction() == MotionEvent.ACTION_DOWN){
//                    int color_temp = bitmap_Rose.getPixel((int)event.getX(),(int)event.getY());
//                    rose1.setBackgroundColor(color_temp);
//                }
//                return false;
//            }
//        });
    }
}
