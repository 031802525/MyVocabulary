package com.example.desktop.Fun.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.desktop.Bean.wordbookbean.Cet4ReviewBean;
import com.example.desktop.Bean.wordbookbean.Cet6ReviewBean;
import com.example.desktop.R;

import java.util.List;

public class LinkGame6Adapter extends BaseAdapter {

    Context context;
    List<Cet6ReviewBean._$6Bean> mDatas;
    int selectItem = -1;

    public LinkGame6Adapter(Context context, List<Cet6ReviewBean._$6Bean> mDatas) {
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
        Cet6ReviewBean._$6Bean resultBean = mDatas.get(i);
        holder.contentTv.setText(resultBean.getContent());
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
