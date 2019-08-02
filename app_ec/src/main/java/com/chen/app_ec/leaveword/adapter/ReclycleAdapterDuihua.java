package com.chen.app_ec.leaveword.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.app_ec.R;
import com.chen.app_ec.leaveword.javabean.JavaNews;

import java.util.List;

public class ReclycleAdapterDuihua extends RecyclerView.Adapter {

    private Context mContext;
    private List<JavaNews> mDataArrayList ;
    int reciverid;
    int giveid;

    public static final int typel = 0;
    public static final int typer = 1;


    public int getReciverid() {
        return reciverid;
    }

    public void setReciverid(int reciverid) {
        this.reciverid = reciverid;
    }

    public int getGiveid() {
        return giveid;
    }

    public void setGiveid(int giveid) {
        this.giveid = giveid;
    }

    public ReclycleAdapterDuihua(Context context, List<JavaNews> dataArrayList, int reciverid, int giveid) {
        mContext = context;
        mDataArrayList = dataArrayList;
        this.reciverid = reciverid;
        this.giveid = giveid;
    }

    @Override
    public int getItemViewType(int position) {
        int id = mDataArrayList.get(position).getSend();
        if (id == reciverid){
            return typel;
        }else {
            return typer;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view ;
        RecyclerView.ViewHolder viewHolder;
        if (i == typel){
            view = LayoutInflater.from(mContext).inflate(R.layout.itemview_duihual,viewGroup,false);
            viewHolder = new ViewHolderL(view);
        }else {
            view = LayoutInflater.from(mContext).inflate(R.layout.itemview_duihuar,viewGroup,false);
            viewHolder = new ViewHolderR(view);
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        JavaNews data= mDataArrayList.get(i);
        if (data.getSend() == reciverid){

            TextView textView = viewHolder.itemView.findViewById(R.id.tv_duihual);
            textView.setText(data.getContent());
        }else {
            TextView textView = viewHolder.itemView.findViewById(R.id.tv_duihualr);
            textView.setText(data.getContent());
        }
    }


    @Override
    public int getItemCount() {
        return mDataArrayList.size();
    }

    static class ViewHolderL extends RecyclerView.ViewHolder{

        TextView mTextView;

        public ViewHolderL(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_duihual);
        }
    }

    static class ViewHolderR extends RecyclerView.ViewHolder{

        TextView mTextView;

        public ViewHolderR(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_duihualr);
        }
    }



}
