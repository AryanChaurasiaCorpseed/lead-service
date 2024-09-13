package com.lead.dashboard.dto.remarkDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRemarkRequest {

    private Long remarkId;
    private Long userId;
    private String message;
    private List<String> file;
    private String type;
    private Long leadId;
}
