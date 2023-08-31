package com.syn.projectsyn2.bugtracker.service;

import com.syn.projectsyn2.bugtracker.domain.Bug;
import com.syn.projectsyn2.bugtracker.domain.Comment;
import com.syn.projectsyn2.bugtracker.domain.User;
import com.syn.projectsyn2.bugtracker.repository.CommentRepo;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepo commentRepo;

    // Constructor-based dependency injection
    // @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    // Service method to retrieve all comments
    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    // Service method to retrieve a specific comment by its ID
    public Optional<Comment> getCommentById(int commentId) {
        return commentRepo.findById(commentId);
    }

    // Service method to retrieve comments made by a specific user
    public List<Comment> getCommentsByUser(User user) {
        return commentRepo.findByUser(user);
    }

    // Service method to retrieve comments associated with a specific bug
    public List<Comment> getCommentsByBug(Bug bug) {
        return commentRepo.findByBug(bug);
    }

    // Service method to retrieve comments made after a specific date
    public List<Comment> getCommentsAfterDate(Date commentedOn) {
        return commentRepo.findByCommentedOnAfter(commentedOn);
    }

    // // Service method to retrieve comments made before a specific date
    // public List<Comment> getCommentsBeforeDate(Date commentedOn) {
    //     return commentRepo.findByCommentedOnBefore(commentedOn);
    // }

    // Service method to save or update a comment
    public Comment saveComment(Comment comment) {
        return commentRepo.save(comment);
    }

    // Service method to delete a comment by its ID
    public void deleteCommentById(int commentId) {
        commentRepo.deleteById(commentId);
    }

    // Getter method for CommentRepo (Optional: Can be removed if you find it unneeded)
    public CommentRepo getCommentRepo() {
        return commentRepo;
    }
}
