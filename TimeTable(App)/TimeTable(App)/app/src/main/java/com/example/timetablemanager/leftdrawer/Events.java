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

import com.example.timetablemanager.Activities.AddEventActivity;
import com.example.timetablemanager.Adapters.EventAdpater;
import com.example.timetablemanager.R;
import com.example.timetablemanager.model.EventModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import io.realm.Realm;
import kotlin.jvm.Synchronized;


public class Events extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton addEvents;
    //Making Singleton Object For class
    private Events(){}
    TextView textView;
    private static Events instance = null;
    @Synchronized
    public static Events getInstance() {
        if(instance == null) instance = new Events();
        return instance;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<EventModel> realmList = Realm.getDefaultInstance().where(EventModel.class).findAll();
        if(realmList.size() >0){
            textView.setVisibility(View.GONE);
        }else{
            textView.setVisibility(View.VISIBLE);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        EventAdpater adapter = new EventAdpater (getActivity(), realmList);

        recyclerView.setAdapter(adapter);
        addEvents.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), AddEventActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        addEvents = (FloatingActionButton) rootView.findViewById(R.id.addEvents);
        textView = (TextView) rootView.findViewById(R.id.textViewempty);
        return rootView;
    }
}