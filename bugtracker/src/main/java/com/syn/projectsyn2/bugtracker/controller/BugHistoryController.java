package com.syn.projectsyn2.bugtracker.controller;

import com.syn.projectsyn2.bugtracker.domain.Bug;
import com.syn.projectsyn2.bugtracker.domain.BugHistory;
import com.syn.projectsyn2.bugtracker.service.BugHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bughistories")
public class BugHistoryController {

    @Autowired
    private BugHistoryService bugHistoryService;

    // Fetch all bug history records
    @GetMapping
    public List<BugHistory> getAllBugHistories() {
        return bugHistoryService.getAllBugHistories();
    }

    // Fetch a specific bug history record by its ID
    @GetMapping("/{id}")
    public ResponseEntity<BugHistory> getBugHistoryById(@PathVariable int id) {
        Optional<BugHistory> bugHistory = bugHistoryService.getBugHistoryById(id);
        return bugHistory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Fetch the entire history of changes for a specific bug
    @GetMapping("/by-bug/{bugId}")
    public ResponseEntity<List<BugHistory>> getBugHistoryByBug(@PathVariable int bugId) {
        Bug bug = new Bug();  // Assuming you have a default constructor in the Bug entity
        bug.setBugId(bugId);
        List<BugHistory> histories = bugHistoryService.getBugHistoryByBug(bug);
        if (histories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(histories);
    }

    // Fetch the latest change for a specific bug
    @GetMapping("/latest-by-bug/{bugId}")
    public ResponseEntity<List<BugHistory>> getLatestChangeByBug(@PathVariable int bugId) {
        Bug bug = new Bug();  // Assuming you have a default constructor in the Bug entity
        bug.setBugId(bugId);
        List<BugHistory> latestChange = bugHistoryService.getLatestChangeByBug(bug);
        if (latestChange.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(latestChange);
    }

    // // Fetch changes made on a specific date
    // @GetMapping("/by-date")
    // public ResponseEntity<List<BugHistory>> getChangesByDate(@RequestParam Date changedOn) {
    //     List<BugHistory> changes = bugHistoryService.getChangesByDate(changedOn);
    //     if (changes.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     return ResponseEntity.ok(changes);
    // }

    // // Fetch changes based on old or new values
    // @GetMapping("/by-value")
    // public ResponseEntity<List<BugHistory>> getChangesByValue(@RequestParam String oldValue, @RequestParam String newValue) {
    //     List<BugHistory> changes = bugHistoryService.getChangesByValue(oldValue, newValue);
    //     if (changes.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     return ResponseEntity.ok(changes);
    // }

    // Create a new bug history record
    @PostMapping
    public ResponseEntity<BugHistory> createBugHistory(@RequestBody BugHistory bugHistory) {
        BugHistory savedBugHistory = bugHistoryService.saveBugHistory(bugHistory);
        return ResponseEntity.ok(savedBugHistory);
    }

    // Update a bug history record
    @PutMapping("/{id}")
    public ResponseEntity<BugHistory> updateBugHistory(@PathVariable int id, @RequestBody BugHistory updatedBugHistory) {
        if (!bugHistoryService.getBugHistoryById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        updatedBugHistory.setHistoryId(id);  // Ensure the correct record is updated
        BugHistory savedBugHistory = bugHistoryService.saveBugHistory(updatedBugHistory);
        return ResponseEntity.ok(savedBugHistory);
    }

    // Delete a bug history record by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBugHistory(@PathVariable int id) {
        if (!bugHistoryService.getBugHistoryById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        bugHistoryService.deleteBugHistoryById(id);
        return ResponseEntity.noContent().build();
    }
}
