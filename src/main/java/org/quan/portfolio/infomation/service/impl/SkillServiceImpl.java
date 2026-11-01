package org.quan.portfolio.infomation.service.impl;

import org.quan.portfolio.infomation.entity.Skill;
import org.quan.portfolio.infomation.repository.SkillRepository;
import org.quan.portfolio.infomation.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SkillServiceImpl implements SkillService {
private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<String> findAllNames() {
        return skillRepository.findAll().stream().map(Skill::getName).toList();
    }
}
