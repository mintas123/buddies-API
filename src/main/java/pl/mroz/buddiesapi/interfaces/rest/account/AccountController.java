package pl.mroz.buddiesapi.interfaces.rest.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mroz.buddiesapi.domain.account.AccountDomainService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountDomainService service;

    @Operation(summary = "Get list of all accounts", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List fetched"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AccountDto> getAllAccounts() {
        return service.getAllAccounts()
                .stream()
                .map(AccountDto::from)
                .toList();
    }

    @Operation(summary = "Create an account", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping({"/"})
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto rentalDTO) {
        var created = service.createAccount(AccountDto.toDomain(rentalDTO));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AccountDto.from(created));
    }

}
