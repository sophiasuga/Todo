package com.example.todo.infrastructure.persistence;

import com.example.todo.infrastructure.persistence.CardLabelRowExample;
import com.example.todo.infrastructure.persistence.CardLabelRowKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardLabelRowMapper {
    long countByExample(CardLabelRowExample example);

    int deleteByExample(CardLabelRowExample example);

    int deleteByPrimaryKey(CardLabelRowKey key);

    int insert(CardLabelRowKey row);

    int insertSelective(CardLabelRowKey row);

    List<CardLabelRowKey> selectByExample(CardLabelRowExample example);

    int updateByExampleSelective(@Param("row") CardLabelRowKey row, @Param("example") CardLabelRowExample example);

    int updateByExample(@Param("row") CardLabelRowKey row, @Param("example") CardLabelRowExample example);
}