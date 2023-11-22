package com.collabo.taskmanagement.TODOList;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TODOListReq {

    @Nullable
    private int idx;

    private int P_idx;
    private String title;
    private String content;
    private LocalDate deadline;
    private String C_idx;
    private int state;
    private int M_idx;
}
