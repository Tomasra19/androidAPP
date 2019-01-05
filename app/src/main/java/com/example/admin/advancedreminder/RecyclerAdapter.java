package com.example.admin.advancedreminder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    List<Notification> notificationList;


    public RecyclerAdapter(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Notification currentNotification = notificationList.get(position);

        holder.notificationTitle.setText(currentNotification.getEventName());
        holder.notificationDate.setText(currentNotification.getDate() + " " + currentNotification.getTime());



    }
    @Override
    public int getItemCount() {
        return notificationList.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView notificationTitle, notificationDate;

        public MyViewHolder(View view) {
            super(view);

            notificationDate = itemView.findViewById(R.id.notification_date);
            notificationTitle = itemView.findViewById(R.id.notification_title);
        }

    }

    public void deleteItem(int position) {
        try{

            MainActivity.database.notificationDAO().delete(notificationList.get(position));
            notificationList.remove(position);
            notifyItemRemoved(position);

        }catch (Exception e){

        }

    }
}
