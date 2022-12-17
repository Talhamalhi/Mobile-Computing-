package com.example.timetablemanager.leftdrawer;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timetablemanager.R;
import com.example.timetablemanager.TimeTable_Adapter;

import java.util.ArrayList;

import kotlin.jvm.Synchronized;

public class Timetable extends Fragment {
RecyclerView recyclerView;
    //Making Singleton Object For class
    private Timetable(){}
    private static Timetable instance = null;
    @Synchronized
    public static Timetable getInstance() {
        if(instance == null) instance = new Timetable();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // data to populate the RecyclerView with


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_timetable, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

//        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        // set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TimeTable_Adapter adapter = new TimeTable_Adapter(getActivity(), animalNames);
//        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

}