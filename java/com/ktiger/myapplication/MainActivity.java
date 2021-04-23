package com.ktiger.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(v -> {
            int offset = getResources().getDimensionPixelSize(R.dimen.item_size) +
                    (getResources().getDimensionPixelSize(R.dimen.item_margin) * 2);
            recyclerView.smoothScrollBy(offset, 0);
        });

        layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        MyItemDecoration itemDecoration = new MyItemDecoration(recyclerView, layoutManager);
        adapter = new ItemAdapter(this, recyclerView, layoutManager);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                // Defines a variable to store the action type for the incoming event
                final int action = event.getAction();

                // Handles each of the expected events
                switch(action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        return true;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        return true;
                    case DragEvent.ACTION_DROP:
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:
                        recyclerView.animate().translationYBy(100).setDuration(500);
                        return true;
                    default:
                        break;
                }

                return true;
            }
        });
    }
}