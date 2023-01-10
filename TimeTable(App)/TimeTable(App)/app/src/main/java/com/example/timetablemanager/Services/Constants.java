package com.example.timetablemanager.Services;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.example.timetablemanager.Activities.PopUp.CompletedTrainingPickerViewActivity;
import com.example.timetablemanager.Activities.PopUp.DateTimePickerViewActivity;
import com.example.timetablemanager.Utils.PickerViewClickListener;

public class Constants {

    public static void ShowStatusPickerViewScreen(Context context, String btnText, PickerViewClickListener pickerViewClickListener){
        CompletedTrainingPickerViewActivity pickerDialog=new CompletedTrainingPickerViewActivity(context,btnText,pickerViewClickListener);
        pickerDialog.setCancelable(false);
        pickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        pickerDialog.show();
    }
    public static void ShowDateTimePickerView(Context context, PickerViewClickListener pickerViewClickListener){
        DateTimePickerViewActivity pickerDialog=new DateTimePickerViewActivity(context,pickerViewClickListener);
        pickerDialog.setCancelable(false);
        pickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        pickerDialog.show();
    }
}
