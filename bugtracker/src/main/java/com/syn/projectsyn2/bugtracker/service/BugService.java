package com.syn.projectsyn2.bugtracker.service;

import com.syn.projectsyn2.bugtracker.domain.Bug;
import com.syn.projectsyn2.bugtracker.repository.BugRepo;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugService {

    private final BugRepo bugRepo;

    // Constructor-based dependency injection
    // @Autowired
    public BugService(BugRepo bugRepo) {
        this.bugRepo = bugRepo;
    }

    // Retrieve all bugs
    public List<Bug> getAllBugs() {
        return bugRepo.findAll();
    }

    // Retrieve bugs based on their status
    public List<Bug> getBugsByStatus(Bug.BugStatus status) {
        return bugRepo.findByStatus(status);
    }

    // Retrieve bugs based on their priority
    public List<Bug> getBugsByPriority(Bug.BugPriority priority) {
        return bugRepo.findByPriority(priority);
    }

    // Save or update a bug
    public Bug saveBug(Bug bug) {
        return bugRepo.save(bug);
    }

    // Delete a bug by its ID
    public void deleteBugById(int bugId) {
        bugRepo.deleteById(bugId);
    }

    public List<Bug> getBugsByStatusAndPriority(Bug.BugStatus status, Bug.BugPriority priority) {
        return bugRepo.findByStatusAndPriority(status, priority);
    }
}
