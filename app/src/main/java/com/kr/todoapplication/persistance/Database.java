package com.kr.todoapplication.persistance;

import com.kr.todoapplication.model.TodoItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {

    public static List<TodoItem> data;

    static {
        data = new ArrayList<>();
        data.add(new TodoItem("Do this", "This has to be done", false, new Date()));
        data.add(new TodoItem("Do this stuff", "This has to be done", true, new  Date()));
        data.add(new TodoItem("Important", "This has to be done", true, new  Date()));
        data.add(new TodoItem("Not so important", "This has to be done", false, new  Date()));
        data.add(new TodoItem("Header", "This has to be done", false, new  Date()));
        data.add(new TodoItem("Do this stuff 2", "This has to be done !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", true, new  Date()));
        data.add(new TodoItem("Postponed", "This has to be done", false, new  Date()));
    }

}
