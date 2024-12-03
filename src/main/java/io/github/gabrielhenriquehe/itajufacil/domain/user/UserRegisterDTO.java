package io.github.gabrielhenriquehe.itajufacil.domain.user;

import lombok.NonNull;

public record UserRegisterDTO(
        @NonNull String email,
        @NonNull String name,
        @NonNull String username,
        @NonNull String password,
        @NonNull String phone
) {

}
