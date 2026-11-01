package org.quan.portfolio.infomation.service;

import org.quan.portfolio.infomation.dto.ProjectDto;
import org.quan.portfolio.infomation.entity.Project;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> findAll();
}
