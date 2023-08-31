package com.syn.projectsyn2.bugtracker.controller;

import com.syn.projectsyn2.bugtracker.domain.Permission;
import com.syn.projectsyn2.bugtracker.domain.Role;
import com.syn.projectsyn2.bugtracker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
public class RoleController {

    // Injecting the RoleService using Spring's Autowired annotation
    @Autowired
    private RoleService roleService;

    // Fetch all roles
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    // Fetch a role by ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable int id) {
        Optional<Role> role = roleService.getRoleById(id);
        
        if(role.isPresent()) {
            return ResponseEntity.ok(role.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Fetch a role by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable Role.RoleName name) {
        Optional<Role> role = roleService.getRoleByName(name);
        
        if(role.isPresent()) {
            return ResponseEntity.ok(role.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new role
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        if(roleService.roleExists(role.getRoleName())) {
            return ResponseEntity.badRequest().body(null); // or provide a custom error message
        }
        
        Role savedRole = roleService.saveRole(role);
        return ResponseEntity.ok(savedRole); // or ResponseEntity.created(uri).body(savedRole);
    }

    // Update role details
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable int id, @RequestBody Role updatedRole) {
        // Ideally, you should first check if a role with the provided ID exists
        
        Role savedRole = roleService.saveRole(updatedRole);
        return ResponseEntity.ok(savedRole);
    }

    // Delete a role
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        try {
            roleService.deleteRoleById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) { // This is a general catch. In real scenarios, handle specific exceptions.
            return ResponseEntity.notFound().build();
        }
    }

    // Fetch all permissions associated with a role
    @GetMapping("/{id}/permissions")
    public ResponseEntity<List<Permission>> getRolePermissions(@PathVariable int id) {
        Optional<Role> role = roleService.getRoleById(id);
        
        if(role.isPresent()) {
            return ResponseEntity.ok(role.get().getPermissions());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
