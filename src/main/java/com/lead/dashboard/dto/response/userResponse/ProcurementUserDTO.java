package com.lead.dashboard.dto.response.userResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcurementUserDTO {
    private Long id ;
    private String name;
    private Long weightValue;

}
