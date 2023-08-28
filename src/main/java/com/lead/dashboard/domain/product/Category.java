package com.lead.dashboard.domain.product;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String CategoryName;

    private Date createdDate;

//    @OneToMany(mappedBy = "category")
//    private List<Product> products = new ArrayList<>();


}
