package com.syn.projectsyn2.bugtracker.controller;

import com.syn.projectsyn2.bugtracker.domain.Permission;
import com.syn.projectsyn2.bugtracker.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    // Fetch all permissions
    @GetMapping
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    // Fetch a permission by ID
    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable int id) {
        Optional<Permission> permission = permissionService.getPermissionById(id);
        
        // Use the Optional with lambdas for concise handling
        return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Fetch a permission by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Permission> getPermissionByName(@PathVariable String name) {
        Optional<Permission> permission = permissionService.getPermissionByName(name);
        
        return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new permission
    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        // Basic check: If permission with the same name exists, don't create a new one
        if (permissionService.permissionExists(permission.getPermissionName())) {
            return ResponseEntity.status(409).body(null);  // 409 Conflict
        }

        Permission savedPermission = permissionService.savePermission(permission);
        return ResponseEntity.ok(savedPermission);
    }

    // Update permission details
    @PutMapping("/{id}")
    public ResponseEntity<Permission> updatePermission(@PathVariable int id, @RequestBody Permission updatedPermission) {
        if(!permissionService.getPermissionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Ensures the correct permission is updated
        updatedPermission.setPermissionId(id);
        Permission savedPermission = permissionService.savePermission(updatedPermission);
        return ResponseEntity.ok(savedPermission);
    }

    // Delete a permission by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable int id) {
        if(!permissionService.getPermissionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        permissionService.deletePermissionById(id);
        return ResponseEntity.noContent().build();
    }
}
