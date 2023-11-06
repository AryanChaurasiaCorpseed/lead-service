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
    
    private String description;
//
//    @OneToOne(targetEntity = Client.class)
//    @JoinColumn(name = "client_id",nullable = false)
//    private Client client;
//
//    @OneToOne(targetEntity = User.class)
//    @JoinColumn(name = "user_id",nullable = false)
//    private  User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstimateClose() {
		return estimateClose;
	}

	public void setEstimateClose(String estimateClose) {
		this.estimateClose = estimateClose;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTypePayment() {
		return typePayment;
	}

	public void setTypePayment(String typePayment) {
		this.typePayment = typePayment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//    @ManyToOne
//    @JoinColumn(name = "client_id")
//    private Client client;


//    @OneToOne
//    private User user;
//
//    @OneToOne
//    private Client client;
	

    
    

}
