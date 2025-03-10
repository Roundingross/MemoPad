package com.samcain.memopad;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samcain.memopad.databinding.ActivityMainBinding;
import java.util.List;

/**
 * Main activity of the app that serves as view.
 * Displays memos and allows the user to add new ones.
 */
public class MainActivity extends AppCompatActivity implements MemoContract.View {
    // Variables
    private ActivityMainBinding binding;
    private MemoPresenter presenter;
    private RecyclerViewAdapter adapter;
    private int selectMemoId = -1;
    private final MemoPadClickHandler itemClick = new MemoPadClickHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        presenter = new MemoPresenter(this, this);
        presenter.loadMemos();

        binding.addMemoButton.setOnClickListener(v -> presenter.addMemo(binding.memoInput.getText().toString()));
        binding.deleteMemoButton.setOnClickListener(v -> {
            if (selectMemoId != -1) {
                presenter.deleteMemo(selectMemoId);
                selectMemoId = -1;
            } else {
                Toast.makeText(v.getContext(), "No memo selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public MemoPadClickHandler getItemClick() { return itemClick; }


    /**
     * Updates the recycler view with the new memos.
     * @param memoList - list of memos
     */
    @Override
    public void updateRecyclerView(List<Memo> memoList) {
        if (adapter == null) {
            adapter = new RecyclerViewAdapter(this, memoList);
            binding.memoOutput.setHasFixedSize(true);
            binding.memoOutput.setLayoutManager(new LinearLayoutManager(this));
            binding.memoOutput.setAdapter(adapter);
        } else {
            adapter.updateData(memoList);
        }
        selectMemoId = -1;
    }

    // Clears the input field
    public void clearInput() {
        binding.memoInput.setText("");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    /**
     * Handles clicks on the recycler view.
     */
    private class MemoPadClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int position = binding.memoOutput.getChildLayoutPosition(v);
            if (position == RecyclerView.NO_POSITION) return;

            Memo memo = adapter.getItem(position);
            if (memo != null) {
                selectMemoId = memo.getId();
                Toast.makeText(v.getContext(),"Selected Memo ID: " + selectMemoId, Toast.LENGTH_SHORT).show();
            } else {
                Log.e("MemoPadClickHandler", "Memo is null at position: " + position);
            }
        }
    }
}