package com.chen.app_ec.app_main_activity.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chen.app_ec.R;

import java.util.ArrayList;

public class RecyclerAdapter_action  extends RecyclerView.Adapter<RecyclerAdapter_action.MyViewHolder> {


    AdapterOncllicListener mOnClickListener ;


    private ArrayList<String> data;
    private Context mContext;

    public RecyclerAdapter_action(Context context) {
        mContext = context;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public void setOnClickListener(AdapterOncllicListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview_action,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
         myViewHolder.mTextView.setText(data.get(i));
         myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                if (mOnClickListener != null){
                    mOnClickListener.onClick(view, myViewHolder.getAdapterPosition());
                }
             }
         });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_action);
        }
    }
}
