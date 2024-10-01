package com.lead.dashboard.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Table
@Entity
public class Ratings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id;
	
	String rating;
    
	@ManyToOne
	UrlsManagment urlsManagment;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="ratings_user",joinColumns = {@JoinColumn(name="rating_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="ratings_user_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<User>ratingsUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public UrlsManagment getUrlsManagment() {
		return urlsManagment;
	}

	public void setUrlsManagment(UrlsManagment urlsManagment) {
		this.urlsManagment = urlsManagment;
	}

	public List<User> getRatingsUser() {
		return ratingsUser;
	}

	public void setRatingsUser(List<User> ratingsUser) {
		this.ratingsUser = ratingsUser;
	}
	
	
	
	
}
