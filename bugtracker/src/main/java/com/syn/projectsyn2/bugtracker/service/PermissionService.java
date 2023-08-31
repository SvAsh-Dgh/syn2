package com.syn.projectsyn2.bugtracker.service;

import com.syn.projectsyn2.bugtracker.domain.Permission;
import com.syn.projectsyn2.bugtracker.repository.PermissionRepo;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    private final PermissionRepo permissionRepo;

    // Constructor-based dependency injection
    // @Autowired
    public PermissionService(PermissionRepo permissionRepo) {
        this.permissionRepo = permissionRepo;
    }

    // Service method to retrieve all permissions
    public List<Permission> getAllPermissions() {
        return permissionRepo.findAll();
    }

    // Service method to find a permission by its ID
    public Optional<Permission> getPermissionById(int permissionId) {
        return permissionRepo.findById(permissionId);
    }

    // Service method to find a permission by its name
    public Optional<Permission> getPermissionByName(String permissionName) {
        return permissionRepo.findByPermissionName(permissionName);
    }

    // Service method to check if a permission with a particular name exists
    public boolean permissionExists(String permissionName) {
        return permissionRepo.existsByPermissionName(permissionName);
    }

    // Service method to save or update a permission
    public Permission savePermission(Permission permission) {
        return permissionRepo.save(permission);
    }

    // Service method to delete a permission by its ID
    public void deletePermissionById(int permissionId) {
        permissionRepo.deleteById(permissionId);
    }

    // Getters and setters
    public PermissionRepo getPermissionRepo() {
        return permissionRepo;
    }
}
