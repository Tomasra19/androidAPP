package com.example.admin.advancedreminder;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Bundle bundle;
    ListView listView;

    public static NotificationDatabase notificationDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Notification> notifications = NotificationDatabase
                .getDatabase(this).notificationDAO().selectAll();

        for (Notification notification : notifications) {
            System.out.println(notification);
        }


        FloatingActionButton button1 = findViewById(R.id.ButtonAddNew);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EventGeneratorActivity.class);
                startActivityForResult(intent, 1);}
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        bundle = data.getExtras();
        if (requestCode == 1) {
            if(resultCode == EventGeneratorActivity.RESULT_OK){

                ArrayList <String> arrayList = new ArrayList<>();
                arrayList.add(bundle.getString("Name") + " " + bundle.getString("Date")
                    + " " + bundle.getString("Time"));
                listView = findViewById(R.id.dialoglistview);

            ArrayAdapter arrayAdapter = new ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    arrayList);
            listView.setAdapter(arrayAdapter);
            }
        }
    }
}
