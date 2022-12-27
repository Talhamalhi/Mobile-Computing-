package com.example.timetablemanager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetablemanager.model.TimeTableModel;

import io.realm.Realm;

public class AddTimeTable extends AppCompatActivity {
 Realm realm =Realm.getDefaultInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timetable);

        EditText name = (EditText) findViewById(R.id.name);
        EditText description = (EditText) findViewById(R.id.description);
        EditText day = (EditText) findViewById(R.id.day);
        EditText dateTime = (EditText) findViewById(R.id.date_time);
        Button add = (Button)findViewById(R.id.add_timetable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add.setOnClickListener(view -> {
            realm.executeTransactionAsync(realm -> {
                //create task
                realm.beginTransaction();
                TimeTableModel timeTableModel = new TimeTableModel();
                timeTableModel.setName("Hello Talha");
                timeTableModel.setDay("Talha Day");
                timeTableModel.setDate("Date here");
                timeTableModel.setDescription("Pdf reader");
                realm.beginTransaction();
                realm.copyFromRealm(timeTableModel);

            });

//            Realm.getDefaultInstance().executeTransactionAsync(realm -> realm.copyFromRealm(timeTableModel));

        });




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
