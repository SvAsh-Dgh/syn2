package com.syn.projectsyn2.bugtracker.service;

import com.syn.projectsyn2.bugtracker.domain.Bug;
import com.syn.projectsyn2.bugtracker.domain.BugAssignment;
import com.syn.projectsyn2.bugtracker.domain.User;
import com.syn.projectsyn2.bugtracker.repository.BugAssignmentRepo;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BugAssignmentService {

    private final BugAssignmentRepo bugAssignmentRepo;

    // Constructor-based dependency injection
    // @Autowired
    public BugAssignmentService(BugAssignmentRepo bugAssignmentRepo) {
        this.bugAssignmentRepo = bugAssignmentRepo;
    }

    // Service method to retrieve all assignments
    public List<BugAssignment> getAllAssignments() {
        return bugAssignmentRepo.findAll();
    }

    // Service method to find a specific assignment by its ID
    public Optional<BugAssignment> getAssignmentById(int assignmentId) {
        return bugAssignmentRepo.findById(assignmentId);
    }

    // Service method to find all assignments for a specific bug
    public List<BugAssignment> getAssignmentsByBug(Bug bug) {
        return bugAssignmentRepo.findByBug(bug);
    }

    // Service method to find all bugs assigned to a specific user
    public List<BugAssignment> getAssignmentsByUser(User user) {
        return bugAssignmentRepo.findByUser(user);
    }

    // // Service method to check if a specific bug is assigned to a specific user
    // public boolean isBugAssignedToUser(Bug bug, User user) {
    //     return bugAssignmentRepo.existsByBugAndUser(bug, user);
    // }

    // // Service method to get all assignments made on a specific date
    // public List<BugAssignment> getAssignmentsByDate(Date assignedOn) {
    //     return bugAssignmentRepo.findByAssignedOn(assignedOn);
    // }

    // Service method to save or update an assignment
    public BugAssignment saveAssignment(BugAssignment bugAssignment) {
        return bugAssignmentRepo.save(bugAssignment);
    }

    // Service method to delete an assignment by its ID
    public void deleteAssignmentById(int assignmentId) {
        bugAssignmentRepo.deleteById(assignmentId);
    }

    // Getter method for BugAssignmentRepo
    public BugAssignmentRepo getBugAssignmentRepo() {
        return bugAssignmentRepo;
    }
}
