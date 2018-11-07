package com.example.admin.advancedreminder.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notification")
public class Notification {

        @NonNull
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="id")
        private  int id;
        @ColumnInfo(name="eventName")
        private String eventName;
        @ColumnInfo(name="date")
        private String date;
        @ColumnInfo(name="time")
        private String time;


        public Notification() {
        }

        public Notification (String eventName,String date, String time) {
            this.eventName = eventName;
            this.date = date;
            this.time = time;
        }

        @NonNull
        public int getId() {
            return id;
        }

        public void setId(@NonNull int id) {
            this.id = id;
        }

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @NonNull
        @Override
        public String toString() {
            return new StringBuilder(eventName).append(" ").append(date).append(" ").append(time).
                    toString();
        }
}
