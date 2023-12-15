package pl.mroz.buddiesapi.interfaces.rest.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mroz.buddiesapi.domain.account.AccountDomainService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private static final String ALREADY_EXISTS_MESSAGE = "Account with id {} already exists!";
    private static final String CANNOT_ACCESS_MESSAGE = "Cannot access account {}!";

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

    @Operation(summary = "Get an account", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account received"),
            @ApiResponse(responseCode = "403", description = "Account not received"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @GetMapping({"/{uuid}"})
    public ResponseEntity<AccountDto> updateAccount(@PathVariable UUID uuid) {
        if (service.getAccount(uuid) == null) {
            //todo return meaningful errors
            log.info(CANNOT_ACCESS_MESSAGE, uuid);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(AccountDto.from(service.getAccount(uuid)));
    }

    @Operation(summary = "Create an account", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created"),
            @ApiResponse(responseCode = "403", description = "Account not created"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PostMapping({"/"})
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        if (service.getAccount(accountDto.getAccountId()) != null) {
            //todo return meaningful errors
            log.info(ALREADY_EXISTS_MESSAGE, accountDto.getAccountId());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        var created = service.createAccount(AccountDto.toDomain(accountDto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AccountDto.from(created));
    }

    @Operation(summary = "Update an account", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account updated"),
            @ApiResponse(responseCode = "403", description = "Account not updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @PutMapping({"/{uuid}"})
    public ResponseEntity<AccountDto> updateAccount(@PathVariable UUID uuid,
                                                    @RequestBody AccountDto accountDto) {
        if (service.getAccount(uuid) == null) {
            //todo return meaningful errors
            log.info(CANNOT_ACCESS_MESSAGE, uuid);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        var updated = service.updateAccount(uuid, AccountDto.toDomain(accountDto));
        return ResponseEntity.status(HttpStatus.OK)
                .body(AccountDto.from(updated));
    }

    @Operation(summary = "Delete an account", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account deleted"),
            @ApiResponse(responseCode = "403", description = "Account not deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
    @DeleteMapping({"/{uuid}"})
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID uuid,
                                              @RequestBody AccountDto accountDto) {
        if (service.getAccount(uuid) == null) {
            //todo return meaningful errors
            log.info(CANNOT_ACCESS_MESSAGE, uuid);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
        service.deleteAccount(AccountDto.toDomain(accountDto));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
