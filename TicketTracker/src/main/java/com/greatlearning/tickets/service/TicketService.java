package com.greatlearning.tickets.service;

import java.util.List;

import com.greatlearning.tickets.model.Ticket;

public interface TicketService {
	public List<Ticket> findAll();

	public Ticket findById(int theId);

	public void saveTicket(Ticket theTicket);

	public void deleteById(int theId);

	public List<Ticket> findByKeyword(String keyword);

}
