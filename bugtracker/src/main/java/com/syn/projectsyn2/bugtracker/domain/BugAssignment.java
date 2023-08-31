package com.syn.projectsyn2.bugtracker.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bugassignment")
public class BugAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignmentId")
    private int assignmentId;

    // Many BugAssignments can be related to one Bug
    @ManyToOne
    @JoinColumn(name = "bugId", referencedColumnName = "bugId", nullable = false)
    private Bug bug;

    // Many BugAssignments can be related to one User
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;

    @Column(name = "assignedOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date assignedOn;

    // Constructors
    public BugAssignment() {}

    public BugAssignment(Bug bug, User user, Date assignedOn) {
        this.bug = bug;
        this.user = user;
        this.assignedOn = assignedOn;
    }

    // Getters and setters
    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getAssignedOn() {
        return assignedOn;
    }

    public void setAssignedOn(Date assignedOn) {
        this.assignedOn = assignedOn;
    }
}