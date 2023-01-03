package com.example.timetablemanager.leftdrawer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timetablemanager.AddTimeTable;
import com.example.timetablemanager.R;
import com.example.timetablemanager.RemindersModel;
import com.example.timetablemanager.TimeTable_Adapter;
import com.example.timetablemanager.model.Reminders_Adapter;
import com.example.timetablemanager.model.TimeTableModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import kotlin.jvm.Synchronized;

public class Reminders extends Fragment {
    RecyclerView recyclerView;
    //Making Singleton Object For class
    private Reminders() {
    }

    private static Reminders instance = null;
    FloatingActionButton addTimetable;

    @Synchronized
    public static Reminders getInstance() {
        if (instance == null) instance = new Reminders();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // data to populate the RecyclerView with


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);


    }
    @Override
    public void onResume() {
        super.onResume();
        List<RemindersModel> realmList = Realm.getDefaultInstance().where(RemindersModel.class).findAll();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Reminders_Adapter adapter = new Reminders_Adapter (getActivity(), realmList);

        recyclerView.setAdapter(adapter);
        addReminders.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AddReminders.class);
            startActivity(intent);

        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reminders, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        addReminders = (FloatingActionButton) rootView.findViewById(R.id.add_Reminders);


        return rootView;
    }

}