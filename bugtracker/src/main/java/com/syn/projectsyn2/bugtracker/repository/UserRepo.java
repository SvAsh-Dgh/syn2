package com.syn.projectsyn2.bugtracker.repository;

import com.syn.projectsyn2.bugtracker.domain.User;
import com.syn.projectsyn2.bugtracker.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Query("SELECT u.salt FROM User u WHERE u.username = ?1")
    Optional<byte[]> findSaltByUsername(String username);
    List<User> findByRole(Role role);

    // Additional method to fetch all users with roles and projects
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.role LEFT JOIN FETCH u.assignedProjects")
    List<User> findAllWithRolesAndProjects();
}
