package fr.jeremy.cardsapi.controllers;

import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.repositories.projections.OrderColorCardsInfo;
import fr.jeremy.cardsapi.services.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{deckName}/colors")
    public OrderColorResponse getOrderCards(@PathVariable String deckName) {

        return orderService.findOrderColorByDeckName(deckName);
    }

}
