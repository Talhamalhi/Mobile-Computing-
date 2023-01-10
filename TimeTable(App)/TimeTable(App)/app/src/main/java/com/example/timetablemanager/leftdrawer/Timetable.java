package com.example.timetablemanager.leftdrawer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timetablemanager.Activities.AddTimeTable;
import com.example.timetablemanager.R;
import com.example.timetablemanager.Adapters.TimeTable_Adapter;
import com.example.timetablemanager.model.TimeTableModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.realm.Realm;
import kotlin.jvm.Synchronized;

public class Timetable extends Fragment {
    RecyclerView recyclerView;
    //Making Singleton Object For class
    private Timetable() {
    }
    TextView textView;
    private static Timetable instance = null;
    FloatingActionButton addTimetable;

    @Synchronized
    public static Timetable getInstance() {
        if (instance == null) instance = new Timetable();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_timetable, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        addTimetable = (FloatingActionButton) rootView.findViewById(R.id.add_timetable);
        textView = (TextView) rootView.findViewById(R.id.textViewempty);

        setUpUI();
        return rootView;
    }

    private void setUpUI() {

    }

    @Override
    public void onResume() {
        super.onResume();
        List<TimeTableModel> realmList = Realm.getDefaultInstance().where(TimeTableModel.class).findAll();
        if(realmList.size() >0){
            textView.setVisibility(View.GONE);
        }else{
            textView.setVisibility(View.VISIBLE);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TimeTable_Adapter adapter = new TimeTable_Adapter(getActivity(), realmList);

        recyclerView.setAdapter(adapter);
        addTimetable.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AddTimeTable.class);
            startActivity(intent);

        });

    }

}