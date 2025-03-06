package com.samcain.memopad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.samcain.memopad.databinding.ActivityMainBinding;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ActivityMainBinding binding;
    private List<Memo> data;
    public RecyclerViewAdapter(List<Memo> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ActivityMainBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ViewHolder holder = new ViewHolder(binding.getRoot());
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setMemo(data.get(position));
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Memo memo;
        private TextView memoLabel;

        public ViewHolder(View itemView) {
            super(itemView);
        }
        public Memo getMemo() {
            return memo;
        }
        public void setMemo(Memo memo) {
            this.memo = memo;
        }

        public void bindData() {
            if (memoLabel == null) {
                /*
                TODO
                memoLabel = (TextView) itemView.findViewById(R.id.memoLabel);
                 */
            }
            memoLabel.setText(memo.getMemo());
        }
    }
}