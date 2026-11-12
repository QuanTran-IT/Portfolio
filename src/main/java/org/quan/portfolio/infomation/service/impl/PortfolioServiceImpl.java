package org.quan.portfolio.infomation.service.impl;

import org.quan.portfolio.infomation.config.ProfileProperties;
import org.quan.portfolio.infomation.dto.HealthDto;
import org.quan.portfolio.infomation.dto.ProfileDto;
import org.quan.portfolio.infomation.dto.ProjectDto;
import org.quan.portfolio.infomation.dto.SkillsDto;
import org.quan.portfolio.infomation.service.PortfolioService;
import org.quan.portfolio.infomation.service.ProjectService;
import org.quan.portfolio.infomation.service.SkillService;
import org.springframework.stereotype.Service;
import org.springframework.boot.SpringBootVersion;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import java.time.Instant;
import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    private final ProfileProperties profileProperties;
    private final SkillService skillService;
    private final ProjectService projectService;
    private final DataSource dataSource;

    public PortfolioServiceImpl(ProfileProperties profileProperties,
                                SkillService skillService,
                                ProjectService projectService,
                                DataSource dataSource) {
        this.profileProperties = profileProperties;
        this.skillService = skillService;
        this.projectService = projectService;
        this.dataSource = dataSource;
    }

    @Override
    public ProfileDto getProfile() {
        return new ProfileDto(
                profileProperties.fullName(), profileProperties.title(),
                "FPT University", "Computer Software Engineering (2024 - 2028)",
                8.29, profileProperties.email(), profileProperties.githubUrl(),
                "AVAILABLE_FOR_INTERNSHIPS",
                List.of("Java 21", "Spring Boot 3", "RESTful APIs", "PostgreSQL", "SQL Server")
        );
    }

    @Override
    public SkillsDto getSkills() {
        return new SkillsDto(
                List.of("Java 21", "Spring Boot 3", "Spring MVC", "Hibernate / JPA", "RESTful APIs"),
                List.of("PostgreSQL", "Microsoft SQL Server", "Database Design", "Relational Modeling"),
                List.of("C Language", "Data Structures & Algorithms", "Object-Oriented Programming (OOP)"),
                List.of("Git", "GitHub", "Maven", "Postman", "Linux Basics"),
                skillService.findAllNames()
        );
    }

    @Override
    public List<ProjectDto> getProjects() {
        return projectService.findAll();
    }

    @Override
    public HealthDto getHealth() {
        boolean databaseConnected;
        try (Connection connection = dataSource.getConnection()) {
            databaseConnected = connection.isValid(3);
        } catch (SQLException exception) {
            databaseConnected = false;
        }
        return new HealthDto(
                databaseConnected ? "UP" : "DOWN",
                "Spring Boot " + SpringBootVersion.getVersion(),
                System.getProperty("java.version", "21"),
                databaseConnected ? "CONNECTED" : "DISCONNECTED",
                Instant.now().toString(), profileProperties.fullName()
        );
    }
}
