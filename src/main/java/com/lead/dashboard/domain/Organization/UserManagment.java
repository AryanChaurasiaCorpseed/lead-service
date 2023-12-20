package com.lead.dashboard.domain.Organization;

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

import java.util.List;

import com.lead.dashboard.domain.Role;
import com.lead.dashboard.domain.User;

@Table
@Entity
@Data
public class UserManagment {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
//    private Role Role;
    @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_managment_role",joinColumns = {@JoinColumn(name="user_managment_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="user_managment_role_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
    private List<Role>userManagmentRole;
    
    @ManyToOne
    User user;
    
    boolean isDeleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Role> getUserManagmentRole() {
		return userManagmentRole;
	}

	public void setUserManagmentRole(List<Role> userManagmentRole) {
		this.userManagmentRole = userManagmentRole;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	

	
    

}
