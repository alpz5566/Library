package com.book.library.mapper;

import com.book.library.po.XtTeacher;
import com.book.library.po.XtTeacherExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XtTeacherMapper {
    int countByExample(XtTeacherExample example);

    int deleteByExample(XtTeacherExample example);

    int deleteByPrimaryKey(String id);

    int insert(XtTeacher record);

    int insertSelective(XtTeacher record);

    List<XtTeacher> selectByExample(XtTeacherExample example);

    XtTeacher selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") XtTeacher record, @Param("example") XtTeacherExample example);

    int updateByExample(@Param("record") XtTeacher record, @Param("example") XtTeacherExample example);

    int updateByPrimaryKeySelective(XtTeacher record);

    int updateByPrimaryKey(XtTeacher record);
}