package com.example.todo.infrastructure.persistence;

import com.example.todo.infrastructure.persistence.LabelRow;
import com.example.todo.infrastructure.persistence.LabelRowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LabelRowMapper {
    long countByExample(LabelRowExample example);

    int deleteByExample(LabelRowExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LabelRow row);

    int insertSelective(LabelRow row);

    List<LabelRow> selectByExample(LabelRowExample example);

    LabelRow selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") LabelRow row, @Param("example") LabelRowExample example);

    int updateByExample(@Param("row") LabelRow row, @Param("example") LabelRowExample example);

    int updateByPrimaryKeySelective(LabelRow row);

    int updateByPrimaryKey(LabelRow row);
}