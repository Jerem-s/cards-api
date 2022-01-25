package fr.jeremy.cardsapi.controllers;

import fr.jeremy.cardsapi.dto.request.OrderColorRequest;
import fr.jeremy.cardsapi.dto.response.OrderColorResponse;
import fr.jeremy.cardsapi.services.OrderColorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-colors")
public class OrderColorController {

    private final OrderColorService orderColorService;

    public OrderColorController(OrderColorService orderColorService) {
        this.orderColorService = orderColorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderColorResponse create(@RequestBody OrderColorRequest orderColorRequest) {
        return orderColorService.save(orderColorRequest);
    }
}
