package com.greatlearning.tickets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greatlearning.tickets.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

	@Query(value = "select * from ticket t where t.title like %:keyword% or t.description like %:keyword%", nativeQuery = true)
	public List<Ticket> findByKeyword(@Param("keyword") String keyword);

}
