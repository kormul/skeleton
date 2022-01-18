package com.kormul.skeleton.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User {
	
    @Id
	@Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

	@Column(name = "username")
    @NotBlank(message = "Username is mandatory")
    private String username;

	@Column(name = "password")
    @NotBlank(message = "Password is mandatory")
    private String password;

	@Column(name = "fullname")
    @NotBlank(message = "FullName is mandatory")
    private String fullname;

	@Column(name = "role")
    @NotBlank(message = "Role is mandatory")
    private String role;
	
}
