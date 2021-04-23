package com.ktiger.myapplication;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    public MyItemDecoration(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        this.recyclerView = recyclerView;
        this.layoutManager = linearLayoutManager;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int first = layoutManager.findFirstVisibleItemPosition();
        int last = layoutManager.findLastVisibleItemPosition();
        for (int idx = first; idx <= last; idx++) {
            View view = layoutManager.findViewByPosition(idx);
            View imageView = view.findViewById(R.id.image_view);
            if (view.getX() < 0 || view.getX() + view.getWidth() > recyclerView.getWidth())
                imageView.setTranslationY(100);
            else
                imageView.setTranslationY(-100);
        }
    }
}
