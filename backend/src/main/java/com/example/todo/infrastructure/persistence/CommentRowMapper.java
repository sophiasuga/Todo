package com.example.todo.infrastructure.persistence;

import com.example.todo.infrastructure.persistence.CommentRow;
import com.example.todo.infrastructure.persistence.CommentRowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentRowMapper {
    long countByExample(CommentRowExample example);

    int deleteByExample(CommentRowExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommentRow row);

    int insertSelective(CommentRow row);

    List<CommentRow> selectByExample(CommentRowExample example);

    CommentRow selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CommentRow row, @Param("example") CommentRowExample example);

    int updateByExample(@Param("row") CommentRow row, @Param("example") CommentRowExample example);

    int updateByPrimaryKeySelective(CommentRow row);

    int updateByPrimaryKey(CommentRow row);
}