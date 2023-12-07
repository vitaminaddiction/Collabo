package com.collabo.taskmanagement.team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    private int M_idx;
    private int P_idx;
    private int state;
    private String comment;
}
