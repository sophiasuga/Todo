package com.example.todo.resolver;

import com.example.todo.domain.TodoMapper;
import com.example.todo.domain.TodoService;
import com.example.todo.generated.CreateTodoInput;
import com.example.todo.generated.Todo;
import com.example.todo.generated.UpdateTodoInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class TodoResolver {

    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoResolver(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    @QueryMapping
    public List<Todo> todos() {
        return todoService.getAllTodos().stream().map(todoMapper::toTodo).toList();
    }

    @QueryMapping
    public Optional<Todo> todo(@Argument String id) {
        return todoService.getTodoById(id).map(todoMapper::toTodo);
    }

    @MutationMapping
    public Todo createTodo(@Argument CreateTodoInput input) {
        return todoMapper.toTodo(todoService.createTodo(input.title()));
    }

    @MutationMapping
    public Todo updateTodo(@Argument String id, @Argument UpdateTodoInput input) {
        return todoService.updateTodo(id, input.title(), input.completed())
                .map(todoMapper::toTodo)
                .orElse(null);
    }

    @MutationMapping
    public Boolean deleteTodo(@Argument String id) {
        return todoService.deleteTodo(id);
    }
}
