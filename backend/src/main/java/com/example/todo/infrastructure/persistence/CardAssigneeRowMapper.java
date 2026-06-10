package com.example.todo.infrastructure.persistence;

import com.example.todo.infrastructure.persistence.CardAssigneeRowExample;
import com.example.todo.infrastructure.persistence.CardAssigneeRowKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardAssigneeRowMapper {
    long countByExample(CardAssigneeRowExample example);

    int deleteByExample(CardAssigneeRowExample example);

    int deleteByPrimaryKey(CardAssigneeRowKey key);

    int insert(CardAssigneeRowKey row);

    int insertSelective(CardAssigneeRowKey row);

    List<CardAssigneeRowKey> selectByExample(CardAssigneeRowExample example);

    int updateByExampleSelective(@Param("row") CardAssigneeRowKey row, @Param("example") CardAssigneeRowExample example);

    int updateByExample(@Param("row") CardAssigneeRowKey row, @Param("example") CardAssigneeRowExample example);
}