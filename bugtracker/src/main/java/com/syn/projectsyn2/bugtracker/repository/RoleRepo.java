package com.syn.projectsyn2.bugtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query; to use with custom queries if any
import org.springframework.stereotype.Repository;

import com.syn.projectsyn2.bugtracker.domain.Role;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

    // 1. Find a role by its name
    Optional<Role> findByRoleName(Role.RoleName roleName);


    // 2. Check if a role with a particular name exists
    boolean existsByRoleName(Role.RoleName roleName);

    // Added method to find by String name
    default Optional<Role> findByName(String roleName) {
        return findByRoleName(Role.RoleName.valueOf(roleName));
    }
}