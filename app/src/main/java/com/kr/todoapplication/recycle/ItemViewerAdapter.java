package com.kr.todoapplication.recycle;

import android.content.Context;
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

import com.kr.todoapplication.R;
import com.kr.todoapplication.model.TodoItem;
import com.kr.todoapplication.persistance.TodoItemRepository;

import java.util.List;

public class ItemViewerAdapter extends RecyclerView.Adapter<ItemViewerAdapter.ItemViewHolder> {

    private static final String TAG = "ItemViewerAdapter";

    private Context context;
    private List<TodoItem> todoItems;

    public ItemViewerAdapter(Context context) {
        this.context = context;
        this.todoItems = TodoItemRepository.getInstance().findAll();
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
        Log.d(TAG, "Binding. Position: " + position);

        TodoItem currentTodoItem = todoItems.get(position);

        holder.header.setText(currentTodoItem.getHeader());
        holder.content.setText(currentTodoItem.getContent());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItemAt(position);
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateItemAt(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (int) TodoItemRepository.getInstance().totalCount();
    }

    private void deleteItemAt(int position) {
        TodoItem todoItem = todoItems.get(position);
        TodoItemRepository.getInstance().delete(todoItem);
        todoItems.remove(todoItem);

        Log.d(TAG, "Deleted item at " + position);

        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());

        Toast.makeText(context, String.valueOf(todoItem.getId()), Toast.LENGTH_LONG).show();
    }

    private void updateItemAt(int position) {
        Log.d(TAG, "An item should be updated (not implemented)");
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
