package com.greatlearning.tickets.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.greatlearning.tickets.model.Ticket;
import com.greatlearning.tickets.repository.TicketRepository;

@Service
public class TicketServiceFuntions implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Override
	public List<Ticket> findAll() {
		// TODO Auto-generated method stub
		return ticketRepository.findAll();
	}

	@Override
	public Ticket findById(int theId) {
		Optional<Ticket> result = ticketRepository.findById(theId);
		Ticket ticket = null;
		if (result.isPresent()) {
			ticket = result.get();
		} else {
			throw new RuntimeException("Ticket is not present with given Id : " + theId);
		}
		return ticket;
	}

	@Override
	public void saveTicket(Ticket theTicket) {
		ticketRepository.save(theTicket);

	}

	@Override
	public void deleteById(int theId) {
		ticketRepository.deleteById(theId);

	}

	public List<Ticket> findByKeyword(String keyword) {
		return ticketRepository.findByKeyword(keyword);
	}

}
