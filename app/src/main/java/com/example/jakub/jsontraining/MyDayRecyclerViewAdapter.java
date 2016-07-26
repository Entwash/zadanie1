package com.example.jakub.jsontraining;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class MyDayRecyclerViewAdapter extends RecyclerView.Adapter<MyDayRecyclerViewAdapter.ViewHolder> {

    private final List<Day> mValues;
    private final DayFragment.OnListFragmentInteractionListener mListener;

    public MyDayRecyclerViewAdapter(List<Day> items, DayFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        //aningianinainigsani
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.app.setText(holder.mItem.getApp());
        holder.day.setText(holder.mItem.getName());
        holder.topic.setText(holder.mItem.getTopic());
        holder.plan.setText(holder.mItem.getPlan());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView app;
        public final TextView day;
        public final TextView topic;
        public final TextView plan;
        public Day mItem;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            app = (TextView) view.findViewById(R.id.app_textView);
            day = (TextView) view.findViewById(R.id.day_textView);
            topic = (TextView) view.findViewById(R.id.topic_textView);
            plan = (TextView) view.findViewById(R.id.plan_textView);
        }

    }
}
