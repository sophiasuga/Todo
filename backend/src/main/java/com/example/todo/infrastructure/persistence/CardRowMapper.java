package com.example.todo.infrastructure.persistence;

import com.example.todo.infrastructure.persistence.CardRow;
import com.example.todo.infrastructure.persistence.CardRowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardRowMapper {
    long countByExample(CardRowExample example);

    int deleteByExample(CardRowExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CardRow row);

    int insertSelective(CardRow row);

    List<CardRow> selectByExample(CardRowExample example);

    CardRow selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CardRow row, @Param("example") CardRowExample example);

    int updateByExample(@Param("row") CardRow row, @Param("example") CardRowExample example);

    int updateByPrimaryKeySelective(CardRow row);

    int updateByPrimaryKey(CardRow row);
}