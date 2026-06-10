package com.example.todo.infrastructure.persistence;

import java.util.List;

public interface TodoRowMapper {
    int insert(TodoRow row);

    List<TodoRow> selectAll();

    TodoRow selectByPrimaryKey(Long id);

    int updateByPrimaryKey(TodoRow row);

    int deleteByPrimaryKey(Long id);
}
