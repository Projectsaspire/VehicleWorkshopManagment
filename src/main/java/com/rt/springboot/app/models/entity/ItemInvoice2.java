package com.rt.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "items_invoices2")
public class ItemInvoice2 implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private Integer amounts;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "labour_id")
	private Labour labour;

	
	public Double calculateImport2() {
		return amounts.doubleValue() * labour.getPrice();
	}
	/*----- Getters & Setters -----*/
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id; }
	
	public Integer getAmounts() {
		return amounts;
	}
	public void setAmounts(Integer amounts) {
		this.amounts = amounts;
	}
	public Labour getLabour() {
		return labour;
	}
	public void setLabour(Labour labour) {
		this.labour = labour;
	}
	
	
}
