package com.jw.practice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
	
	@Id
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String hobby;
	
	@Column
	private Date createDate;
	
}
