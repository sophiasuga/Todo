package com.example.todo.infrastructure.persistence;

import com.example.todo.infrastructure.persistence.BoardMemberRow;
import com.example.todo.infrastructure.persistence.BoardMemberRowExample;
import com.example.todo.infrastructure.persistence.BoardMemberRowKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BoardMemberRowMapper {
    long countByExample(BoardMemberRowExample example);

    int deleteByExample(BoardMemberRowExample example);

    int deleteByPrimaryKey(BoardMemberRowKey key);

    int insert(BoardMemberRow row);

    int insertSelective(BoardMemberRow row);

    List<BoardMemberRow> selectByExample(BoardMemberRowExample example);

    BoardMemberRow selectByPrimaryKey(BoardMemberRowKey key);

    int updateByExampleSelective(@Param("row") BoardMemberRow row, @Param("example") BoardMemberRowExample example);

    int updateByExample(@Param("row") BoardMemberRow row, @Param("example") BoardMemberRowExample example);

    int updateByPrimaryKeySelective(BoardMemberRow row);

    int updateByPrimaryKey(BoardMemberRow row);
}