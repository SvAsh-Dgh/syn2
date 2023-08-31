package com.syn.projectsyn2.bugtracker.controller;

import com.syn.projectsyn2.bugtracker.domain.Bug;
import com.syn.projectsyn2.bugtracker.domain.Comment;
import com.syn.projectsyn2.bugtracker.domain.User;
import com.syn.projectsyn2.bugtracker.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Fetch all comments
    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    // Fetch a comment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable int id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Fetch comments made by a specific user
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUser(@PathVariable int userId) {
        User user = new User();  // Assuming you have a default constructor in the User entity
        user.setUserId(userId);
        List<Comment> comments = commentService.getCommentsByUser(user);
        if (comments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comments);
    }

    // Fetch comments associated with a specific bug
    @GetMapping("/by-bug/{bugId}")
    public ResponseEntity<List<Comment>> getCommentsByBug(@PathVariable int bugId) {
        Bug bug = new Bug();  // Assuming you have a default constructor in the Bug entity
        bug.setBugId(bugId);
        List<Comment> comments = commentService.getCommentsByBug(bug);
        if (comments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comments);
    }

    // Create a new comment
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.saveComment(comment);
        return ResponseEntity.ok(savedComment);
    }

    // Update comment details
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable int id, @RequestBody Comment updatedComment) {
        if (!commentService.getCommentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        updatedComment.setCommentId(id);  // Ensure the correct comment is updated
        Comment savedComment = commentService.saveComment(updatedComment);
        return ResponseEntity.ok(savedComment);
    }

    // Delete a comment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable int id) {
        if (!commentService.getCommentById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        commentService.deleteCommentById(id);
        return ResponseEntity.noContent().build();
    }
}
