package buddiesapi.interfaces.rest.account

import buddiesapi.domain.account.Account
import buddiesapi.domain.account.AccountDomainService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import java.util.UUID
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/account")
class AccountController(
    private val service: AccountDomainService
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "List fetched"
        ), ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )]
    )
    @Operation(summary = "Get list of all accounts", method = "GET")
    fun getAllAccounts(): List<AccountDto> {
        return service.allAccounts
            .stream()
            .map { domain: Account ->
                AccountDto.from(
                    domain
                )
            }
            .toList()
    }

    @Operation(summary = "Get an account", method = "GET")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Account received"
        ), ApiResponse(responseCode = "403", description = "Account not received"), ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )]
    )
    @GetMapping("/{uuid}")
    fun updateAccount(@PathVariable uuid: UUID): ResponseEntity<AccountDto?> {
        //todo auth
        val account = service.getAccount(uuid)
        if (account == null) {
            logger().info { "Cannot access account!" }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null)
        }
        return ResponseEntity.status(HttpStatus.OK)
            .body(
                AccountDto.from(account)
            )
    }

    @Operation(summary = "Create an account", method = "POST")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "201",
            description = "Account created"
        ), ApiResponse(responseCode = "403", description = "Account not created"), ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )]
    )
    @PostMapping("/")
    fun createAccount(@RequestBody accountDto: AccountDto): ResponseEntity<AccountDto?> {
        if (service.getAccount(accountDto.accountId) != null) {
            //todo return meaningful errors
            logger().info { "Account with id ${accountDto.accountId} already exists!" }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null)
        }
        val created = service.createAccount(AccountDto.toDomain(accountDto))
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(AccountDto.from(created))
    }

    @Operation(summary = "Update an account", method = "PUT")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Account updated"
        ), ApiResponse(responseCode = "403", description = "Account not updated"), ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )]
    )
    @PutMapping("/{uuid}")
    fun updateAccount(
        @PathVariable uuid: UUID,
        @RequestBody accountDto: AccountDto
    ): ResponseEntity<AccountDto?> {
        //todo auth
        val account = service.getAccount(uuid)
        if (account == null) {
            logger().info { "Cannot access account!" }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null)
        }
        val updated = service.updateAccount(uuid, AccountDto.toDomain(accountDto))
        return ResponseEntity.status(HttpStatus.OK)
            .body(AccountDto.from(updated))
    }

    @Operation(summary = "Delete an account", method = "DELETE")
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "Account deleted"
        ), ApiResponse(responseCode = "403", description = "Account not deleted"), ApiResponse(
            responseCode = "500",
            description = "Internal server error"
        )]
    )
    @DeleteMapping("/{uuid}")
    fun deleteAccount(
        @PathVariable uuid: UUID,
        @RequestBody accountDto: AccountDto
    ): ResponseEntity<Void?> {
        //todo auth
        val account = service.getAccount(uuid)
        if (account == null) {
            logger().info { "Cannot access account!" }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null)
        }
        service.deleteAccount(AccountDto.toDomain(accountDto))
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    companion object : KLogging()

}
