package com.kr.todoapplication.persistance;

import com.kr.todoapplication.model.TodoItem;

import java.util.List;

public class TodoItemRepository implements Repository<TodoItem> {

    private TodoItemRepository() {}

    @Override
    public long save(TodoItem todoItem) {
        return todoItem.save();
    }

    @Override
    public void delete(TodoItem todoItem) {
        todoItem.delete();
    }

    @Override
    public List<TodoItem> findAll() {
        return TodoItem.listAll(TodoItem.class);
    }

    @Override
    public void deleteAll(List<TodoItem> todoItems) {
        TodoItem.deleteInTx(todoItems);
    }

    @Override
    public long totalCount() {
        return  TodoItem.count(TodoItem.class, null, null);
    }

    public static TodoItemRepository getInstance() {
        return LazyHolder.instance;
    }

    private static class LazyHolder {
        static final TodoItemRepository instance = new TodoItemRepository();
    }
}
