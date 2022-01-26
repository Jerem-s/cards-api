package fr.jeremy.cardsapi.controllers;

import fr.jeremy.cardsapi.dto.request.OrderValueRequest;
import fr.jeremy.cardsapi.dto.response.OrderValueResponse;
import fr.jeremy.cardsapi.services.OrderValueService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-values")
public class OrderValueController {

    private final OrderValueService orderValueService;

    public OrderValueController(OrderValueService orderValueService) {
        this.orderValueService = orderValueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderValueResponse create(@RequestBody OrderValueRequest orderValueRequest) {
        return orderValueService.save(orderValueRequest);
    }

    @GetMapping("/last")
    public OrderValueResponse getLast() {
        return this.orderValueService.findLast();
    }
}
