package com.example.timetablemanager.Adapters;

import static android.content.Context.ALARM_SERVICE;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetablemanager.R;
import com.example.timetablemanager.Utils.AlarmReceiver;
import com.example.timetablemanager.model.RemindersModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;

public class Reminders_Adapter extends RecyclerView.Adapter<Reminders_Adapter.ViewHolder> {
    Realm realm = Realm.getDefaultInstance();
    private List<RemindersModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    Context context;

    // data is passed into the constructor
    public Reminders_Adapter(Context context, List<RemindersModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_raminder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            holder.name.setText(mData.get(position).getName());
            holder.description.setText(mData.get(position).getDescription());
            holder.day.setText(mData.get(position).getDay());
            holder.time.setText(mData.get(position).getDate().toString());
            holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    realm.beginTransaction();
                    RemindersModel timeTableModel = realm.where(RemindersModel.class)
                            .equalTo("id", mData.get(position).getId()).findFirst();
                    timeTableModel.deleteFromRealm();
                    realm.commitTransaction();
                    notifyDataSetChanged();
                }
            });

        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    SimpleDateFormat sourceFormat = new SimpleDateFormat("EEE, MMM dd yyyy; hh:mm:ss a", Locale.ENGLISH);
//                    Date date = new Date();
//                    try {
//                        date = sourceFormat.parse(mData.get(position).getDate().trim());
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
                    OnToggleClicked(holder.toggleButton, mData.get(position).getDate());
                } else {
                    // The toggle is disabled
                }
            }
        });

    }

    // binds the data to the TextView in each row




    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void OnToggleClicked(ToggleButton toggleButton, Date date) {
        long time;
        if (!toggleButton.isChecked()) {
            try {
                Toast.makeText(context, "ALARM ON", Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();
                Log.d(":::date",date.getHours()+":::"+date.getMinutes());
                // calendar is called to get current time in hour and minute
                calendar.set(Calendar.HOUR_OF_DAY, date.getHours());
                calendar.set(Calendar.MINUTE, date.getMinutes());

                // using intent i have class AlarmReceiver class which inherits
                // BroadcastReceiver
                Intent intent = new Intent(context, AlarmReceiver.class);

                // we call broadcast using pendingIntent
                pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

                time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                if (System.currentTimeMillis() > time) {
                    // setting time as AM and PM
                    if (Calendar.AM_PM == 0)
                        time = time + (1000 * 60 * 60 * 12);
                    else
                        time = time + (1000 * 60 * 60 * 24);
                }
                // Alarm rings continuously until toggle button is turned off
                alarmManager.setExact(AlarmManager. ELAPSED_REALTIME_WAKEUP, 10000, pendingIntent);
                //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
                // alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (time * 1000), pendingIntent);

            }catch (Exception e){
                e.printStackTrace();
            }
             } else {
            if(pendingIntent != null) {
                alarmManager.cancel(pendingIntent);
            }
            Toast.makeText(context, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }
    }
    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView description;
        TextView day;
        TextView time;
        TextView deleteBtn;
        ToggleButton toggleButton;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            day = itemView.findViewById(R.id.day);
            time = itemView.findViewById(R.id.time);
            deleteBtn = itemView.findViewById(R.id.del_btn);
            toggleButton = itemView.findViewById(R.id.toggleButton);

        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
