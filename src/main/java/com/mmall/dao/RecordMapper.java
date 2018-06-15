package com.mmall.dao;

import com.mmall.model.Record;

import java.util.List;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    List<Record> selectAllRecord();

    List<Record> selectAllRecordWhileActionNotNull();

    Record selectByPic(String pic);

    List<Record> selectByAction();
}