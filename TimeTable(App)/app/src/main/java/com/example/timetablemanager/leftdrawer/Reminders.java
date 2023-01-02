package com.example.timetablemanager.leftdrawer;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.timetablemanager.AddTimeTable;
import com.example.timetablemanager.R;
import com.example.timetablemanager.TimeTable_Adapter;
import com.example.timetablemanager.model.Reminders_Adapter;
import com.example.timetablemanager.model.TimeTableModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.realm.Realm;
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
        Reminders_Adapter adapter = new Reminders_Adapter(getActivity(), realmList);

        recyclerView.setAdapter(adapter);
        addReminders.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), Reminders.class);
            startActivity(intent);

        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reminders, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        Reminders = (FloatingActionButton) rootView.findViewById(R.id.add_Reminders);


        return rootView;
    }

}

    





