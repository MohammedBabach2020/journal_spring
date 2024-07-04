package ma.micda.journal.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.micda.journal.enumeration.ERole;
import ma.micda.journal.models.Role;
import ma.micda.journal.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        // Check if roles are already present
        if (roleRepository.count() == 0) {
            // Insert default roles
            roleRepository.save(new Role(ERole.ROLE_ADMINISTRATOR));
            roleRepository.save(new Role(ERole.ROLE_USER));
        }
    }

}
