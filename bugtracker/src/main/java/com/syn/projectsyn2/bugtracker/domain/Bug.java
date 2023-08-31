package com.syn.projectsyn2.bugtracker.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bug")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bugId")
    private int bugId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BugStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private BugPriority priority;

    @Column(name = "creationDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "lastUpdated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name = "projectId", referencedColumnName = "projectId")
    private Project project;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User reportedBy;

    public enum BugStatus {
        NEW,
        IN_PROGRESS,
        RESOLVED
    }

    public enum BugPriority {
        LOW,
        MEDIUM,
        HIGH
    }

    public Bug() {
    }

    public Bug(String title, String description, BugStatus status, BugPriority priority, Date creationDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.creationDate = creationDate;
        this.lastUpdated = creationDate;
    }

    public int getBugId() {
        return bugId;
    }

    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }

    public BugPriority getPriority() {
        return priority;
    }

    public void setPriority(BugPriority priority) {
        this.priority = priority;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
