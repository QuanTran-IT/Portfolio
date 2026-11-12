package org.quan.portfolio.infomation.controller;

import org.quan.portfolio.infomation.dto.HealthDto;
import org.quan.portfolio.infomation.dto.ProfileDto;
import org.quan.portfolio.infomation.dto.ProjectDto;
import org.quan.portfolio.infomation.dto.SkillsDto;
import org.quan.portfolio.infomation.service.PortfolioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST API powering the live developer terminal and portfolio data.
 */
@RestController
@RequestMapping("/api/v1/portfolio")
@CrossOrigin(origins = "*")
public class PortfolioApiController {
    private final PortfolioService portfolioService;

    public PortfolioApiController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getProfile() {
        ProfileDto profile = portfolioService.getProfile();
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/skills")
    public ResponseEntity<SkillsDto> getSkills() {
        SkillsDto skills = portfolioService.getSkills();
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDto>> getProjects() {
        List<ProjectDto> projects = portfolioService.getProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/health")
    public ResponseEntity<HealthDto> getHealth() {
        HealthDto health = portfolioService.getHealth();
        return ResponseEntity.status("UP".equals(health.status()) ? 200 : 503).body(health);
    }
}
