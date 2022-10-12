package za.co.anycompany.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import za.co.anycompany.service.OrderService;

@RestController
@RequiredArgsConstructor
public class WebRestController {
    private final OrderService webService;

}
