package com.moore.springbootgraphqltoolsjpa.model;

import com.moore.springbootgraphqltoolsjpa.domain.Todo;

public class UpdateTodoInput {
    private Long id;
    private String title;
    private Boolean complete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void applyToEntity(Todo todo) {
        if (title != null) {
            todo.setTitle(title);
        }
        if (complete != null) {
            todo.setComplete(complete);
        }
    }
}
