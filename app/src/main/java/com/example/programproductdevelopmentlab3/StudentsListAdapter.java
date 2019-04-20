package com.example.programproductdevelopmentlab3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class StudentsListAdapter extends RecyclerView.Adapter<StudentsListAdapter.HistoryViewHolder> {
    private ArrayList<StudentsList> historyData;

    StudentsListAdapter(ArrayList<StudentsList> historyData) {
        this.historyData = historyData;
    }
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleview_layout, viewGroup, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder historyViewHolder, int i) {
        historyViewHolder.studentInfo.setText(historyData.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return historyData.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        private TextView studentInfo;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            studentInfo = itemView.findViewById(R.id.studentHistory);
        }
    }
}