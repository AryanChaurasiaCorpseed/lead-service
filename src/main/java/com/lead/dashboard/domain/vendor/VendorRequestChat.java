package com.lead.dashboard.domain.vendor;//package com.lead.dashboard.domain.vendor;

import com.lead.dashboard.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorRequestChat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Vendor vendor;

    private User messagedTo;

    private User messagedBy;



    private LocalDate date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;




}
