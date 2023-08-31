package com.syn.projectsyn2.bugtracker.domain;


import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private int commentId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "commentedOn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentedOn;

    // Many comments can be created by one user
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;

    // Many comments can be associated with one bug
    @ManyToOne
    @JoinColumn(name = "bugId", referencedColumnName = "bugId", nullable = false)
    private Bug bug;

    // Default constructor required by JPA
    public Comment() {}

    // All-args constructor for creating an instance with all values
    public Comment(String content, Date commentedOn, User user, Bug bug) {
        this.content = content;
        this.commentedOn = commentedOn;
        this.user = user;
        this.bug = bug;
    }

    // Getter and setter methods

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentedOn() {
        return commentedOn;
    }

    public void setCommentedOn(Date commentedOn) {
        this.commentedOn = commentedOn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", commentedOn=" + commentedOn +
                ", user=" + user +
                ", bug=" + bug +
                '}';
    }
}

