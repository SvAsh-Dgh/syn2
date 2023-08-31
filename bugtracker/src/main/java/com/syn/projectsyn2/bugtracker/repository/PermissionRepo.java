package com.syn.projectsyn2.bugtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.syn.projectsyn2.bugtracker.domain.Permission;
import java.util.Optional;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Integer> {

    // 1. Find a permission by its name
    Optional<Permission> findByPermissionName(String permissionName);

    // 2. Check if a permission with a particular name exists
    boolean existsByPermissionName(String permissionName);
    }