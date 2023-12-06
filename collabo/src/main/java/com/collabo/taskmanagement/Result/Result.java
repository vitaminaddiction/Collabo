package com.collabo.taskmanagement.Result;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {
    private int TL_idx;
    private String title;
    private String content;
    private String filename;
}