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

public class LinkGameExplain4Adapter extends BaseAdapter {

    Context context;
    List<Cet4ReviewBean._$4Bean> mDatas;
    int selectItem = -1;

    public LinkGameExplain4Adapter(Context context, List<Cet4ReviewBean._$4Bean> mDatas) {
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

    public void setSelectItem(int selectItem){
        this.selectItem = selectItem;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_game_link,null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Cet4ReviewBean._$4Bean resultBean = mDatas.get(i);
        holder.contentTv.setText(resultBean.getExplaination());
        if(selectItem == i){
            holder.contentTv.setTextColor(Color.BLUE);
        }else {
            holder.contentTv.setTextColor(Color.BLACK);

        }
        return view;
    }

    class ViewHolder{
        TextView contentTv;

        public ViewHolder(View view){
            contentTv = view.findViewById(R.id.linkgame_tv);
        }
    }
}
