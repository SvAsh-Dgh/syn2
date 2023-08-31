package com.syn.projectsyn2.bugtracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.syn.projectsyn2.bugtracker.domain.Bug;
import com.syn.projectsyn2.bugtracker.domain.BugHistory;

import java.util.Date;
import java.util.List;

public interface BugHistoryRepo extends JpaRepository<BugHistory, Integer> {

    // 1. Retrieve the entire history of changes for a specific bug
    List<BugHistory> findByBugOrderByChangedOnDesc(Bug bug);

    // 2. Get the latest change for a specific bug
    @Query("SELECT bh FROM BugHistory bh WHERE bh.bug = :bug ORDER BY bh.changedOn DESC")
    List<BugHistory> findLatestByBug(@Param("bug") Bug bug);

    // 3. Find all changes made on a specific date
    List<BugHistory> findByChangedOn(Date changedOn);

    // 4. Retrieve changes based on old or new values (if needed)
    List<BugHistory> findByOldValueOrNewValue(String oldValue, String newValue);

}