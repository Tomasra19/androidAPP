package com.example.admin.advancedreminder;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface NotificationDAO {

    @Query("SELECT * from notification")
    List<Notification> selectAll();

    @Delete
    void delete(Notification notification);

    @Insert
    void insertNotification(Notification notications);

    @Query("DELETE FROM notification WHERE eventName = :eventName")
    void deleteNotification(String eventName);

    @Query("DELETE FROM notification ")
    void deleteAll();
}
