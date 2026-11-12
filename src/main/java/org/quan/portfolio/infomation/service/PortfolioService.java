package org.quan.portfolio.infomation.service;

import org.quan.portfolio.infomation.dto.HealthDto;
import org.quan.portfolio.infomation.dto.ProfileDto;
import org.quan.portfolio.infomation.dto.ProjectDto;
import org.quan.portfolio.infomation.dto.SkillsDto;

import java.util.List;

public interface PortfolioService {
    ProfileDto getProfile();
    SkillsDto getSkills();
    List<ProjectDto> getProjects();
    HealthDto getHealth();
}
