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

public class CompletedTrainingPickerViewActivity extends Dialog {

    PickerViewClickListener pickerViewClickListener;
    String  btnText;
    boolean isFinish;
    TextView btnOk,btnCancel;
    NumberPicker spinner;
    public CompletedTrainingPickerViewActivity(@NonNull Context context, String btnText, PickerViewClickListener pickerViewClickListener) {
        super(context);
        this.isFinish = isFinish;
        this.pickerViewClickListener = pickerViewClickListener;
        this.btnText = btnText;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_training_picker_view);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        btnOk = findViewById(R.id.textViewDone);
        btnCancel = findViewById(R.id.textViewCancel);
        spinner =  findViewById(R.id.picker_status);
        onClickListners();
    }

    private void onClickListners() {
        final String genders[] = { "Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday" };

        spinner.setMinValue(0);
        spinner.setMaxValue(genders.length - 1);
        spinner.setDisplayedValues(genders);
        spinner.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (genders.length > 0 ) {
                    pickerViewClickListener.onDoneClick(genders[spinner.getValue()], isFinish);
                }else{
                    pickerViewClickListener.onDoneClick("" + "", isFinish);
                }
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