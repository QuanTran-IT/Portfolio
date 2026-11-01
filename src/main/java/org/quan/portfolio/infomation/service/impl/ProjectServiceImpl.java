package org.quan.portfolio.infomation.service.impl;

import org.quan.portfolio.infomation.dto.ProjectDto;
import org.quan.portfolio.infomation.entity.Project;
import org.quan.portfolio.infomation.repository.ProjectRepository;
import org.quan.portfolio.infomation.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream().map(ProjectDto::convertEntityToDto).toList();
    }
}
