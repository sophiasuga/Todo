package com.example.todo.infrastructure.persistence;

import com.example.todo.infrastructure.persistence.BoardColumnRow;
import com.example.todo.infrastructure.persistence.BoardColumnRowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoardColumnRowMapper {
    long countByExample(BoardColumnRowExample example);

    int deleteByExample(BoardColumnRowExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BoardColumnRow row);

    int insertSelective(BoardColumnRow row);

    List<BoardColumnRow> selectByExample(BoardColumnRowExample example);

    BoardColumnRow selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") BoardColumnRow row, @Param("example") BoardColumnRowExample example);

    int updateByExample(@Param("row") BoardColumnRow row, @Param("example") BoardColumnRowExample example);

    int updateByPrimaryKeySelective(BoardColumnRow row);

    int updateByPrimaryKey(BoardColumnRow row);
}