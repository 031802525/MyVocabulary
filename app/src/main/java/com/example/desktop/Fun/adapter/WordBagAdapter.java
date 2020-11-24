package com.example.desktop.Fun.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.desktop.Bean.wordbookbean.Cet4ReviewBean;
import com.example.desktop.R;

import java.util.List;

public class WordBagAdapter extends BaseAdapter {

    Context context;
    List<Cet4ReviewBean._$4Bean> mDatas;

    public WordBagAdapter(Context context, List<Cet4ReviewBean._$4Bean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public String getItemtranslation(int i){
        return mDatas.get(i).getExplaination();
    }

    public String getItemword(int i){
        return mDatas.get(i).getContent();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_word_list,null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Cet4ReviewBean._$4Bean resultBean = mDatas.get(i);
        holder.wordspellTv.setText(resultBean.getContent());
        holder.wordtranslateTv.setText(resultBean.getExplaination());
        return view;
    }

    class ViewHolder{

        TextView wordspellTv,wordtranslateTv;

        public ViewHolder(View view){
            wordspellTv = view.findViewById(R.id.worditem_wordspell);
            wordtranslateTv = view.findViewById(R.id.worditem_wordtranslate);
        }
    }
}
