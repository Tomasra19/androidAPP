package com.example.admin.advancedreminder.Database;

import com.example.admin.advancedreminder.Model.Notification;

import java.util.List;

public class NotificationRepository implements INotificationDataSource {

    private INotificationDataSource mLocalDataSource;
    private static NotificationRepository mInstance;

    public NotificationRepository(INotificationDataSource mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }
    public static NotificationRepository getmInstance(INotificationDataSource mLocalDataSource) {
        if (mInstance == null) {
            mInstance = new NotificationRepository(mLocalDataSource);
        }
        return mInstance;
    }

    @Override
    public List<Notification> getNotificationById(int notificationId) {
        return mLocalDataSource.getNotificationById(notificationId);
    }

    @Override
    public List<List<Notification>> getAllNotifications() {
        return mLocalDataSource.getAllNotifications();
    }

    @Override
    public void insertNotification(Notification notication) {
        mLocalDataSource.insertNotification(notication);
    }

    @Override
    public void deleteNotification(Notification notification) {
        mLocalDataSource.deleteNotification(notification);
    }
}
