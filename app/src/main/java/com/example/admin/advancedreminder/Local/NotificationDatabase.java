package com.example.admin.advancedreminder.Local;


import android.content.Context;

import com.example.admin.advancedreminder.Model.Notification;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import static com.example.admin.advancedreminder.Local.NotificationDatabase.DATABASE_VERSION;

@Database(entities = Notification.class,version = DATABASE_VERSION)
public abstract class NotificationDatabase extends RoomDatabase {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="notification_db";

    public abstract NotificationDAO notificationDAO();
    private static NotificationDatabase mInstance;

    public static NotificationDatabase getmInstance(Context context) {

        if (mInstance == null){
            mInstance = Room.databaseBuilder(context,NotificationDatabase.class,
                    DATABASE_NAME).
                    fallbackToDestructiveMigration().build();
        }
        return mInstance;
    }


}
