package com.book.library.mapper;

import com.book.library.po.XtStudent;
import com.book.library.po.XtStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XtStudentMapper {
    int countByExample(XtStudentExample example);

    int deleteByExample(XtStudentExample example);

    int deleteByPrimaryKey(String id);

    int insert(XtStudent record);

    int insertSelective(XtStudent record);

    List<XtStudent> selectByExample(XtStudentExample example);

    XtStudent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") XtStudent record, @Param("example") XtStudentExample example);

    int updateByExample(@Param("record") XtStudent record, @Param("example") XtStudentExample example);

    int updateByPrimaryKeySelective(XtStudent record);

    int updateByPrimaryKey(XtStudent record);
}