package com.samcain.memopad;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.samcain.memopad.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        database = new DatabaseHandler(this, null, null, 1);
        updateRecyclerView();

        binding.addMemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewMemo();
            }
        });
        binding.deleteMemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMemo();
            }
        });
    }

    public void addNewMemo() {
        String memo = binding.memoInput.getText().toString();
        database.addMemo(new Memo(memo));
        updateRecyclerView();
    }

    public void deleteMemo() {
        // TODO delete
        updateRecyclerView();
    }

    public void updateRecyclerView() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(database.getAllMemosAsList());
        /*
        TODO
        binding.output.setHasFixedSize(true);
        binding.output.setLayoutManager(new LinearLayoutManager(this));
        binding.output.setAdapter(adapter);
         */
    }


}