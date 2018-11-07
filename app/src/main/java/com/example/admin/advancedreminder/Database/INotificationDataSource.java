package com.example.admin.advancedreminder.Database;

import com.example.admin.advancedreminder.Model.Notification;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

public interface INotificationDataSource {

    List<Notification> getNotificationById(int notificationId);
    List<List<Notification>> getAllNotifications();
    void insertNotification (Notification notications);
    void deleteNotification (Notification notification);
}
