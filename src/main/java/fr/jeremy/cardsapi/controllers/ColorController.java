package fr.jeremy.cardsapi.controllers;

import fr.jeremy.cardsapi.dto.response.ColorListResponse;
import fr.jeremy.cardsapi.services.ColorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colors")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public ColorListResponse findAll() {
        return colorService.findAll();
    }

}
