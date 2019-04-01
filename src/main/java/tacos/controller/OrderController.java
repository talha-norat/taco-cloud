package tacos.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tacos.dao.OrderRepository;
import tacos.model.Order;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController
{

	private final OrderRepository orderRepo;
	
	@GetMapping("/current")
	public String getCurrentOrderForm(Model model)
	{
		model.addAttribute("order", new Order());
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus)
	{
		if (errors.hasErrors()) return "orderForm";
		log.info("Processing order: {} ", order.toString());
		orderRepo.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}

}
