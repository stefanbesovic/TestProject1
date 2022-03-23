package com.practice.test1.entities;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Size(min = 3, max = 20, message = "Name of user should be between 3 and 20 characters.")
	private String name;
	@NotEmpty
	@Size(min = 4, max = 8, message = "Username should be between 4 and 8 characters.")
	private String username;
	@NotEmpty
	@Size(min = 4, max = 12, message = "Password should be between 4 and 12 characters.")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<UserRole> userRoles = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Playlist> playlists = new HashSet<>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id && Objects.equals(name, other.name);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
