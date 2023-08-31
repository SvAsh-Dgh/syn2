package com.syn.projectsyn2.bugtracker.domain;


import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bughistory")
public class BugHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historyId")
    private int historyId;

    // Many bug history records can be associated with one bug
    @ManyToOne
    @JoinColumn(name = "bugId", referencedColumnName = "bugId", nullable = false)
    private Bug bug;

    @Column(name = "changedOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedOn;

    @Column(name = "oldValue", length = 255)
    private String oldValue;

    @Column(name = "newValue", length = 255)
    private String newValue;

    // Default constructor required by JPA
    public BugHistory() {}

    // All-args constructor for creating an instance with all values
    public BugHistory(Bug bug, Date changedOn, String oldValue, String newValue) {
        this.bug = bug;
        this.changedOn = changedOn;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    // Getter and setter methods

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    public Date getChangedOn() {
        return changedOn;
    }

    public void setChangedOn(Date changedOn) {
        this.changedOn = changedOn;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Override
    public String toString() {
        return "Bughistory{" +
                "historyId=" + historyId +
                ", bug=" + bug +
                ", changedOn=" + changedOn +
                ", oldValue='" + oldValue + '\'' +
                ", newValue='" + newValue + '\'' +
                '}';
    }
}