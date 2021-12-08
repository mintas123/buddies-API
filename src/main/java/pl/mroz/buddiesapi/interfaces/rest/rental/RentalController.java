package pl.mroz.buddiesapi.interfaces.rest.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mroz.buddiesapi.domain.rental.RentalService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    int doSomething() {
        return service.doSomething();
    }
}
