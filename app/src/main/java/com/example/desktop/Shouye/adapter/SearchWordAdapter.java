package com.example.desktop.Shouye.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.desktop.Bean.translatebean.EnglishToChineseWordBean;
import com.example.desktop.Fun.adapter.LinkGame6Adapter;
import com.example.desktop.R;

import java.util.List;

public class SearchWordAdapter extends BaseAdapter {
    Context context;
    List<EnglishToChineseWordBean.WebBean> mDatas;

    public SearchWordAdapter(Context context, List<EnglishToChineseWordBean.WebBean> mDatas) {
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_tran,null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        EnglishToChineseWordBean.WebBean bean = mDatas.get(i);
        List<String> value = bean.getValue();
        String s = "";
        for (int j = 0; j < value.size(); j++) {
            s = s + value.get(j) + ";";
        }
        holder.englishTv.setText(s);
        holder.chineseTv.setText(bean.getKey());

        return view;
    }

    class ViewHolder{
        TextView englishTv,chineseTv;

        public ViewHolder(View view){
            englishTv = view.findViewById(R.id.english);
            chineseTv = view.findViewById(R.id.chinese);
        }
    }

}
