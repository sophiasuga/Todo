package com.example.todo.infrastructure.persistence;

import com.example.todo.infrastructure.persistence.UserRow;
import com.example.todo.infrastructure.persistence.UserRowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRowMapper {
    long countByExample(UserRowExample example);

    int deleteByExample(UserRowExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRow row);

    int insertSelective(UserRow row);

    List<UserRow> selectByExample(UserRowExample example);

    UserRow selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UserRow row, @Param("example") UserRowExample example);

    int updateByExample(@Param("row") UserRow row, @Param("example") UserRowExample example);

    int updateByPrimaryKeySelective(UserRow row);

    int updateByPrimaryKey(UserRow row);
}