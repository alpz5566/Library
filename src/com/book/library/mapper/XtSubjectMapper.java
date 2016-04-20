package com.book.library.mapper;

import com.book.library.po.XtSubject;
import com.book.library.po.XtSubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XtSubjectMapper {
    int countByExample(XtSubjectExample example);

    int deleteByExample(XtSubjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(XtSubject record);

    int insertSelective(XtSubject record);

    List<XtSubject> selectByExample(XtSubjectExample example);

    XtSubject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") XtSubject record, @Param("example") XtSubjectExample example);

    int updateByExample(@Param("record") XtSubject record, @Param("example") XtSubjectExample example);

    int updateByPrimaryKeySelective(XtSubject record);

    int updateByPrimaryKey(XtSubject record);

	List<XtSubject> findSubjectByTeacherId(String teacherid);

	String findSubjectIdByUserId(String userid);

	XtSubject findSubjectByUserId(String studentid);
}