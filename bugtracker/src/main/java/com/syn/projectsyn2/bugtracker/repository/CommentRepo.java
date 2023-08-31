package com.syn.projectsyn2.bugtracker.repository;

import com.syn.projectsyn2.bugtracker.domain.Comment;
import com.syn.projectsyn2.bugtracker.domain.User;
import com.syn.projectsyn2.bugtracker.domain.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

    // 1. Retrieve comments by a specific user
    List<Comment> findByUser(User user);

    // 2. Retrieve comments for a specific bug
    List<Comment> findByBug(Bug bug);

    // 3. Retrieve comments made after a specific date
    List<Comment> findByCommentedOnAfter(Date commentedOn);

    // 4. Retrieve comments made before a specific date
    List<Comment> findByCommentedOnBefore(Date commentedOn);

}