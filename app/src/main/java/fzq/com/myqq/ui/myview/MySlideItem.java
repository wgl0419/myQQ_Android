package fzq.com.myqq.ui.myview;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fzq on 2017/7/28.
 * 作者：阿敏其人
 * 链接：http://www.jianshu.com/p/5cb27a2ce03d
 * 來源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

public class MySlideItem extends ViewGroup {

    private View mContent;
    private View mDelete;
    private ViewDragHelper dragHelper;
    private int mContentWidth, mContentHeight;
    private int mDeleteWidth, mDeleteHeight;

    public MySlideItem(Context context) {
        this(context, null);
    }

    public MySlideItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySlideItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        dragHelper = ViewDragHelper.create(this, new MyDrawHelper());
    }

    //利用onFinishInflate、onMeasure和onLayout让控件显示出来

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContent = getChildAt(0);
        mDelete = getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 这跟mContent的父亲的大小有关，父亲是宽填充父窗体，高度是和孩子一样是固定的，比如60dp
        // mContent.measure(widthMeasureSpec,heightMeasureSpec); // 测量内容部分的大小

        LayoutParams contentParams = mContent.getLayoutParams();
        int contentWidth = MeasureSpec.makeMeasureSpec(contentParams.width, MeasureSpec.EXACTLY);
        int contentHeight = MeasureSpec.makeMeasureSpec(contentParams.height, MeasureSpec.EXACTLY);
        // 这个参数就需要指定为精确大小
        mContent.measure(contentWidth, contentHeight);

        LayoutParams deleteParams = mDelete.getLayoutParams();
        int deleteWidth = MeasureSpec.makeMeasureSpec(deleteParams.width, MeasureSpec.EXACTLY);
        int deleteHeight = MeasureSpec.makeMeasureSpec(deleteParams.height, MeasureSpec.EXACTLY);
        mDelete.measure(deleteWidth, deleteHeight);

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        // 摆放内容部分的位置
        mContentWidth = mContent.getMeasuredWidth();
        mContentHeight = mContent.getMeasuredHeight();
        mContent.layout(0, 0, mContentWidth, mContentHeight);

        // 摆放删除部分的位置
        mDeleteWidth = mDelete.getMeasuredWidth();
        mDeleteHeight = mDelete.getMeasuredHeight();
        mDelete.layout(mContentWidth, 0, mContentWidth + mDeleteWidth, mContentHeight);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return dragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            dragHelper.processTouchEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回true，这里表示去拦截事件
        return true;
    }


    class MyDrawHelper extends ViewDragHelper.Callback {

        /**
         * Touch的down事件会回调这个方法 tryCaptureView
         *
         * @return : ViewDragHelper是否继续分析处理 child的相关touch事件
         * @Child：指定要动的孩子 （哪个孩子需要动起来）
         * @pointerId: 点的标记
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            System.out.println("调用tryCaptureView");
            System.out.println("contentView : " + (mContent == child));
            return mContent == child || mDelete == child;
        }


        // Touch的move事件会回调这面这几个方法
        // clampViewPositionHorizontal
        // clampViewPositionVertical
        // onViewPositionChanged

        /**
         * 捕获了水平方向移动的位移数据
         *
         * @param child 移动的孩子View
         * @param left  父容器的左上角到孩子View的距离
         * @param dx    增量值，其实就是移动的孩子View的左上角距离控件（父亲）的距离，包含正负
         * @return 如何动
         * <p>
         * 调用完此方法，在android2.3以上就会动起来了，2.3以及以下是海动不了的
         * 2.3不兼容怎么办？没事，我们复写onViewPositionChanged就是为了解决这个问题的
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if(child == mContent){  // 解决内容部分左右拖动的越界问题
                if(left>0){
                    return 0;
                }else if(-left>mDeleteWidth){
                    return -mDeleteWidth;
                }
            }else if(child == mDelete){  // 解决删除部分左右拖动的越界问题
                if(left<mContentWidth - mDeleteWidth){
                    return mContentWidth - mDeleteWidth;
                }else if(left > mContentWidth){
                    return mContentWidth;
                }
            }
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return super.clampViewPositionVertical(child, top, dy);
        }

        /**
         * 当View的位置改变时的回调
         * 需要注意的是，我们的clampViewPositionHorizontal和clampViewPositionHorizontal所产生的动画效果
         * 在2.3以上才会有效果，如果要达到兼容，我们就需要借助onViewPositionChanged方法。
         *
         * @param changedView 哪个View的位置改变了
         * @param left        changedView的left
         * @param top         changedView的top
         * @param dx          x方向的上的增量值
         * @param dy          y方向上的增量值
         */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            invalidate();
//            super.onViewPositionChanged(changedView, left, top, dx, dy);

            if (changedView == mContent) { // 滑动的mContent
                //我们移动mContent的时候要相应的联动改变mDelete的位置
                // 怎么改变mDelete的位置，当然是mDelete的layout方法啦
                int tempDeleteLeft = mContentWidth + left;
                int tempDeleteRight = mContentWidth + left + mDeleteWidth;
                mDelete.layout(tempDeleteLeft, 0, tempDeleteRight, mDeleteHeight);

            } else if (changedView == mDelete) { //滑动的mDelete
                int tempContentLeft = left - mContentWidth;
                int tempContentRight = left;
                mContent.layout(tempContentLeft,0,tempContentRight,mContentHeight);

            }
        }

        /**
         * 相当于Touch的up的事件会回调onViewReleased这个方法
         *
         * @param releasedChild
         * @param xvel          x方向的速率
         * @param yvel          y方向的速率
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {

            // 方法的参数里面没有left，那么我们就采用 getLeft()这个方法
            int mConLeft = mContent.getLeft();
            // 这里没必要来分两个孩子判断
            if(-mConLeft>mDeleteWidth/2){
               // mContent.layout(-mDeleteWidth,0,mContentWidth-mDeleteWidth,mContentHeight);
               // mDelete.layout(mContentWidth-mDeleteWidth,0,mContentWidth,mDeleteHeight);

                //采用ViewDragHelper的 smoothSlideViewTo 方法让移动变得顺滑自然，不会太生硬
                // smoothSlideViewTo只是模拟了数据，但是不会真正的动起来，动起来需要调用 invalidate
                // 而 invalidate 通过调用draw()等方法之后最后还是还是会调用 computeScroll 这个方法
                // 所以，使用 smoothSlideViewTo 做过渡动画需要结合invalidate方法和computeScroll方法
                // smoothSlideViewTo的动画执行时间没有暴露的参数可以设置，但是这个时间是google给
                // 我们经过大量计算给出合理时间
                dragHelper.smoothSlideViewTo(mContent,-mDeleteWidth,0);
                dragHelper.smoothSlideViewTo(mDelete,mContentWidth-mDeleteWidth,0);

            }else{
               // mContent.layout(0,0,mContentWidth,mContentHeight);
               // mDelete.layout(mContentWidth,0,mContentWidth+mDeleteWidth,mDeleteHeight);
                dragHelper.smoothSlideViewTo(mContent, 0, 0);
                dragHelper.smoothSlideViewTo(mDelete,mContentWidth, 0);
            }
            invalidate();
            super.onViewReleased(releasedChild, xvel, yvel);
        }
    }

    @Override
    public void computeScroll() {
        //super.computeScroll();
        // 把捕获的View适当的时间移动，其实也可以理解为 smoothSlideViewTo 的模拟过程还没完成
        if(dragHelper.continueSettling(true)){
            invalidate();
        }
        // 其实这个动画过渡的过程大概在怎么走呢？
        // 1、smoothSlideViewTo方法进行模拟数据，模拟后就就调用invalidate();
        // 2、invalidate()最终调用computeScroll，computeScroll做一次细微动画，
        // computeScroll判断模拟数据是否彻底完成，还没完成会再次调用invalidate
        // 3、递归调用，知道数据模拟完成。
    }


    // SlideDlete的接口
    public interface OnSlideDeleteListener {
        void onOpen(MySlideItem slideDelete);
        void onClose(MySlideItem slideDelete);
    }
    private OnSlideDeleteListener onSlideDeleteListener;
    public void setOnSlideDeleteListener(OnSlideDeleteListener onSlideDeleteListener){
        this.onSlideDeleteListener = onSlideDeleteListener;
    }

}