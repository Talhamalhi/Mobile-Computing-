package com.example.timetablemanager.Utils;

import java.util.Date;

public interface PickerViewClickListener {

    void onDoneClick(String year,String month,String days,boolean isFinish);
    void onDoneClick(Date status, boolean isFinish);
    void onDoneClick(String status, boolean isFinish);


}
