package expandablelayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.apple.swordplayer.R;

/**
 * Created by apple on 1/13/16.
 */
public class SwordExpandableLayout extends RelativeLayout implements View.OnClickListener{

    static final String TAG = "SWORDEXPANDABLELAYOUT";

    private  boolean isContentShown = false;

    FrameLayout headerLayout, contentLayout;
    int contentID,headerID;
    TypedArray typedArray;
    public SwordExpandableLayout(Context context) {
        super(context);

    }

    public SwordExpandableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SwordExpandableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {

        Log.i(TAG, "init start");

        View root = View.inflate(context, R.layout.view_expandable, this);

        headerLayout = (FrameLayout)root.findViewById(R.id.view_expandable_headerlayout);
        contentLayout = (FrameLayout) root.findViewById(R.id.view_expandable_contentLayout);

        typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableLayout);

        contentID = typedArray.getResourceId(R.styleable.ExpandableLayout_sword_contentLayout, -1);
        headerID = typedArray.getResourceId(R.styleable.ExpandableLayout_sword_headerLayout,-1);

        if(contentID == -1 || headerID == -1 ){
            Log.i(TAG, "ID is wrong");
        }

        View contentView = View.inflate(context,contentID,null);
        View headerView = View.inflate(context,headerID,null);


        contentLayout.addView(contentView);
        headerLayout.addView(headerView);
        contentLayout.setVisibility(GONE);

        headerLayout.setOnClickListener(this);

        typedArray.recycle();


    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_expandable_headerlayout:
                if(isContentShown){

                    startCollapseAnimation(contentLayout);
                    isContentShown = false;
                }else{
                    startShowAnimation(contentLayout);

                    isContentShown = true;
                }
                break;
            default: break;
        }
    }

    private void startCollapseAnimation(View v) {

        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int height = v.getMeasuredHeight();


        ValueAnimator valueAnimator = ValueAnimator.ofInt(height,0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                changeContentHeight((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                hideContent();

            }
        });

        valueAnimator.setDuration(500);
        valueAnimator.start();

    }



    private void startShowAnimation(View v ){
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int height = v.getMeasuredHeight();

        v.setVisibility(VISIBLE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, height);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                changeContentHeight((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();

    }

    private void changeContentHeight (int height){
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
    }
    private void hideContent(){
        contentLayout.setVisibility(GONE);
    }


}
