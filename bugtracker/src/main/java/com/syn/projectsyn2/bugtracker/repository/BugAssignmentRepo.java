package com.syn.projectsyn2.bugtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.syn.projectsyn2.bugtracker.domain.BugAssignment;
import com.syn.projectsyn2.bugtracker.domain.User;
import com.syn.projectsyn2.bugtracker.domain.Bug;

import java.util.Date;
import java.util.List;

public interface BugAssignmentRepo extends JpaRepository<BugAssignment, Integer> {

    // 1. Find all assignments for a specific bug
    List<BugAssignment> findByBug(Bug bug);

    // 2. Find all bugs assigned to a specific user
    List<BugAssignment> findByUser(User user);

    // 3. Check if a particular bug is assigned to a particular user
    boolean existsByBugAndUser(Bug bug, User user);

    // 4. Get all assignments made on a specific date
    List<BugAssignment> findByAssignedOn(Date assignedOn);

}