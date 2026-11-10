package org.quan.portfolio.infomation.controller;

import org.quan.portfolio.infomation.config.ProfileProperties;
import org.quan.portfolio.infomation.dto.ProjectDto;
import org.quan.portfolio.infomation.service.ProjectService;
import org.quan.portfolio.infomation.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * REST API Controller powering the interactive live developer terminal
 * and providing programmatic access to Tran Le Anh Quan's portfolio data.
 */
@RestController
@RequestMapping("/api/v1/portfolio")
@CrossOrigin(origins = "*")
public class PortfolioApiController {

    private final ProfileProperties profileProperties;
    private final SkillService skillService;
    private final ProjectService projectService;

    public PortfolioApiController(ProfileProperties profileProperties,
                                  SkillService skillService,
                                  ProjectService projectService) {
        this.profileProperties = profileProperties;
        this.skillService = skillService;
        this.projectService = projectService;
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getProfile() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("name", profileProperties.fullName());
        data.put("title", profileProperties.title());
        data.put("university", "FPT University");
        data.put("major", "Computer Software Engineering (2024 - 2028)");
        data.put("gpa", 8.29);
        data.put("email", profileProperties.email());
        data.put("github", profileProperties.githubUrl());
        data.put("status", "AVAILABLE_FOR_INTERNSHIPS");
        data.put("primaryFocus", List.of("Java 21", "Spring Boot 3", "RESTful APIs", "PostgreSQL", "SQL Server"));
        return ResponseEntity.ok(data);
    }

    @GetMapping("/skills")
    public ResponseEntity<Map<String, Object>> getSkills() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("backend", List.of("Java 21", "Spring Boot 3", "Spring MVC", "Hibernate / JPA", "RESTful APIs"));
        data.put("databases", List.of("PostgreSQL", "Microsoft SQL Server", "Database Design", "Relational Modeling"));
        data.put("fundamentals", List.of("C Language", "Data Structures & Algorithms", "Object-Oriented Programming (OOP)"));
        data.put("tooling", List.of("Git", "GitHub", "Maven", "Postman", "Linux Basics"));
        data.put("allDatabaseSynced", skillService.findAllNames());
        return ResponseEntity.ok(data);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDto>> getProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> getHealth() {
        Map<String, Object> health = new LinkedHashMap<>();
        health.put("status", "UP");
        health.put("framework", "Spring Boot 3.5.x");
        health.put("javaVersion", System.getProperty("java.version", "21"));
        health.put("databaseStatus", "CONNECTED");
        health.put("timestamp", Instant.now().toString());
        health.put("developer", profileProperties.fullName());
        return ResponseEntity.ok(health);
    }
}
