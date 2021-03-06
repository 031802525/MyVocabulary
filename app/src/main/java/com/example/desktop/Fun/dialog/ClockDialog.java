package com.example.desktop.Fun.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desktop.R;
import com.squareup.picasso.Picasso;


public class ClockDialog extends Dialog implements View.OnClickListener{
    private TextView sentenceTv,translationTv;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;
    private ImageView imageIv;
    private CharSequence picURL,sentence,translation;

    private Context mContext;
    private CharSequence content;
    private OnCloseListener listener;
    private CharSequence positiveName;
    private CharSequence negativeName;
    private CharSequence title;

    public ClockDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public ClockDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public ClockDialog(Context context, int themeResId, CharSequence content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    public ClockDialog(Context context, int themeResId, CharSequence sentence, CharSequence translation, CharSequence picURL, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.listener = listener;
        this.sentence = sentence;
        this.translation = translation;
        this.picURL = picURL;
    }

    protected ClockDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public ClockDialog setTitleText(CharSequence title){
        this.title = title;
        return this;
    }

    public ClockDialog setPositiveButton(CharSequence name){
        this.positiveName = name;
        return this;
    }
    public ClockDialog setWindow(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//8.0新特性
            this.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY - 1);
        } else {
            this.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        }

        return this;
    }
    public ClockDialog setNegativeButton(String name){
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_clock);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        sentenceTv = findViewById(R.id.sentence_clock);
        translationTv = findViewById(R.id.translation_clock);
        imageIv = findViewById(R.id.image_clock);
        titleTxt = findViewById(R.id.title_clock);
        submitTxt = (TextView)findViewById(R.id.submit_clock);
        submitTxt.setOnClickListener(this);
        cancelTxt = (TextView)findViewById(R.id.cancel_clock);
        cancelTxt.setOnClickListener(this);

        sentenceTv.setText(sentence);
        translationTv.setText(translation);
//        Picasso.with(mContext).load(picURL).into(imageIv);

        if(!TextUtils.isEmpty(positiveName)){
            submitTxt.setText(positiveName);
        }

        if(!TextUtils.isEmpty(negativeName)){
            cancelTxt.setText(negativeName);
        }

        if(!TextUtils.isEmpty(title)){
            titleTxt.setText(title);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel:
                if(listener != null){
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
            case R.id.submit:
                if(listener != null){
                    listener.onClick(this, true);
                }
                break;
        }
    }

    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }
}