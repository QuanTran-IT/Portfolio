package org.quan.portfolio.infomation.service.impl;

import org.quan.portfolio.infomation.config.ProfileProperties;
import org.quan.portfolio.infomation.dto.HomePageDto;
import org.quan.portfolio.infomation.service.HomePageService;
import org.quan.portfolio.infomation.service.ProjectService;
import org.quan.portfolio.infomation.service.SkillService;
import org.springframework.stereotype.Service;

@Service
public class HomePageServiceImpl implements HomePageService {
    private final ProfileProperties profileProperties;
    private final SkillService skillService;
    private final ProjectService projectService;

    public HomePageServiceImpl(ProfileProperties profileProperties,
                               SkillService skillService,
                               ProjectService projectService) {
        this.profileProperties = profileProperties;
        this.skillService = skillService;
        this.projectService = projectService;
    }

    @Override
    public HomePageDto getHomePage() {
        return new HomePageDto(
                profileProperties.fullName(),
                profileProperties.title(),
                profileProperties.about(),
                profileProperties.email(),
                profileProperties.githubUrl(),
                skillService.findAllNames(),
                projectService.findAll()
        );
    }
}
