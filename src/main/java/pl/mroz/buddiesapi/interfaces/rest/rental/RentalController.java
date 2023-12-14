package pl.mroz.buddiesapi.interfaces.rest.rental;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mroz.buddiesapi.domain.account.AccountDomainService;
import pl.mroz.buddiesapi.domain.rental.RentalDomainService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rentals")
public class RentalController {

    private final RentalDomainService service;
    private final AccountDomainService accountDomainService;

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

    @Operation(summary = "Create a rental", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rental created"),
            @ApiResponse(responseCode = "403", description = "Incorrect author data"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping({"/"})
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalDto rentalDTO) {
        var author = accountDomainService.getAccount(rentalDTO.getAuthorId());
        //todo get author data from JWT token not DTO
        if (author == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var created = service.createRental(RentalDto.toDomain(rentalDTO, author));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RentalDto.from(created));
    }

    @Operation(summary = "Update a rental", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated"),
            @ApiResponse(responseCode = "403", description = "Rental not updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping({"/{uuid}"})
    public ResponseEntity<RentalDto> udpateRental(@PathVariable UUID uuid,
                                                  @RequestBody RentalDto rentalDTO) {
        var author = accountDomainService.getAccount(rentalDTO.getAuthorId());
        //todo get author data from JWT token not DTO
        if (author == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var updated = service.updateRental(uuid, RentalDto.toDomain(rentalDTO, author));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RentalDto.from(updated));
    }
}
