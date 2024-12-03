package io.github.gabrielhenriquehe.itajufacil.services;

import io.github.gabrielhenriquehe.itajufacil.domain.user.User;
import io.github.gabrielhenriquehe.itajufacil.domain.user.UserRegisterDTO;
import io.github.gabrielhenriquehe.itajufacil.exceptions.InfoAlreadyInUseException;
import io.github.gabrielhenriquehe.itajufacil.exceptions.InvalidDataProvidedException;
import io.github.gabrielhenriquehe.itajufacil.repositories.UserRepository;
import io.github.gabrielhenriquehe.itajufacil.utils.AuthDataValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User register(UserRegisterDTO data) {

        validateUserData(data);

        if (this.userRepository.findByEmail(data.email().toLowerCase()).isPresent())
            throw new InfoAlreadyInUseException("Email já utilizado por outro usuário.");

        if (this.userRepository.findByUsername(data.username().toLowerCase()).isPresent())
            throw new InfoAlreadyInUseException("Nome de usuário já utilizado por outro usuário.");

        if (this.userRepository.findByPhone(data.phone()).isPresent())
            throw new InfoAlreadyInUseException("Número de telefone já utilizado por outro usuário.");

        User user = new User(data.email().toLowerCase(), data.name().toLowerCase(), data.username().toLowerCase(), data.password(), data.phone());

        return this.userRepository.save(user);
    }


    private void validateUserData(UserRegisterDTO data) {
        if (!AuthDataValidator.isValidEmail(data.email())) throw new InvalidDataProvidedException("Email inválido.");
        if (!AuthDataValidator.isValidName(data.name())) throw new InvalidDataProvidedException("Nome inválido.");
        if (!AuthDataValidator.isValidUsername(data.username())) throw new InvalidDataProvidedException("Nome de usuário inválido.");
        if (!AuthDataValidator.isValidPassword(data.password())) throw new InvalidDataProvidedException("Senha inválida.");
        if (!AuthDataValidator.isValidPhoneNumber(data.phone())) throw new InvalidDataProvidedException("Número de telefone inválido.");
    }
}

