package com.collabo.taskmanagement.project;

import com.collabo.taskmanagement.auth.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetail {
    private Project project;
    private Member projectLeader;
}
