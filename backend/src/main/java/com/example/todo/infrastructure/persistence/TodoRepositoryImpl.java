package com.example.todo.infrastructure.persistence;

import com.example.todo.domain.Todo;
import com.example.todo.domain.TodoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

    private final TodoRowMapper mapper;

    public TodoRepositoryImpl(TodoRowMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Todo> findAll() {
        return mapper.selectAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(mapper.selectByPrimaryKey(id)).map(this::toDomain);
    }

    @Override
    public Todo save(Todo todo) {
        TodoRow row = toRow(todo);
        if (todo.getId() == null) {
            mapper.insert(row);  // useGeneratedKeys により row.id がセットされる
        } else {
            mapper.updateByPrimaryKey(row);
        }
        return toDomain(row);
    }

    @Override
    public boolean delete(Long id) {
        return mapper.deleteByPrimaryKey(id) > 0;
    }

    private Todo toDomain(TodoRow row) {
        return Todo.reconstruct(
            row.getId(),
            row.getTitle(),
            Boolean.TRUE.equals(row.getCompleted()),
            row.getCreatedAt()
        );
    }

    private TodoRow toRow(Todo todo) {
        TodoRow row = new TodoRow();
        row.setId(todo.getId());
        row.setTitle(todo.getTitle());
        row.setCompleted(todo.isCompleted());
        row.setCreatedAt(todo.getCreatedAt());
        return row;
    }
}
