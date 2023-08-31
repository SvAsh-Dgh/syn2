package com.syn.projectsyn2.bugtracker.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permissionId")
    private int permissionId;

    @Column(name = "permissionName", nullable = false, unique = true)
    private String permissionName;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;

    // Default constructor for JPA
    public Permission() {}

    // Constructor to initialize permissionName
    public Permission(String permissionName) {
        this.permissionName = permissionName;
    }

    // Getters and Setters
    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}