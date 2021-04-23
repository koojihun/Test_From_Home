package com.ktiger.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<Item> list;
    private LinearLayoutManager layoutManager;

    public ItemAdapter(Context context, RecyclerView recyclerView, LinearLayoutManager layoutManager) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.list = new ArrayList<>();
        this.layoutManager = layoutManager;
        for (int cnt = 0; cnt < 10; cnt++)
            list.add(new Item());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item, parent, false) ;
        ViewHolder vh = new ViewHolder(view) ;
        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView imageView = holder.itemView.findViewById(R.id.image_view);
        imageView.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.item_background));
        imageView.setOnLongClickListener(v -> {
            recyclerView.animate().translationYBy(-100).setDuration(500);
            imageView.startDrag(
                    null,  // the data to be dragged
                    new  View.DragShadowBuilder(imageView),  // the drag shadow builder
                    null,      // no need to use local data
                    0          // flags (not currently used, set to 0)
            );
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
