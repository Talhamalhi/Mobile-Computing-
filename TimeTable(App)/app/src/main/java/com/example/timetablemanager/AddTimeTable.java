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
            realm.beginTransaction();
            TimeTableModel timeTableModel = new TimeTableModel();

            Number newId = realm.where(TimeTableModel.class).max("id");
            if(newId != null)
            timeTableModel.setId(newId.intValue()+1);
            else
                timeTableModel.setId(1);

            timeTableModel.setName(name.getText().toString());
            timeTableModel.setDescription(description.getText().toString());
            timeTableModel.setDay(day.getText().toString());
            timeTableModel.setDate(dateTime.getText().toString());
            realm.copyToRealmOrUpdate(timeTableModel);
            realm.commitTransaction();
            finish();
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
