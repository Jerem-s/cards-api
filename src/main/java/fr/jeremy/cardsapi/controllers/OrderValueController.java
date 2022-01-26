package fr.jeremy.cardsapi.controllers;

import fr.jeremy.cardsapi.dto.request.OrderValueRequest;
import fr.jeremy.cardsapi.dto.response.OrderValueResponse;
import fr.jeremy.cardsapi.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-values")
public class OrderValueController {

    private final OrderService orderService;

    public OrderValueController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderValueResponse create(@RequestBody OrderValueRequest orderValueRequest) {
        return orderService.saveValues(orderValueRequest);
    }

    @GetMapping
    public OrderValueResponse get() {
        return this.orderService.getOrderValues();
    }
}
