package com.example.todomvc;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    void addItem(String itemText) {
        TodoItem todoItem = new TodoItem(itemText, false);
        todoRepository.save(todoItem);
    }
}
