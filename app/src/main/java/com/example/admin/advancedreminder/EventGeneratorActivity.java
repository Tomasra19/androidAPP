package com.example.admin.advancedreminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class EventGeneratorActivity extends AppCompatActivity {

    public String setDate;
    public String setTime;
    public String event;
    private Button DisplayDate;
    private DatePickerDialog.OnDateSetListener DateSetListener;
    private Button DisplayTime;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    Button saveButton;
    EditText eventName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_generator);

        //initialize views
        DisplayDate = findViewById(R.id.buttonDatePicker);
        DisplayTime = findViewById(R.id.buttonTimepicker);
        eventName = findViewById(R.id.eventTextInput);
        saveButton = findViewById(R.id.saveButton);

//datepickeris
        DisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        EventGeneratorActivity.this,
                        android.R.style.Theme_DeviceDefault_Light,
                        DateSetListener,
                        year, month, day);
                dialog.show();
            }
        });

        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                setDate = String.valueOf(year) + "/"
                        + String.valueOf(month) + "/"
                        + String.valueOf(dayOfMonth);

                Toast.makeText(EventGeneratorActivity.this, setDate,
                        Toast.LENGTH_LONG).show();
            }
        };
//time pickeris

        DisplayTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog dialog = new TimePickerDialog(
                        EventGeneratorActivity.this,
                        android.R.style.Theme_DeviceDefault,
                        timeSetListener,
                        hour,
                        minute,
                        true);
                dialog.show();
            }
        });
        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker View, int hour, int minute) {
                if (hour < 10 && minute < 10) {
                    setTime = "0" + String.valueOf(hour) + ":" + "0" + String.valueOf(minute);
                } else if (hour < 10) {
                    setTime = "0" + String.valueOf(hour) + ":" + String.valueOf(minute);
                } else if (minute < 10) {
                    setTime = String.valueOf(hour) + ":" + "0" + String.valueOf(minute);
                }
                Toast.makeText(EventGeneratorActivity.this, setTime,
                        Toast.LENGTH_LONG).show();
            }
        };

        //Intento sukurimas
        final Intent intent = new Intent(EventGeneratorActivity.this, MainActivity.class);

        //Isaugoti mygtukas

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event = eventName.getText().toString();
                //Database operacijos
                Notification notif = new Notification();
                notif.setEventName(event);
                notif.setDate(setDate);
                notif.setTime(setTime);
                MainActivity.database
                        .notificationDAO()
                        .insertNotification(notif);

//                Patikrinimas ivedimo
                if (notif.getDate() == null) {
                    Toast.makeText(EventGeneratorActivity.this, "Pasirinkite datą",
                            Toast.LENGTH_LONG).show();
                } else if (notif.getTime() == null) {
                    Toast.makeText(EventGeneratorActivity.this, "Pasirinkite laiką",
                            Toast.LENGTH_LONG).show();
                } else if (notif.getEventName() == null) {
                    Toast.makeText(EventGeneratorActivity.this, "Irašykite pavadinimą",
                            Toast.LENGTH_LONG).show();
                    return;
                } else {
                    //Gryzimas atgal y main activity
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
