package com.syn.projectsyn2.bugtracker.service;

import com.syn.projectsyn2.bugtracker.domain.Role;
import com.syn.projectsyn2.bugtracker.repository.RoleRepo;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepo roleRepo;

    // Constructor-based dependency injection
    // This is a best practice as it ensures that your classes are always properly initialized
    // @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    // Service method to retrieve all roles
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    // Service method to find a role by its ID
    public Optional<Role> getRoleById(int roleId) {
        return roleRepo.findById(roleId);
    }

    // Service method to find a role by its name
    public Optional<Role> getRoleByName(Role.RoleName roleName) {
        return roleRepo.findByRoleName(roleName);
    }

    // Service method to check if a role with a particular name exists
    public boolean roleExists(Role.RoleName roleName) {
        return roleRepo.existsByRoleName(roleName);
    }

    // Service method to save or update a role
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    // Service method to delete a role by its ID
    public void deleteRoleById(int roleId) {
        roleRepo.deleteById(roleId);
    }

    // Getters and setters (though for services they might not always be needed)
    public RoleRepo getRoleRepo() {
        return roleRepo;
    }
}
