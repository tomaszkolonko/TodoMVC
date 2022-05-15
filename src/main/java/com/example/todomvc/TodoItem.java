package com.example.todomvc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    private boolean checked;

    public TodoItem() {
    }

    public TodoItem(String description, boolean checked) {
        this.description = description;
        this.checked = checked;
    }


}
