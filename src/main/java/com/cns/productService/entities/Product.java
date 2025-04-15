package com.cns.productService.entities;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private String brand;
	private int quantity;
	private double price;
	private boolean active;
	@JsonFormat(shape =JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
	private Date relaseDate;
	private String imageName;
	private String imageType;
	@Lob
	private byte[]image;
	
	@CreationTimestamp
	private LocalDateTime dateCreated;
	@CreationTimestamp
	private LocalDateTime dateUpdated;

}
