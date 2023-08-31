package com.syn.projectsyn2.bugtracker.service;

import com.syn.projectsyn2.bugtracker.domain.Project;
import com.syn.projectsyn2.bugtracker.repository.ProjectRepo;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepo projectRepo;

    // Constructor-based dependency injection
    // @Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    // Service method to retrieve all projects
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    // Service method to find a project by its ID
    public Optional<Project> getProjectById(int projectId) {
        return projectRepo.findById(projectId);
    }

    // Service method to find a project by its name
    public Optional<Project> getProjectByName(String projectName) {
        return projectRepo.findByProjectName(projectName);
    }

// // Service method to retrieve projects starting on or after a specific date
// public List<Project> getProjectsStartingOnOrAfter(Date date) {
//     return projectRepo.findByStartDateAfterOrStartDateEquals(date, date);
// }

// // Service method to retrieve projects ending on or before a specific date
// public List<Project> getProjectsEndingOnOrBefore(Date date) {
//     return projectRepo.findByEndDateBeforeOrEndDateEquals(date, date);
// }



    // Service method to retrieve ongoing projects (with no end date set)
    public List<Project> getOngoingProjects() {
        return projectRepo.findByEndDateIsNull();
    }

    // Service method to save or update a project
    public Project saveProject(Project project) {
        return projectRepo.save(project);
    }

    // Service method to delete a project by its ID
    public void deleteProjectById(int projectId) {
        projectRepo.deleteById(projectId);
    }

    // Getter method for ProjectRepo
    public ProjectRepo getProjectRepo() {
        return projectRepo;
    }
}
