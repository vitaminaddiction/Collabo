package com.collabo.taskmanagement.Result;

import com.collabo.taskmanagement.TODOList.TODOList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResultRepository {
    public void insert(Result result);
}
