package com.moore.springbootgraphqltoolsjpa.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.moore.springbootgraphqltoolsjpa.domain.Todo;
import com.moore.springbootgraphqltoolsjpa.repository.TodoRepository;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    private final TodoRepository todoRepository;

    public QueryResolver(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    Optional<Todo> getTodo(Long id) {
        return todoRepository.findById(id);
    }

    Connection<Todo> todos() {
        new SimpleListConnection<>()
    }
}
