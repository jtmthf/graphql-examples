package com.moore.springbootgraphqltoolsjpa.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.moore.springbootgraphqltoolsjpa.domain.Todo;
import com.moore.springbootgraphqltoolsjpa.model.CreateTodoInput;
import com.moore.springbootgraphqltoolsjpa.model.UpdateTodoInput;
import com.moore.springbootgraphqltoolsjpa.repository.TodoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    private final TodoRepository todoRepository;

    public MutationResolver(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(CreateTodoInput input) {
        return todoRepository.save(input.toEntity());
    }

    @Transactional
    public Todo updateTodo(UpdateTodoInput input) {
        Todo todo = todoRepository.findById(input.getId()).orElseThrow(RuntimeException::new);
        input.applyToEntity(todo);

        return todo;
}
}
