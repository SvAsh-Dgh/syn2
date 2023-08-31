package com.syn.projectsyn2.bugtracker.domain;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private byte[] salt;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @OneToMany(mappedBy = "assignedTo")
    private List<Project> assignedProjects;

    @OneToMany(mappedBy = "reportedBy")
    private List<Bug> reportedBugs;

    @OneToMany(mappedBy = "user")
    private List<BugAssignment> bugAssignments;
    
    private static final SecureRandom rand = new SecureRandom();

    // Set a new password: hash and store the password
    public void setPassword(String password) {
        byte[] newSalt = new byte[16];
        rand.nextBytes(newSalt);
        this.salt = newSalt;
        this.password = hashPassword(password, newSalt);
    }

    // Check if an input password is the same as the stored one
    public boolean checkPassword(String inputPassword) {
        String hashedInput = hashPassword(inputPassword, this.salt);
        return this.password.equals(hashedInput);
    }

    private String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Default constructor
    public User() {}

    // Parameterized constructor. 7 parameters is max for best practice.
    public User(int userId, String username, String email, byte[] salt, String password, Role role, List<Project> assignedProjects, List<Bug> reportedBugs, List<BugAssignment> bugAssignments) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.salt = salt;
        this.password = password;
        this.role = role;
        this.assignedProjects = assignedProjects;
        this.reportedBugs = reportedBugs;
        this.bugAssignments = bugAssignments;
    }

    // Getters and setters for all fields

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public void setAssignedProjects(List<Project> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }

    public List<Bug> getReportedBugs() {
        return reportedBugs;
    }

    public void setReportedBugs(List<Bug> reportedBugs) {
        this.reportedBugs = reportedBugs;
    }

    public List<BugAssignment> getBugAssignments() {
        return bugAssignments;
    }

    public void setBugAssignments(List<BugAssignment> bugAssignments) {
        this.bugAssignments = bugAssignments;
    }
}