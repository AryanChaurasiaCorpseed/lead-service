package com.lead.dashboard.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bitrix_bad")
@Data
@NoArgsConstructor
public class BitrixBad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Stage")
    private String stage;

    @Column(name = "Responsible")
    private String responsible;

//    @Column(name = "Deal_Name")
//    private String dealName;

    @Column(name = "Deal Name")
    private String dealName;

    @Column(name = "Contact")
    private String contact;

    @Column(name = "Comment")
    private String comment;

//    @Column(name = "Clinet_Mobile")
//    private String clientMobile;

    @Column(name = "Clinet Mobile")
    private String clientMobile;

    @Column(name = "client_email")
    private String clientEmail;




}
