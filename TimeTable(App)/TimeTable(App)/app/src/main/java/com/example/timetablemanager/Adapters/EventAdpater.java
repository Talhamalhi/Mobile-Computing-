package com.example.timetablemanager.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetablemanager.R;
import com.example.timetablemanager.model.EventModel;

import java.util.List;

import io.realm.Realm;

public class EventAdpater extends RecyclerView.Adapter<EventAdpater.ViewHolder> {
    Realm realm = Realm.getDefaultInstance();
    private List<EventModel> mData;
    private LayoutInflater mInflater;
    private EventAdpater.ItemClickListener mClickListener;
    public EventAdpater(Context context, List<EventModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    @Override
    public EventAdpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_timetable, parent, false);
        return new EventAdpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(mData.get(position).getName());
        holder.description.setText(mData.get(position).getDescription());
        holder.day.setText(mData.get(position).getDay());
        holder.time.setText(mData.get(position).getDate());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                EventModel eventModel = realm.where(EventModel.class)
                        .equalTo("id", mData.get(position).getId()).findFirst();
                eventModel.deleteFromRealm();
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
    void setClickListener(EventAdpater.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
