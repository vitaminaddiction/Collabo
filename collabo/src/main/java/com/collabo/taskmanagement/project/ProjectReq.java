package com.collabo.taskmanagement.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectReq {

    private String title;
    private String requirement;
    private LocalDate startLine;
    private LocalDate deadLine;
    private int state;
}
