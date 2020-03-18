package com.kr.todoapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private List<TodoItem> todoItems;

    public ItemViewerAdapter(Context context, List<TodoItem> todoItems) {
        this.context = context;
        this.todoItems = todoItems;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        Log.d(TAG, "Binding");

        TodoItem currentTodoItem = todoItems.get(position);

        String backgroundColor = position % 2 == 0 ? "#ffffff" : "#e6f5e6";

        holder.header.setText(currentTodoItem.getHeader());
        holder.content.setText(currentTodoItem.getContent());
        holder.parentLayout.setBackgroundColor(Color.parseColor(backgroundColor));

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String removedHeader = Database.data.get(position).getHeader();
                Database.data.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, Database.data.size());
                Toast.makeText(context, removedHeader, Toast.LENGTH_LONG).show();
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }


    // view holder class
    static class ItemViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout parentLayout;
        ImageView imageView;
        TextView header;
        TextView content;
        Button deleteButton;
        Button editButton;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            imageView = itemView.findViewById(R.id.ri_image);
            header = itemView.findViewById(R.id.ri_text);
            header.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
            content = itemView.findViewById(R.id.ri_content);
            deleteButton = itemView.findViewById(R.id.ri_delete_button);
            editButton = itemView.findViewById(R.id.ri_edit_button);
        }
    }
}
