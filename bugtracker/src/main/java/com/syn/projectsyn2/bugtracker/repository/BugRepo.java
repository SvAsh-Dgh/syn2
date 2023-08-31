package com.syn.projectsyn2.bugtracker.repository;

import com.syn.projectsyn2.bugtracker.domain.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BugRepo extends JpaRepository<Bug, Integer> {
    // Retrieve bugs based on their status
    List<Bug> findByStatus(Bug.BugStatus status);

    // Retrieve bugs based on their priority
    List<Bug> findByPriority(Bug.BugPriority priority);

    List<Bug> findByStatusAndPriority(Bug.BugStatus status, Bug.BugPriority priority);

        // Additional method to find bugs reported by a specific username
    @Query("SELECT b FROM Bug b WHERE b.reportedBy.username = ?1")
    List<Bug> findAllByReportedBy(String username);
}
