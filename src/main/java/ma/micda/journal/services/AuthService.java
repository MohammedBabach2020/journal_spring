package ma.micda.journal.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ma.micda.journal.enumeration.ERole;
import ma.micda.journal.models.Role;
import ma.micda.journal.models.User;
import ma.micda.journal.payload.request.SignupRequest;
import ma.micda.journal.repository.RoleRepository;
import ma.micda.journal.repository.UserRepositoryNaf;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepositoryNaf userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public AuthService(UserRepositoryNaf userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public User registerUser(SignupRequest signUpRequest) {
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                Role _role;
                switch (role) {
                    case "admin":
                        _role = roleRepository.findByName(ERole.ROLE_ADMINISTRATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        break;
                    case "user":
                        _role = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        break;

                    default:
                        _role = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                }
                roles.add(_role);
            });
        }

        user.setRoles(roles);
        return userRepository.save(user);
    }
}
