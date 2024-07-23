package com.example.channuoithaiviet.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="orderstatus")
public class Orderstatus {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    private String name;
    
    
    
}
