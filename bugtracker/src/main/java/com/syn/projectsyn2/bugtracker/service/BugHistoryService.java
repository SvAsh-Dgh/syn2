package com.syn.projectsyn2.bugtracker.service;

import com.syn.projectsyn2.bugtracker.domain.Bug;
import com.syn.projectsyn2.bugtracker.domain.BugHistory;
import com.syn.projectsyn2.bugtracker.repository.BugHistoryRepo;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BugHistoryService {

    private final BugHistoryRepo bugHistoryRepo;

    // Constructor-based dependency injection
    // @Autowired
    public BugHistoryService(BugHistoryRepo bugHistoryRepo) {
        this.bugHistoryRepo = bugHistoryRepo;
    }

    // Service method to retrieve all bug history records
    public List<BugHistory> getAllBugHistories() {
        return bugHistoryRepo.findAll();
    }

    // Service method to retrieve a specific bug history record by its ID
    public Optional<BugHistory> getBugHistoryById(int historyId) {
        return bugHistoryRepo.findById(historyId);
    }

    // Service method to retrieve the entire history of changes for a specific bug
    public List<BugHistory> getBugHistoryByBug(Bug bug) {
        return bugHistoryRepo.findByBugOrderByChangedOnDesc(bug);
    }

    // Service method to get the latest change for a specific bug
    public List<BugHistory> getLatestChangeByBug(Bug bug) {
        return bugHistoryRepo.findLatestByBug(bug);
    }

    // // Service method to retrieve all changes made on a specific date
    // public List<BugHistory> getChangesByDate(Date changedOn) {
    //     return bugHistoryRepo.findByChangedOn(changedOn);
    // }

    // // Service method to retrieve changes based on old or new values
    // public List<BugHistory> getChangesByValue(String oldValue, String newValue) {
    //     return bugHistoryRepo.findByOldValueOrNewValue(oldValue, newValue);
    // }

    // Service method to save or update a bug history record
    public BugHistory saveBugHistory(BugHistory bugHistory) {
        return bugHistoryRepo.save(bugHistory);
    }

    // Service method to delete a bug history record by its ID
    public void deleteBugHistoryById(int historyId) {
        bugHistoryRepo.deleteById(historyId);
    }

    // Getter method for BugHistoryRepo (Optional: Can be removed if you find it unneeded)
    public BugHistoryRepo getBugHistoryRepo() {
        return bugHistoryRepo;
    }
}
