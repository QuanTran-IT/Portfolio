package org.quan.portfolio.infomation.config;

import org.quan.portfolio.infomation.entity.Project;
import org.quan.portfolio.infomation.entity.Skill;
import org.quan.portfolio.infomation.repository.ProjectRepository;
import org.quan.portfolio.infomation.repository.SkillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seed(ProjectRepository projects, SkillRepository skills) {
        return args -> {
            if (skills.count() > 0 || projects.count() > 0) {
                return; // data already exists, skip
            }

            Skill java = skills.save(new Skill("Java"));
            Skill spring = skills.save(new Skill("Spring Boot"));
            Skill postgres = skills.save(new Skill("PostgreSQL"));
            Skill security = skills.save(new Skill("Spring Security"));
            Skill jwt = skills.save(new Skill("JWT"));
            skills.save(new Skill("Git"));

            Project rht = new Project("RaceHorse Transport",
                    "Cross-border racehorse transport system (team project). "
                            + "I designed the database and built the REST APIs for the driver module "
                            + "and the settlement process after a horse is delivered.",
                    "https://github.com/hankfwr23r2/SWP_RHT");
            rht.addSkill(java);
            rht.addSkill(spring);
            rht.addSkill(postgres);
            rht.addSkill(security);
            rht.addSkill(jwt);
            projects.save(rht);
        };
    }
}