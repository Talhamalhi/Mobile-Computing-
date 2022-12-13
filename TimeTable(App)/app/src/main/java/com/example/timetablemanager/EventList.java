package com.example.timetablemanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kotlin.jvm.Synchronized;


public class EventList extends Fragment {

    //Making Singleton Object For class
    private EventList(){}
    private static EventList instance = null;
    @Synchronized
    public static EventList getInstance() {
        if(instance == null) instance = new EventList();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }
}