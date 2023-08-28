package com.lead.dashboard.domain.product;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ProductName;

    private Date createdBy;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
