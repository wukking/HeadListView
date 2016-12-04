package com.wuys.wuyson.headandlistview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by Wuyson on 2016/12/3.
 */

public class ParallaxListView extends ListView{
    public ParallaxListView(Context context) {
        super(context);
    }

    public ParallaxListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private ImageView imageView;
    private int maxHeight;
    private int orignalHeight;
    public void setParallsxImageView(final ImageView imageView){
        this.imageView = imageView;
        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                orignalHeight = imageView.getHeight();//imageVIew高度
                int drawableHeight = imageView.getDrawable().getIntrinsicHeight();//图片的高度

                maxHeight = orignalHeight>drawableHeight?orignalHeight*2:drawableHeight;
            }
        });
    }

    /**
     *
     * @param deltaX 继续滑动x方向的距离
     * @param deltaY 负值顶部到头，正值底部到头
     * @param scrollX
     * @param scrollY
     * @param scrollRangeX
     * @param scrollRangeY
     * @param maxOverScrollX x方向最大可以滚动的距离
     * @param maxOverScrollY
     * @param isTouchEvent  是否手指拖动滑动
     * @return
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if(deltaY<0 && isTouchEvent){
            if(imageView!=null) {
                int newHeight = imageView.getHeight() - deltaY/3;//放大图片
                if(newHeight>maxHeight) newHeight = maxHeight;
                imageView.getLayoutParams().height = newHeight;
                imageView.requestLayout();//使imageView得布局生效
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP){
            //imageView恢复到最初高度
            ValueAnimator animator = ValueAnimator.ofInt(imageView.getHeight(),orignalHeight);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    //获取动画的值
                    int animateValue = (int) valueAnimator.getAnimatedValue();
                    imageView.getLayoutParams().height = animateValue;
                    imageView.requestLayout();
                }
            });
            animator.setInterpolator(new OvershootInterpolator(5));
            animator.setDuration(350);
            animator.start();
        }
        return super.onTouchEvent(ev);
    }
}
