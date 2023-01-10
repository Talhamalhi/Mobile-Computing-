package com.example.timetablemanager.leftdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetablemanager.Activities.AddReminderActivity;
import com.example.timetablemanager.R;

import com.example.timetablemanager.Adapters.Reminders_Adapter;
import com.example.timetablemanager.model.RemindersModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.realm.Realm;
import kotlin.jvm.Synchronized;

public class Reminders extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton addReminders;
    //Making Singleton Object For class
    private Reminders() {
    }
    TextView textView;
    private static Reminders instance = null;
    FloatingActionButton addTimetable;

    @Synchronized
    public static Reminders getInstance() {
        if (instance == null) instance = new Reminders();
        return instance;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<RemindersModel> realmList = Realm.getDefaultInstance().where(RemindersModel.class).findAll();
        if(realmList.size() >0){
            textView.setVisibility(View.GONE);
        }else{
            textView.setVisibility(View.VISIBLE);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Reminders_Adapter adapter = new Reminders_Adapter (getActivity(), realmList);

        recyclerView.setAdapter(adapter);
        addReminders.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AddReminderActivity.class);
            startActivity(intent);

        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reminders, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        addReminders = (FloatingActionButton) rootView.findViewById(R.id.add_reminders);
        textView = (TextView) rootView.findViewById(R.id.textViewempty);
        return rootView;
    }

}