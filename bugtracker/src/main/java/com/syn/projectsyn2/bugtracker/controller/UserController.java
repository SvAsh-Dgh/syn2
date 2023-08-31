package com.syn.projectsyn2.bugtracker.controller;

import com.syn.projectsyn2.bugtracker.domain.Bug;
import com.syn.projectsyn2.bugtracker.domain.Project;
import com.syn.projectsyn2.bugtracker.domain.User;
import com.syn.projectsyn2.bugtracker.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Fetch all users with roles and projects
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsersWithRolesAndProjects();
    }

    // Fetch a user by username
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all projects assigned to a user by username
    @GetMapping("/{username}/projects")
    public ResponseEntity<List<Project>> getUserProjects(@PathVariable String username) {
        List<Project> projects = userService.getAssignedProjectsByUsername(username);
        if (!projects.isEmpty()) {
            return ResponseEntity.ok(projects);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all bugs reported by a user by username
    @GetMapping("/{username}/bugs")
    public ResponseEntity<List<Bug>> getUserBugs(@PathVariable String username) {
        List<Bug> bugs = userService.getAllBugsByUsername(username);
        if (!bugs.isEmpty()) {
            return ResponseEntity.ok(bugs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/user")
public class UserInfo {

}
}
