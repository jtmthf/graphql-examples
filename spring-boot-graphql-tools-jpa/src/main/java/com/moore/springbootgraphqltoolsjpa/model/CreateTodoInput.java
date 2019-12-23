package com.moore.springbootgraphqltoolsjpa.model;

import com.moore.springbootgraphqltoolsjpa.domain.Todo;

public class CreateTodoInput {
    private String title;
    private Boolean complete;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Todo toEntity() {
        Todo todo = new Todo(title);
        if (complete != null) {
            todo.setComplete(complete);
        }
        return todo;
    }
}
