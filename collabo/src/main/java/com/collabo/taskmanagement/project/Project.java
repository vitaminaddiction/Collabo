package com.collabo.taskmanagement.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private int idx;
    private int PL_idx;
    private String title;
    private String requirement;
    private LocalDate startLine;
    private LocalDate deadLine;
    private String state;
}
