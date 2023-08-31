package com.syn.projectsyn2.bugtracker.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private int roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "roleName", nullable = false)
    private RoleName roleName; 

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "rolepermission",
        joinColumns = @JoinColumn(name = "roleId"),
        inverseJoinColumns = @JoinColumn(name = "permissionId")
    )
    private List<Permission> permissions;

    // Default constructor for JPA
    public Role() {}

    // Constructor to initialize roleName
    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    // Getters and Setters
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }


    public enum RoleName {
        ADMIN, DEVELOPER, TESTER
    }
}