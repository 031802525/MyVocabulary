package com.example.desktop.Fun.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.desktop.R;

import java.util.Calendar;

public class ClockView extends View {
    Paint paint;  // 绘制钟表的画笔
    int hours,minute,seconds; //时分秒

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
//                重新获取时间
                getTime();
//                重新绘制页面
                invalidate();
                handler.sendEmptyMessageDelayed(1,1000);
            }
        }
    };
    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);   //设置抗锯齿，放大的时候没有锯齿
//        paint.setColor(Color.BLACK);    //设置画笔的颜色
        getTime(); // 页面启动时获取时分秒 调用函数
//        获取在布局当中设置的自定义属性，设置给view
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClockView);
        int color = typedArray.getColor(R.styleable.ClockView_clockColor, Color.BLACK); //第二个参数是默认值，若没有就设置为黑色
        paint.setColor(color);  //设置画笔颜色
//        回收属性
        typedArray.recycle();

    }

//    获取当前时间的方法
    public void getTime(){
        Calendar calendar = Calendar.getInstance();
        hours = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        seconds = calendar.get(Calendar.SECOND);
    }

//    显示的内容就在onDraw方法中进行绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);     //设置画笔的样式，空心的
        paint.setStrokeWidth(8);  //设置线条宽度
//        设置内边距
        setPadding(20,20,20,20);
//        绘制最外层大圈
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-20,paint); // 圆心的横坐标为宽度的一半，纵坐标为高度的一半，半径为高度或宽度的一半都可
//        重新设置画笔的粗细画内圈的小圆
        paint.setStrokeWidth(4);
//        绘制内层的圆
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2-30,paint);
//        绘制表中间的轴心
        paint.setStyle(Paint.Style.FILL);//实心画笔
        canvas.drawCircle(getWidth()/2,getHeight()/2,10,paint);
//        绘制表的刻度 12个 直线， 因为起始点和结尾点坐标不好确定，需要通过旋转画布实现
        for (int i = 1; i <= 12; i++ ){
//            旋转画布之前保留画布原有的状态
            canvas.save();
//            旋转到指定的角度
            canvas.rotate(360/12*i,getWidth()/2,getHeight()/2);
            canvas.drawLine(getWidth()/2,40,getWidth()/2,50,paint);
//            恢复旋转之前的状态
            canvas.restore();
//            提醒一秒钟之后刷新界面
            handler.sendEmptyMessageDelayed(1,1000);

        }
//        绘制时分秒针
//        时针
        paint.setStrokeWidth(8);
//        旋转画布，旋转的度数由当前的时间决定，1小时是30度，1分钟是0.5度
        canvas.save();
        canvas.rotate(30*hours+0.5f*minute,getWidth()/2,getHeight()/2);
        canvas.drawLine(getWidth()/2,getHeight()/2,getWidth()/2,getHeight()/2-getHeight()/5,paint);
        canvas.restore();
//         分针  1分钟代表6度
        paint.setStrokeWidth(5);
        canvas.save();
        canvas.rotate(6*minute,getWidth()/2,getHeight()/2);
        canvas.drawLine(getWidth()/2,getHeight()/2,getWidth()/2,getHeight()/2-getHeight()/4,paint);
        canvas.restore();
//        秒针  1秒钟代表6度
        paint.setStrokeWidth(3);
        canvas.save();
        canvas.rotate(6*seconds,getWidth()/2,getHeight()/2);
        canvas.drawLine(getWidth()/2,getHeight()/2,getWidth()/2,getHeight()/2-getHeight()/3,paint);
        canvas.restore();
    }

//    显示的尺寸，和使用时传入的宽高相关，因为整体为圆形，所以要保证宽高相同
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        1.获取传入宽高的模式,是具体的值还是wrap_content 的
        int wmode = MeasureSpec.getMode(widthMeasureSpec);
        int hmode = MeasureSpec.getMode(heightMeasureSpec);
//        2.获取宽高的最大尺寸
        int wsize = MeasureSpec.getSize(widthMeasureSpec);
        int hsize = MeasureSpec.getSize(heightMeasureSpec);
//        3.判断模式,目的是获取最终显示的尺寸
        int size = 400;
        if (wmode == MeasureSpec.EXACTLY) { // 当宽度在布局中设置为精确值，match_parent 是精确值
            if (hmode == MeasureSpec.EXACTLY) {
//                宽高都为具体值，谁小就取谁
                size = Math.min(wsize,hsize);
            }else {
//                宽度为精确值，高度为wrap_content
                size = wsize;
            }
        }else {
//            当宽度在布局使用时，被设定为wrap_content
            if (hmode == MeasureSpec.EXACTLY) {
//                高度是精确值
                size = hsize;
            }else {
//                宽高都是wrap_content
                size = 400;
            }
        }
//        4.将测量好的值设置给控件宽高
        setMeasuredDimension(size,size);
    }
}
