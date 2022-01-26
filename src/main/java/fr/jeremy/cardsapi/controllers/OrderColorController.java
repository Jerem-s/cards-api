package fr.jeremy.cardsapi.controllers;

import fr.jeremy.cardsapi.dto.request.OrderColorRequest;
import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-colors")
public class OrderColorController {

    private final OrderService orderService;

    public OrderColorController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderColorResponse create(@RequestBody OrderColorRequest orderColorRequest) {
        return orderService.saveColors(orderColorRequest);
    }

    @GetMapping()
    public OrderColorResponse getLast() {
        return this.orderService.getOrderColors();
    }
}
