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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.apache.tomcat.util.codec.binary.Base64;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "clients")
public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column(name = "firstName")
	private String firstName;

	
	@Column(name = "lastName")
	private String lastName;

	
	@Email
	@Column(name = "email")
	private String email;
	
	@Column(name = "vno")
	private String vNo;

	@Column(name = "vmake")
	private String vMake;

	@Column(name = "phone")
	private String phone;

	@Column(name = "vinventory")
	private String vInventory;

	@Column(name = "sitem")
	private String sItem;

	@Column(name = "cuscomptask")
	private String cusCompTask;
	
	

	@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	
	
	
	@Column(name = "frontImg", columnDefinition = "BLOB")
	private byte[] frontImg;

	public String generatebase64Image() {
		return Base64.encodeBase64String(this.frontImg);
	}

	@Column(name = "rhSide", columnDefinition = "BLOB")
	private byte[] rhSide;

	public String generatebase64Image1() {
		return Base64.encodeBase64String(this.rhSide);
	}

	@Column(name = "lhSide", columnDefinition = "BLOB")
	private byte[] lhSide;

	public String generatebase64Image2() {
		return Base64.encodeBase64String(this.lhSide);
	}

	@Column(name = "rearImg", columnDefinition = "BLOB")
	private byte[] rearImg;

	public String generatebase64Image3() {
		return Base64.encodeBase64String(this.rearImg);
	}

	@Column(name = "dashImg", columnDefinition = "BLOB")
	private byte[] dashImg;

	public String generatebase64Image4() {
		return Base64.encodeBase64String(this.dashImg);
	}

	@Column(name = "dickyImg", columnDefinition = "BLOB")
	private byte[] dickyImg;

	public String generatebase64Image5() {
		return Base64.encodeBase64String(this.dickyImg);
	}
	
	

	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Invoice> invoices;

	public Client() {
		invoices = new ArrayList<Invoice>();
	}

	//private String photo;

	

	/*----- toString -----*/
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
	/*----- Getters & Setters -----*/
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id; }
	
	public String getFirstName() { return firstName; }
	
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	public String getLastName() { return lastName; }
	
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public String getEmail() { return email; }
	
	public void setEmail(String email) { this.email = email; }
	
	public Date getCreateAt() { return createAt; }
	
	public void setCreateAt(Date createAt) { this.createAt = createAt; }
	
	/*
	 * public String getPhoto() { return photo; }
	 * 
	 * public void setPhoto(String photo) { this.photo = photo; }
	 */
	
	public List<Invoice> getInvoices() { return invoices; }
	
	public void setInvoices(List<Invoice> invoices) { this.invoices = invoices; }
	
	public void addInvoice(Invoice invoice) { invoices.add(invoice); }


	
	public byte[] getFrontImg() {
		return frontImg;
	}

	public void setFrontImg(byte[] frontImg) {
		this.frontImg = frontImg;
	}

	public byte[] getRhSide() {
		return rhSide;
	}

	public void setRhSide(byte[] rhSide) {
		this.rhSide = rhSide;
	}

	public byte[] getLhSide() {
		return lhSide;
	}

	public void setLhSide(byte[] lhSide) {
		this.lhSide = lhSide;
	}

	public byte[] getRearImg() {
		return rearImg;
	}

	public void setRearImg(byte[] rearImg) {
		this.rearImg = rearImg;
	}

	public byte[] getDashImg() {
		return dashImg;
	}

	public void setDashImg(byte[] dashImg) {
		this.dashImg = dashImg;
	}

	public byte[] getDickyImg() {
		return dickyImg;
	}

	public void setDickyImg(byte[] dickyImg) {
		this.dickyImg = dickyImg;
	}



	public String getvNo() {
		return vNo;
	}

	public void setvNo(String vNo) {
		this.vNo = vNo;
	}

	public String getvMake() {
		return vMake;
	}

	public void setvMake(String vMake) {
		this.vMake = vMake;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getvInventory() {
		return vInventory;
	}

	public void setvInventory(String vInventory) {
		this.vInventory = vInventory;
	}

	public String getsItem() {
		return sItem;
	}

	public void setsItem(String sItem) {
		this.sItem = sItem;
	}

	public String getCusCompTask() {
		return cusCompTask;
	}

	public void setCusCompTask(String cusCompTask) {
		this.cusCompTask = cusCompTask;
	}

	public Client(Long id, @NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty @Email String email,
			@NotNull Date createAt, byte[] frontImg, byte[] rhSide, byte[] lhSide, byte[] rearImg, byte[] dashImg,
			byte[] dickyImg, List<Invoice> invoices) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.createAt = createAt;
		this.frontImg = frontImg;
		this.rhSide = rhSide;
		this.lhSide = lhSide;
		this.rearImg = rearImg;
		this.dashImg = dashImg;
		this.dickyImg = dickyImg;
		this.invoices = invoices;
	}

	public Client(Long id, @NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty @Email String email,
			@NotNull Date createAt, byte[] frontImg, byte[] rhSide, byte[] lhSide, byte[] rearImg, byte[] dashImg,
			byte[] dickyImg) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.createAt = createAt;
		this.frontImg = frontImg;
		this.rhSide = rhSide;
		this.lhSide = lhSide;
		this.rearImg = rearImg;
		this.dashImg = dashImg;
		this.dickyImg = dickyImg;
	}

	public Client(Long id, @NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty @Email String email,
			String vNo, String vMake, String phone, String vInventory, String sItem, String cusCompTask,
			@NotNull Date createAt, byte[] frontImg, byte[] rhSide, byte[] lhSide, byte[] rearImg, byte[] dashImg,
			byte[] dickyImg) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.vNo = vNo;
		this.vMake = vMake;
		this.phone = phone;
		this.vInventory = vInventory;
		this.sItem = sItem;
		this.cusCompTask = cusCompTask;
		this.createAt = createAt;
		this.frontImg = frontImg;
		this.rhSide = rhSide;
		this.lhSide = lhSide;
		this.rearImg = rearImg;
		this.dashImg = dashImg;
		this.dickyImg = dickyImg;
	}
	
	

}
