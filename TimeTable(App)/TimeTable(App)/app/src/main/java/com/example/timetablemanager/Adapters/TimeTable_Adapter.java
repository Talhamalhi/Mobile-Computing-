package com.example.timetablemanager.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.timetablemanager.R;
import com.example.timetablemanager.model.TimeTableModel;

import java.util.List;

import io.realm.Realm;

public class TimeTable_Adapter extends RecyclerView.Adapter<TimeTable_Adapter.ViewHolder> {
    Realm realm = Realm.getDefaultInstance();
    private List<TimeTableModel> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    Context context;

    // data is passed into the constructor
    public TimeTable_Adapter(Context context, List<TimeTableModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context= context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_timetable, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(mData.get(position).getName());
        holder.description.setText(mData.get(position).getDescription());
        holder.day.setText(mData.get(position).getDay());
        holder.time.setText(mData.get(position).getDate());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                TimeTableModel timeTableModel = realm.where(TimeTableModel.class)
                        .equalTo("id", mData.get(position).getId()).findFirst();
                timeTableModel.deleteFromRealm();
                realm.commitTransaction();
                notifyDataSetChanged();
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
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
