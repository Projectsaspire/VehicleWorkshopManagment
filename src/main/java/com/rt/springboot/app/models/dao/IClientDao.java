package com.rt.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rt.springboot.app.models.entity.Client;


public interface IClientDao extends JpaRepository<Client, Long>{

	// c = Client
	// i = invoices
	@Query("select c from Client c left join fetch c.invoices i where c.id = ?1")
	public Client fetchByIdWithInvoices(Long id);
	
	@Query(value = "select * from clients j where j.first_name like %:keyword% or j.vno like %:keyword% or j.create_at like %:keyword%", nativeQuery = true)
	 List<Client> findByFirstNameOrVNoOrCreateAt(@Param("keyword") String keyword);
	
	
}
