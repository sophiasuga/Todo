package com.example.todo.infrastructure.persistence;

import com.example.todo.infrastructure.persistence.BoardRow;
import com.example.todo.infrastructure.persistence.BoardRowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoardRowMapper {
    long countByExample(BoardRowExample example);

    int deleteByExample(BoardRowExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BoardRow row);

    int insertSelective(BoardRow row);

    List<BoardRow> selectByExample(BoardRowExample example);

    BoardRow selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") BoardRow row, @Param("example") BoardRowExample example);

    int updateByExample(@Param("row") BoardRow row, @Param("example") BoardRowExample example);

    int updateByPrimaryKeySelective(BoardRow row);

    int updateByPrimaryKey(BoardRow row);
}