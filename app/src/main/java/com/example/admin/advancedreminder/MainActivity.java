package com.example.admin.advancedreminder;


import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    FloatingActionButton buttonAddNew;
    FloatingActionButton buttonDelete;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static NotificationDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        buttonAddNew = findViewById(R.id.ButtonAddNew);
        buttonDelete = findViewById(R.id.ButtonDelete);
        database = Room.databaseBuilder(getApplicationContext(),NotificationDatabase.class,"duomenys").allowMainThreadQueries().build();


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Notification> notificationList = database.notificationDAO().selectAll();

        adapter = new RecyclerAdapter(notificationList);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);



//        buttonDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                View view = (LayoutInflater.from(MainActivity.this))
//                        .inflate(R.layout.dialog_delete, null);
//                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
//                alertBuilder.setView(view);
//                final EditText userInput = (EditText) view.findViewById(R.id.deleteName);
//                alertBuilder.setCancelable(true)
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                NotificationDatabase
//                                        .getDatabase(MainActivity.this).notificationDAO().deleteNotification(userInput.getText().toString());
//                                finish();
//                                startActivity(getIntent());
//                            }
//                        });
//                Dialog dialog = alertBuilder.create();
//                dialog.show();
//            }
//        });

        buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EventGeneratorActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        database = Room.databaseBuilder(getApplicationContext(),NotificationDatabase.class,"duomenys").allowMainThreadQueries().build();

        List<Notification> notificationList = database.notificationDAO().selectAll();

        adapter = new RecyclerAdapter(notificationList);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
