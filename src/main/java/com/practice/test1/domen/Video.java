package com.practice.test1.domen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<VideoOrder> videoOrders = new ArrayList<>();
	
	public void addCategory(Category category) {
		if(!categories.contains(category))
			categories.add(category);
	}
	
	public void removeCategory(Category category) {
		categories.remove(category);
	}
}
