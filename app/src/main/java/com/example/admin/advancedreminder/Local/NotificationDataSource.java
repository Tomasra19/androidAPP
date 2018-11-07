package com.example.admin.advancedreminder.Local;

import com.example.admin.advancedreminder.Database.INotificationDataSource;
import com.example.admin.advancedreminder.Model.Notification;

import java.util.List;

public class NotificationDataSource implements INotificationDataSource {

    private NotificationDAO notificationDAO;
    private static NotificationDataSource mInstance;

    public NotificationDataSource(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    public static NotificationDataSource getInstance(NotificationDAO notificationDAO){
        if (mInstance == null) {
            mInstance = new NotificationDataSource(notificationDAO);
        }
        return mInstance;
    }

    @Override
    public List<Notification> getNotificationById(int notificationId) {
        return notificationDAO.getNotificationById(notificationId);
    }

    @Override
    public List<List<Notification>> getAllNotifications() {
        return notificationDAO.getAllNotifications();
    }

    @Override
    public void insertNotification(Notification notication) {
        notificationDAO.insertNotification(notication);
    }

    @Override
    public void deleteNotification(Notification notification) {
        notificationDAO.deleteNotification(notification);
    }
}
