package com.example.admin.advancedreminder.Local;

import android.support.design.widget.FloatingActionButton;

import com.example.admin.advancedreminder.Model.Notification;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

public interface NotificationDAO {
    @Query("SELECT * FROM notification WHERE id=:notificationId")
    List<Notification> getNotificationById(int notificationId);

    @Query("SELECT * FROM notification")
    List<List<Notification>> getAllNotifications();

    @Insert
    void insertNotification (Notification notications);
    @Delete
    void deleteNotification (Notification notification);
}
