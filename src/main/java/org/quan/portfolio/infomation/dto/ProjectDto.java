package org.quan.portfolio.infomation.dto;

import org.quan.portfolio.infomation.entity.Project;
import org.quan.portfolio.infomation.entity.Skill;

import java.time.LocalDate;
import java.util.List;

public record ProjectDto(Long id,
                         String name,
                         String description,
                         String link,
                         LocalDate createdAt,
                         List<String> skills) {

    public static ProjectDto convertEntityToDto(Project project) {
        List<String> skillNames = project.getSkills().stream()
                .map(Skill::getName)
                .sorted()
                .toList();

        return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getLink(),
                project.getCreatedAt(),
                skillNames
        );
    }
}