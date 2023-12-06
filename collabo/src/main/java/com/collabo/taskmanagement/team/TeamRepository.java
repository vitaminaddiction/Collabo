package com.collabo.taskmanagement.team;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeamRepository {
    public int teamByProjectIndex(int idx);

    public int insert(Team team);
}
