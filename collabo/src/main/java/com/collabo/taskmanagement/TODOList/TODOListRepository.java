package com.collabo.taskmanagement.TODOList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TODOListRepository {
    public List<TODOList> list(@Param("pIdx") int pIdx, @Param("cIdx") int cIdx);
    public List<TODOList> mylist(int idx);
    public void update(int M_idx, int state, int idx);
}
