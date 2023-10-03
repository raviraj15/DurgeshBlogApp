package com.lcwd.blog.entity;

import java.util.ArrayList;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@javax.persistence.Entity
@javax.persistence.Table(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class User{

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private int userId;

	@javax.persistence.Column(name = "NAME", nullable = false, length = 100)
	private String name;
	private String email;
	private String password;
	private String about;

	@javax.persistence.OneToMany(mappedBy = "user",
			fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
	private List<Post> posts = new ArrayList<>();

	/*@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "cust_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") )
	Set<Role> roles = new HashSet<Role>();
*/
	
}
