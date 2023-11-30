package com.collabo.taskmanagement.project;

import com.collabo.taskmanagement.auth.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectRepository {
    public List<Project> list();

    public List<Project> myProject(Member member);

    public Project selectOne(int idx);
}
