package com.example.timetablemanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kotlin.jvm.Synchronized;

public class DeleteEvent extends Fragment {

    //Making Singleton Object For class
    private DeleteEvent(){}
    private static DeleteEvent instance = null;
    @Synchronized
    public static DeleteEvent getInstance() {
        if(instance == null) instance = new DeleteEvent();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_event, container, false);
    }
}