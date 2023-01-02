package com.example.timetablemanager.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.Reminders.R;
import com.example.Reminders.model.RemindersModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class Reminders_Adapter extends RecyclerView.Adapter<Reminders_Adapter.ViewHolder> {
    Realm realm = Realm.getDefaultInstance();
    private List<RemindersModel> mData;
    private LayoutInflater mInflater;
    private com.example.timetablemanager.Reminders_Adapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    public Reminders_Adapter(Context context, List<RemindersModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public com.example.timetablemanager.Reminders_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_Reminders, parent, false);
        return new com.example.timetablemanager.Reminders_Adapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(com.example.timetablemanager.Reminders_Adapter.ViewHolder holder, int position) {

        holder.name.setText(mData.get(position).getName());
        holder.description.setText(mData.get(position).getDescription());
        holder.day.setText(mData.get(position).getDay());
        holder.time.setText(mData.get(position).getTime());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                RemindersModel RemindersModel = realm.where(RemindersModel.class)
                        .equalTo("id", mData.get(position).getId()).findFirst();
                RemindersModel.deleteFromRealm();
                realm.commitTransaction();

            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView description;
        TextView day;
        TextView time;
        TextView deleteBtn;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            day = itemView.findViewById(R.id.day);
            time = itemView.findViewById(R.id.time);
            deleteBtn = itemView.findViewById(R.id.del_btn);

        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    void setClickListener(com.example.timetablemanager.Reminders_Adapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
