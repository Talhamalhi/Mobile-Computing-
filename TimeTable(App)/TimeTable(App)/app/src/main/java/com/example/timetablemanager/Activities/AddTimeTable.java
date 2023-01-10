package com.example.timetablemanager.Activities;

import static com.example.timetablemanager.Services.Constants.ShowDateTimePickerView;
import static com.example.timetablemanager.Services.Constants.ShowStatusPickerViewScreen;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetablemanager.R;
import com.example.timetablemanager.Utils.PickerViewClickListener;
import com.example.timetablemanager.model.TimeTableModel;

import java.util.Date;

import io.realm.Realm;

public class AddTimeTable extends AppCompatActivity {
 Realm realm =Realm.getDefaultInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timetable);

        setUpUI();

    }

    private void setUpUI() {
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
                ShowStatusPickerViewScreen(AddTimeTable.this, "Done", new PickerViewClickListener() {
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
                ShowDateTimePickerView(AddTimeTable.this, new PickerViewClickListener() {
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
            TimeTableModel timeTableModel = new TimeTableModel();

            Number newId = realm.where(TimeTableModel.class).max("id");
            if(newId != null){
                timeTableModel.setId(newId.intValue()+1);}
            else {
                timeTableModel.setId(1);
            }
            timeTableModel.setName(name.getText().toString());
            timeTableModel.setDescription(description.getText().toString());
            timeTableModel.setDay(day.getText().toString().trim());
            Log.d(":::time",dateTime.getText().toString().trim());
            timeTableModel.setDate(dateTime.getText().toString().trim());
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
