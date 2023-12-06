package com.collabo.taskmanagement.Result;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultReq {

    @Nullable
    private int TL_idx;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String filename;
}
