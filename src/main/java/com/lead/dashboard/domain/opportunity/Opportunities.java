package com.lead.dashboard.domain.opportunity;



import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
public class Opportunities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @NotNull
    private String estimateClose;

    private String confidence;

    private String value;

    private String typePayment;
//
//    @OneToOne(targetEntity = Client.class)
//    @JoinColumn(name = "client_id",nullable = false)
//    private Client client;
//
//    @OneToOne(targetEntity = User.class)
//    @JoinColumn(name = "user_id",nullable = false)
//    private  User user;

//    @ManyToOne
//    @JoinColumn(name = "client_id")
//    private Client client;


//    @OneToOne
//    private User user;
//
//    @OneToOne
//    private Client client;


}
