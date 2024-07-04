package ma.micda.journal.services;

import org.springframework.stereotype.Service;

import ma.micda.journal.repository.UserRepositoryNaf;

@Service
public class UserService {

    private final UserRepositoryNaf userRepository;

    public UserService(UserRepositoryNaf userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
