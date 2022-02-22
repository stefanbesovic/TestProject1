package com.practice.test1.domen;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "video_categories",
			joinColumns = @JoinColumn(name = "video_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private Set<Category> categories = new HashSet<>();
	
	public void addCategory(Category category) {
		if(!categories.contains(category))
			categories.add(category);
	}
	
	public void removeCategory(Category category) {
		categories.remove(category);
	}
	
}
