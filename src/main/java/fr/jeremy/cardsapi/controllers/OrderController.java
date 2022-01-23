package fr.jeremy.cardsapi.controllers;

import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{deckName}/colors")
    public OrderColorResponse getOrderColorsCards(@PathVariable String deckName) {
        return orderService.findOrderColorByDeckName(deckName);
    }

}
