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

    public Bundle bundle = new Bundle(); //su issaugoti priskirt prie bundle pries.

    public String setDate; //datos stringas
    public String setTime;  //laiko stringas
    public String event; //pavadinimo stringas
    //Datepickeris
    private Button DisplayDate;
    private DatePickerDialog.OnDateSetListener DateSetListener;
    //Timepickeris
    private Button DisplayTime;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    //Issaugoti mygtukas initialization
    Button saveButton;
    //Textinput initialization
    EditText eventName;
    //alarm managerio


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
                //Priskirt prie bundle su raktazodziais
                String setDate = year + "/" + month + "/" + dayOfMonth;
                bundle.putString("Date", setDate); //Datos stringo pridejimas i bundle

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
            public void onTimeSet (TimePicker View,int hour, int minute){
                    setTime = String.valueOf(hour) + ":" + String.valueOf(minute);
                    bundle.putString("Time", setTime);
        }
        };

        //Intento sukurimas
        final Intent intent  = new Intent(EventGeneratorActivity.this,MainActivity.class);

        //Isaugoti mygtukas

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stEventName = eventName.getText().toString();
                bundle.putString("Name", stEventName);

                intent.putExtras(bundle);

                //Patikrinimas ivedimo
                if (bundle.getString("Date") == null) {
                    Toast.makeText(EventGeneratorActivity.this, "Pasirinkite data",
                       Toast.LENGTH_LONG).show();
                } else if (bundle.getString("Time") == null) {
                    Toast.makeText(EventGeneratorActivity.this, "Pasirinkite laika",
                            Toast.LENGTH_LONG).show();
                } else if (bundle.getString("Name") == null) {
                    Toast.makeText(EventGeneratorActivity.this, "Pasirinkite laika",
                            Toast.LENGTH_LONG).show();
                } else {

                    Notification notif = new Notification();
                    notif.setEventName(stEventName);
                    notif.setDate(setDate);
                    notif.setTime(setTime);
                    NotificationDatabase
                            .getDatabase(EventGeneratorActivity.this).
                                    notificationDAO().
                                    insertNotification(notif);
                    //Gryzimas atgal y main activity
                    setResult(RESULT_OK, intent);
                    finish();




                    //save to sharedpreferences
//                    SharedPreferences sharedPreferences = getSharedPreferences(
//                            "notificationInfo", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("1",bundle.getString("Name"));

                }
            }
        });
    }
}
