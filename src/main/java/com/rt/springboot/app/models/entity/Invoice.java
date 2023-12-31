package com.rt.springboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String description;
	
	private String observation;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Client client;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "invoice_id")
	private List<ItemInvoice> items;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "invoice_id1")
	private List<ItemInvoice2> items2;
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	public Invoice() {
		this.items = new ArrayList<ItemInvoice>();
		this.items2 = new ArrayList<ItemInvoice2>();
	}
	
	public Double getTotal() {
		Double total = 0.0;	
		
		int size = items.size();
		
		for(int i = 0; i < size; i++) {
			total += items.get(i).calculateImport();
			
		}
		double total1 =getTotal1();
		return total+total1;
	}
	public Double getTotal1() {
			
		Double total1 =0.0;
		
		int size = items2.size();
		
		for(int i = 0; i < size; i++) {
			total1 += items2.get(i).calculateImport2();
			
		}
		
		return total1;
	}
	/*----- Getters  & Setters -----*/
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id; }
	
	public String getDescription() { return description; }
	
	public void setDescription(String description) { this.description = description; }
	
	public String getObservation() { return observation; }
	
	public void setObservation(String observation) { this.observation = observation; }
	
	public Date getCreateAt() { return createAt; }
	
	public void setCreateAt(Date createAt) { this.createAt = createAt; }
	
	@XmlTransient
	public Client getClient() { return client; }
	
	public void setClient(Client client) { this.client = client; }
	
	public List<ItemInvoice> getItems() { return items; }
	
	public void setItems(List<ItemInvoice> items) { this.items = items; }
	
	public void addItemInvoice(ItemInvoice item) { this.items.add(item); }

	public List<ItemInvoice2> getItems2() {
		return items2;
	}

	public void setItems2(List<ItemInvoice2> items2) {
		this.items2 = items2;
	}
	public void addItemInvoice2(ItemInvoice2 item) { this.items2.add(item); }

}
