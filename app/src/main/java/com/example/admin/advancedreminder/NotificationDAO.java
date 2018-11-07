package com.example.admin.advancedreminder;



import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
@Dao
public interface NotificationDAO {

    @Query("SELECT * from notification")
    List<Notification> selectAll();
    @Insert
    void insertNotification (Notification notications);
    @Delete
    void deleteNotification (Notification notification);
}
