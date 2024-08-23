package buddiesapi.interfaces.rest.rental

import buddiesapi.domain.account.AccountDomainService
import buddiesapi.domain.rental.Rental
import buddiesapi.domain.rental.RentalDomainService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import java.util.UUID
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/rentals")
class RentalController(
    private val service: RentalDomainService,
    private val accountDomainService: AccountDomainService
) {

    @get:GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @get:ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "List fetched"
        ), ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )]
    )
    @get:Operation(summary = "Get list of all rentals", method = "GET")
    val allRentals: List<RentalDto>
        get() = service.allRentals
            .stream()
            .map { domain: Rental ->
                RentalDto.from(
                    domain
                )
            }
            .toList()

    @Operation(summary = "Create a rental", method = "POST")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "201",
            description = "Rental created"
        ), ApiResponse(responseCode = "403", description = "Incorrect author data"), ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )]
    )
    @PostMapping("/")
    fun createRental(@RequestBody rentalDTO: RentalDto): ResponseEntity<RentalDto> {
        val author = accountDomainService.getAccount(rentalDTO.authorId)
            ?: return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .build()
        //todo get author data from JWT token not DTO
        val created = service.createRental(RentalDto.toDomain(rentalDTO, author))
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(RentalDto.from(created))
    }

    @Operation(summary = "Update a rental", method = "PUT")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Rental updated"
        ), ApiResponse(responseCode = "403", description = "Rental not updated"), ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )]
    )
    @PostMapping("/{uuid}")
    fun updateRental(
        @PathVariable uuid: UUID,
        @RequestBody rentalDTO: RentalDto
    ): ResponseEntity<RentalDto> {
        val author = accountDomainService.getAccount(rentalDTO.authorId)
            ?: return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .build()
        //todo get author data from JWT token not DTO
        val updated = service.updateRental(uuid, RentalDto.toDomain(rentalDTO, author))
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(RentalDto.from(updated))
    }

    companion object : KLogging()
}
