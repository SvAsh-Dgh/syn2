package com.syn.projectsyn2.bugtracker.service;

import com.syn.projectsyn2.bugtracker.domain.User;
import com.syn.projectsyn2.bugtracker.domain.Role;
import com.syn.projectsyn2.bugtracker.dto.LoginDto;
import com.syn.projectsyn2.bugtracker.dto.RegDto;
import com.syn.projectsyn2.bugtracker.domain.Project;
import com.syn.projectsyn2.bugtracker.domain.Bug;
import com.syn.projectsyn2.bugtracker.repository.BugRepo;
import com.syn.projectsyn2.bugtracker.repository.ProjectRepo;
import com.syn.projectsyn2.bugtracker.repository.RoleRepo;
import com.syn.projectsyn2.bugtracker.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface UserService {
    // Existing methods
    Optional<User> getUserByUsername(String username);
    List<Project> getAssignedProjectsByUsername(String username);
    List<Bug> getAllBugsByUsername(String username);
    List<User> getAllUsersWithRolesAndProjects();
    void registerNewUser(RegDto user);
    boolean userExistsByUsername(String username);
    void setDefaultRoleForUser(User user, String roleName);
    boolean validateUser(LoginDto loginDto);
}

@Service
class UserServiceImpl implements UserService {

    @Autowired
    private RoleRepo roleRepository;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private ProjectRepo projectRepository;
    @Autowired
    private BugRepo bugRepository;

    public boolean validateUser(LoginDto loginDto) {
        Optional<User> optionalUser = userRepository.findByUsername(loginDto.getUsername());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String hashedPassword = hashPassword(loginDto.getPassword(), user.getSalt());
            
            return hashedPassword.equals(user.getPassword());
        }

        return false;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<Project> getAssignedProjectsByUsername(String username) {
        return projectRepository.findAllByAssignedToUsername(username);
    }

    @Override
    public List<Bug> getAllBugsByUsername(String username) {
        return bugRepository.findAllByReportedBy(username);
    }

    @Override
    public List<User> getAllUsersWithRolesAndProjects() {
        return userRepository.findAllWithRolesAndProjects();
    }

    @Override
    public void registerNewUser(RegDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());

        // Generate salt and hash password
        byte[] newSalt = generateSalt();
        String hashedPassword = hashPassword(userDto.getPassword(), newSalt);
        newUser.setSalt(newSalt);
        newUser.setPassword(hashedPassword);

        // Set default role


        userRepository.save(newUser);
    }

@Override
public void setDefaultRoleForUser(User user, String roleName) {
    Optional<Role> defaultRole = roleRepository.findByName(roleName);
    if (defaultRole.isPresent()) {
        user.setRole(defaultRole.get());
    } else {
        throw new RuntimeException("Default role " + roleName + " not found");
    }
}


    // Existing helper methods
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    @Override
    public boolean userExistsByUsername(String username) {
        throw new UnsupportedOperationException("Unimplemented method 'userExistsByUsername'");
    }
}
