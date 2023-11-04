package pl.mroz.buddiesapi.interfaces.rest.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import pl.mroz.buddiesapi.domain.account.Account;
import pl.mroz.buddiesapi.domain.common.Location;

import java.util.UUID;

@Getter
@Builder
public class AccountDto {

    @Schema(example = "ff291c13-bd30-4950-a887-f0ab54c9eaf8", name = "Account identification")
    private UUID accountId;

    @Schema(example = "jan.kowalski@gmail.com", name = "Account email address")
    private String email;

    @Schema(example = "ad2dc90ed2fceee06f7c8989b33e5509fd76334c639c53ee588529f1517858a2", name = "Hash of password")
    private String hashedPassword;

    @Schema(example = "Jan", name = "First name")
    private String name;

    @Schema(example = "Kowalski", name = "Last name")
    private String lastName;

    @Schema(example = "Warsaw, Poland", name = "Location readable text")
    private String locationStr;

    @Schema(example = "21.017532", name = "Longitude")
    private double locationLng;

    @Schema(example = "52.237049", name = "Latitude")
    private double locationLat;

    static AccountDto from(Account domain) {
        return AccountDto.builder()
                .accountId(domain.getAccountId())
                .email(domain.getEmail())
                .name(domain.getName())
                .lastName(domain.getLastName())
                .locationStr(domain.getLocation().getReadableText())
                .locationLng(domain.getLocation().getLongitude())
                .locationLat(domain.getLocation().getLatitude())
                .build();
    }

    static Account toDomain(AccountDto dto) {
        var location = Location.builder()
                .readableText(dto.getLocationStr())
                .longitude(dto.getLocationLng())
                .latitude(dto.getLocationLat())
                .build();
        return Account.builder()
                .accountId(dto.getAccountId())
                .email(dto.getEmail())
                .hashedPassword(dto.getHashedPassword())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .location(location)
                .build();
    }


}
