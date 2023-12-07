package com.collabo.taskmanagement.project;

import com.collabo.taskmanagement.auth.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectRepository {
    public List<Project> list(int pageNum, int idx);

    public List<Project> myProject(Map<String, Object> map);

    public Project selectOne(int idx);

    public int countRow(Member member);

    public int countRowPublic();

    @Options(useGeneratedKeys = true, keyProperty = "idx")
    public void insert(Project project);
}
