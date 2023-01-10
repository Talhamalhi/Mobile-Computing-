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
import com.example.timetablemanager.model.RemindersModel;
import com.example.timetablemanager.model.TimeTableModel;

import java.util.Date;

import io.realm.Realm;

public class AddReminderActivity extends AppCompatActivity {
    Realm realm =Realm.getDefaultInstance();
    Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

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
                ShowStatusPickerViewScreen(AddReminderActivity.this, "Done", new PickerViewClickListener() {
                    @Override
                    public void onDoneClick(String year, String month, String days, boolean isFinish) {

                    }

                    @Override
                    public void onDoneClick(Date status, boolean isFinish) {

                    }

                    @Override
                    public void onDoneClick(String status, boolean isFinish) {
                        day.setText(status.toString());
                    }
                });
            }
        });

        dateTime.setClickable(true);
        dateTime.setFocusable(false);
        dateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDateTimePickerView(AddReminderActivity.this, new PickerViewClickListener() {
                    @Override
                    public void onDoneClick(String year, String month, String days, boolean isFinish) {

                    }

                    @Override
                    public void onDoneClick(Date status, boolean isFinish) {
                        date =status;
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
            RemindersModel remindersModel = new RemindersModel();

            Number newId = realm.where(RemindersModel.class).max("id");
            if(newId != null) {
                remindersModel.setId(newId.intValue() + 1);
            }
            else {
                remindersModel.setId(1);
            }
            remindersModel.setName(name.getText().toString());
            remindersModel.setDescription(description.getText().toString());
            remindersModel.setDay(day.getText().toString());
            remindersModel.setDate(date);
            realm.copyToRealmOrUpdate(remindersModel);
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