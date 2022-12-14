package com.example.timetablemanager.leftdrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timetablemanager.R;

import kotlin.jvm.Synchronized;


public class Reminders extends Fragment {

    //Making Singleton Object For class
    private Reminders(){}
    private static Reminders instance = null;
    @Synchronized
    public static Reminders getInstance() {
        if(instance == null) instance = new Reminders();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminders, container, false);
    }
}