package com.collabo.taskmanagement.TODOList;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TODOListRepository {
    public List<TODOList> list(int cIdx);
    public void update(int M_idx, int state, int idx);
}
