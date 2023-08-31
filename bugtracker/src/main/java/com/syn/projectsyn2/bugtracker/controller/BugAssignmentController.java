package com.syn.projectsyn2.bugtracker.controller;

import com.syn.projectsyn2.bugtracker.domain.Bug;
import com.syn.projectsyn2.bugtracker.domain.BugAssignment;
import com.syn.projectsyn2.bugtracker.domain.User;
import com.syn.projectsyn2.bugtracker.service.BugAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assignments")
public class BugAssignmentController {

    @Autowired
    private BugAssignmentService bugAssignmentService;

    // Fetch all assignments
    @GetMapping
    public List<BugAssignment> getAllAssignments() {
        return bugAssignmentService.getAllAssignments();
    }

    // Fetch a specific assignment by its ID
    @GetMapping("/{id}")
    public ResponseEntity<BugAssignment> getAssignmentById(@PathVariable int id) {
        Optional<BugAssignment> assignment = bugAssignmentService.getAssignmentById(id);
        return assignment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Fetch all assignments for a specific bug
    @GetMapping("/by-bug/{bugId}")
    public ResponseEntity<List<BugAssignment>> getAssignmentsByBug(@PathVariable int bugId) {
        Bug bug = new Bug();  // Assuming you have a default constructor in the Bug entity
        bug.setBugId(bugId);
        List<BugAssignment> assignments = bugAssignmentService.getAssignmentsByBug(bug);
        if (assignments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assignments);
    }

    // Fetch all bugs assigned to a specific user
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<BugAssignment>> getAssignmentsByUser(@PathVariable int userId) {
        User user = new User();  // Assuming you have a default constructor in the User entity
        user.setUserId(userId);
        List<BugAssignment> assignments = bugAssignmentService.getAssignmentsByUser(user);
        if (assignments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assignments);
    }

    // // Fetch assignments made on a specific date
    // @GetMapping("/by-date")
    // public ResponseEntity<List<BugAssignment>> getAssignmentsByDate(@RequestParam Date assignedOn) {
    //     List<BugAssignment> assignments = bugAssignmentService.getAssignmentsByDate(assignedOn);
    //     if (assignments.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     return ResponseEntity.ok(assignments);
    // }

    // Create a new assignment
    @PostMapping
    public ResponseEntity<BugAssignment> createAssignment(@RequestBody BugAssignment bugAssignment) {
        BugAssignment savedAssignment = bugAssignmentService.saveAssignment(bugAssignment);
        return ResponseEntity.ok(savedAssignment);
    }

    // Update an assignment
    @PutMapping("/{id}")
    public ResponseEntity<BugAssignment> updateAssignment(@PathVariable int id, @RequestBody BugAssignment updatedAssignment) {
        if (!bugAssignmentService.getAssignmentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        updatedAssignment.setAssignmentId(id);  // Ensure the correct record is updated
        BugAssignment savedAssignment = bugAssignmentService.saveAssignment(updatedAssignment);
        return ResponseEntity.ok(savedAssignment);
    }

    // Delete an assignment by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable int id) {
        if (!bugAssignmentService.getAssignmentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        bugAssignmentService.deleteAssignmentById(id);
        return ResponseEntity.noContent().build();
    }
}
