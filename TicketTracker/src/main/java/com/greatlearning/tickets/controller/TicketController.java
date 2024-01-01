package com.greatlearning.tickets.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatlearning.tickets.model.Ticket;
import com.greatlearning.tickets.repository.TicketRepository;
import com.greatlearning.tickets.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired

	TicketService ticketService;
	TicketRepository ticketRepository;

	@GetMapping("/list")
	public String findAll(Model model, String keyword) {

		if (keyword != null) {
			model.addAttribute("tickets", ticketService.findByKeyword(keyword));
		} else {
			List<Ticket> ticket = ticketService.findAll();
			model.addAttribute("tickets", ticket);
		}

		return "track/ticket-list";
	}

	@GetMapping("/addNewTickets")
	public String addNewTickets(Model model) {
		Ticket theTicket = new Ticket();
		model.addAttribute("tickets", theTicket);
		return "track/ticket-addform";
	}

	@PostMapping("/saveTicket")
	public String saveTicket(Model model, @ModelAttribute("tickets") Ticket ticket) {
		ticket.setDate(LocalDate.now());
		ticketService.saveTicket(ticket);
		return "redirect:/tickets/list";
	}

	@GetMapping("/formForEdit/{id}")
	public String formForEdit(Model model, @PathVariable int id) {
		Ticket theTicket = ticketService.findById(id);
		model.addAttribute("tickets", theTicket);
		return "track/ticket-editform";
	}

	@GetMapping("/deleteTicket/{id}")
	public String delete(Model model, @PathVariable int id) {
		Ticket theTicket = new Ticket();
		model.addAttribute("tickets", theTicket);
		ticketService.deleteById(id);
		return "redirect:/tickets/list";

	}

	@GetMapping("/viewTicket/{id}")
	public String viewTicket(Model model, @PathVariable int id) {
		Ticket theTicket = ticketService.findById(id);
		model.addAttribute("tickets", theTicket);
		return "track/ticket-viewform";
	}

	public List<Ticket> findByKeyword(String keyword) {
		return ticketRepository.findByKeyword(keyword);
	}

}
