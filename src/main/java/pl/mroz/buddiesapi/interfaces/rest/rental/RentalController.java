package pl.mroz.buddiesapi.interfaces.rest.rental;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mroz.buddiesapi.domain.rental.RentalService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService service;

    @Operation(summary = "Get list of all rentals", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List fetched"),
        @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RentalDto> getAllRentals() {
        return service.getAllRentals()
                .stream()
                .map(RentalDto::from)
                .toList();
    }
}
