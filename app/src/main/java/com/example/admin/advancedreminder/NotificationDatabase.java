package com.example.admin.advancedreminder;




import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Notification.class},version = 1)
public abstract class NotificationDatabase extends RoomDatabase {

    public abstract NotificationDAO notificationDAO();


    private static volatile NotificationDatabase INSTANCE;

    static NotificationDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NotificationDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotificationDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }



}
