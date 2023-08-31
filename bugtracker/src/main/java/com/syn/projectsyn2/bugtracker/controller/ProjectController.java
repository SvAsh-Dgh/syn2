package com.syn.projectsyn2.bugtracker.controller;

import com.syn.projectsyn2.bugtracker.domain.Project;
import com.syn.projectsyn2.bugtracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Fetch all projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    // Fetch a project by ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) {
        Optional<Project> project = projectService.getProjectById(id);
        
        // Using Optional with lambdas for cleaner handling
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Fetch a project by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Project> getProjectByName(@PathVariable String name) {
        Optional<Project> project = projectService.getProjectByName(name);
        
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // // Fetch projects starting on or after a specific date
    // @GetMapping("/starting-after")
    // public ResponseEntity<List<Project>> getProjectsStartingOnOrAfter(@RequestParam Date date) {
    //     List<Project> projects = projectService.getProjectsStartingOnOrAfter(date);
        
    //     if (!projects.isEmpty()) {
    //         return ResponseEntity.ok(projects);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // // Fetch projects ending on or before a specific date
    // @GetMapping("/ending-before")
    // public ResponseEntity<List<Project>> getProjectsEndingOnOrBefore(@RequestParam Date date) {
    //     List<Project> projects = projectService.getProjectsEndingOnOrBefore(date);
        
    //     if (!projects.isEmpty()) {
    //         return ResponseEntity.ok(projects);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    // Fetch ongoing projects (no end date)
    @GetMapping("/ongoing")
    public List<Project> getOngoingProjects() {
        return projectService.getOngoingProjects();
    }

    // Create a new project
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        // Basic check: If project with same name exists, then don't create a new one
        if (projectService.getProjectByName(project.getProjectName()).isPresent()) {
            return ResponseEntity.status(409).body(null);  // 409 Conflict
        }

        Project savedProject = projectService.saveProject(project);
        return ResponseEntity.ok(savedProject);
    }

    // Update project details
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable int id, @RequestBody Project updatedProject) {
        if(!projectService.getProjectById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Setting the ID from the path variable ensures the correct project is updated
        updatedProject.setProjectId(id);
        Project savedProject = projectService.saveProject(updatedProject);
        return ResponseEntity.ok(savedProject);
    }

    // Delete a project by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        if(!projectService.getProjectById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        projectService.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }
}
