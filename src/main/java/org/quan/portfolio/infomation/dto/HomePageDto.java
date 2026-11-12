package org.quan.portfolio.infomation.dto;

import java.util.List;

public record HomePageDto(
        String name,
        String title,
        String about,
        String email,
        String githubUrl,
        List<String> skills,
        List<ProjectDto> projects
) {
}
