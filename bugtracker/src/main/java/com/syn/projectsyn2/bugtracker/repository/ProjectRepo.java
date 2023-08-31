package com.syn.projectsyn2.bugtracker.repository;

import com.syn.projectsyn2.bugtracker.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProjectRepo extends JpaRepository<Project, Integer> {
    Optional<Project> findByProjectName(String projectName);
    List<Project> findByStartDateAfterOrStartDateEquals(Date afterDate, Date equalsDate);
    List<Project> findByEndDateBeforeOrEndDateEquals(Date beforeDate, Date equalsDate);
    List<Project> findByEndDateIsNull();

    // Additional method to find projects by username
    @Query("SELECT p FROM Project p WHERE p.assignedTo.username = ?1")
    List<Project> findAllByAssignedToUsername(String username);
}
