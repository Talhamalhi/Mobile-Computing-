package com.example.timetablemanager.Activities;

import static com.example.timetablemanager.Services.Constants.ShowDateTimePickerView;
import static com.example.timetablemanager.Services.Constants.ShowStatusPickerViewScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.timetablemanager.R;
import com.example.timetablemanager.Utils.PickerViewClickListener;
import com.example.timetablemanager.model.EventModel;
import com.example.timetablemanager.model.RemindersModel;
import com.example.timetablemanager.model.TimeTableModel;

import java.util.Date;

import io.realm.Realm;

public class AddEventActivity extends AppCompatActivity {
    Realm realm =Realm.getDefaultInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        EditText name = (EditText) findViewById(R.id.name);
        EditText description = (EditText) findViewById(R.id.description);
        EditText day = (EditText) findViewById(R.id.day);
        EditText dateTime = (EditText) findViewById(R.id.date_time);
        Button add = (Button)findViewById(R.id.add_timetable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        day.setClickable(true);
        day.setFocusable(false);
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowStatusPickerViewScreen(AddEventActivity.this, "Done", new PickerViewClickListener() {
                    @Override
                    public void onDoneClick(String year, String month, String days, boolean isFinish) {

                    }

                    @Override
                    public void onDoneClick(Date status, boolean isFinish) {

                    }

                    @Override
                    public void onDoneClick(String status, boolean isFinish) {
                        day.setText(status);
                    }
                });
            }
        });
        dateTime.setClickable(true);
        dateTime.setFocusable(false);
        dateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDateTimePickerView(AddEventActivity.this, new PickerViewClickListener() {
                    @Override
                    public void onDoneClick(String year, String month, String days, boolean isFinish) {

                    }

                    @Override
                    public void onDoneClick(Date status, boolean isFinish) {
                        dateTime.setText(status.toString());
                    }

                    @Override
                    public void onDoneClick(String status, boolean isFinish) {

                    }
                });
            }
        });



        add.setOnClickListener(view -> {
            realm.beginTransaction();
            EventModel eventModel = new EventModel();

            Number newId = realm.where(EventModel.class).max("id");
            if(newId != null){
                eventModel.setId(newId.intValue()+1);}
            else {
                eventModel.setId(1);
            }
            eventModel.setName(name.getText().toString());
            eventModel.setDescription(description.getText().toString());
            eventModel.setDay(day.getText().toString());
            eventModel.setDate(dateTime.getText().toString());
            realm.copyToRealmOrUpdate(eventModel);
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