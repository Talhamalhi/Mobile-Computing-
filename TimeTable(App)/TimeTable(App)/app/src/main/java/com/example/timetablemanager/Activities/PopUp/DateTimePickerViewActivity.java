package com.example.timetablemanager.Activities.PopUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.timetablemanager.R;
import com.example.timetablemanager.Utils.PickerViewClickListener;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;

public class DateTimePickerViewActivity extends Dialog {
    PickerViewClickListener pickerViewClickListener;
   // String  btnText;
    boolean isFinish;
    TextView btnOk,btnCancel;
    SingleDateAndTimePicker spinner;
    public DateTimePickerViewActivity(@NonNull Context context, PickerViewClickListener pickerViewClickListener) {
        super(context);
        this.isFinish = isFinish;
        this.pickerViewClickListener = pickerViewClickListener;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_picker_view);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        btnOk = findViewById(R.id.textViewDone);
        btnCancel = findViewById(R.id.textViewCancel);
        spinner =  findViewById(R.id.picker_status);
        onClickListners();
    }

    private void onClickListners() {
        spinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                   pickerViewClickListener.onDoneClick(spinner.getDate(), isFinish);

            }


        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}