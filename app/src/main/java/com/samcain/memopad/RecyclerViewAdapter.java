package com.samcain.memopad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Adapter for the recycler view.
 * Displays memos in a list.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Memo> data;
    private final MainActivity activity;

    /**
     * Constructs a new instance of the adapter.
     * @param data - list of memos
     */
    public RecyclerViewAdapter(MainActivity activity, List<Memo> data) {
        this.activity = activity;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_item, parent, false);
        view.setOnClickListener(activity.getItemClick());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Memo getItem(int position) {
        return data.get(position);
    }

    public void updateData(List<Memo> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }


    /**
     * ViewHolder for the recycler view.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView memoLabel;
        public ViewHolder(View itemView) {
            super(itemView);
            memoLabel = itemView.findViewById(R.id.memoLabel);
        }
        public void bindData(Memo memo) {
            memoLabel.setText("ID: " + memo.getId() + " - " + memo.getMemo());
        }
    }
}