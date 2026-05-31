package com.example.todo.resolver;

import com.example.todo.generated.Todo;
import com.example.todo.generated.CreateTodoInput;
import com.example.todo.generated.UpdateTodoInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class TodoResolver {

    @QueryMapping
    public List<Todo> todos() {
        return List.of();
    }

    @QueryMapping
    public Optional<Todo> todo(@Argument String id) {
        return Optional.empty();
    }

    @MutationMapping
    public Todo createTodo(@Argument CreateTodoInput input) {
        throw new UnsupportedOperationException("Phase 2 で実装予定");
    }

    @MutationMapping
    public Todo updateTodo(@Argument String id, @Argument UpdateTodoInput input) {
        throw new UnsupportedOperationException("Phase 2 で実装予定");
    }

    @MutationMapping
    public Boolean deleteTodo(@Argument String id) {
        throw new UnsupportedOperationException("Phase 2 で実装予定");
    }
}
