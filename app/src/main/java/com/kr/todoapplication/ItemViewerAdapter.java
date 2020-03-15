package com.kr.todoapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemViewerAdapter extends RecyclerView.Adapter<ItemViewerAdapter.ItemViewHolder> {

    private static final String TAG = "ItemViewerAdapter";

    private Context context;
    private List<String> strings;

    public ItemViewerAdapter(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Log.d(TAG, "Binding");

        holder.textView.setText("111 AAAAA");
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "222 BBBBB", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }


    // view holder class
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parentLayout;
        ImageView imageView;
        TextView textView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            imageView = itemView.findViewById(R.id.ri_image);
            textView = itemView.findViewById(R.id.ri_text);
        }
    }
}
