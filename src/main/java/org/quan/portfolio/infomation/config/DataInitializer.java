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

            skills.save(new Skill("GitHub"));
            Skill git = skills.save(new Skill("Git"));
            skills.save(new Skill("Microsoft SQL Server"));
            Skill java = skills.save(new Skill("Core Java"));
            Skill spring = skills.save(new Skill("Spring Boot"));
            Skill postgres = skills.save(new Skill("PostgreSQL"));
            Skill databaseDesign = skills.save(new Skill("Database Design"));
            Skill c = skills.save(new Skill("C"));
            Skill algorithms = skills.save(new Skill("Data Structures & Algorithms"));
            skills.save(new Skill("Object-Oriented Programming"));
            skills.save(new Skill("MVC Architecture"));
            skills.save(new Skill("RESTful APIs"));
            skills.save(new Skill("JavaScript"));
            skills.save(new Skill("CSS"));

            Project rht = new Project("RaceHorse Transport Management System",
                    "Cross-border racehorse transport system (team project). "
                            + "I designed the database and built the REST APIs for the driver module "
                            + "and the settlement process after a horse is delivered.",
                    "https://github.com/hankfwr23r2/SWP_RHT");
            rht.addSkill(java);
            rht.addSkill(spring);
            rht.addSkill(postgres);
            rht.addSkill(databaseDesign);
            projects.save(rht);

            Project eventSystem = new Project("Event Contributing Management System",
                    "C project developed with F Code. Implemented event search and filtering, member event history tracking, and data processing with data structures and algorithms.",
                    null);
            eventSystem.addSkill(c);
            eventSystem.addSkill(algorithms);
            eventSystem.addSkill(git);
            projects.save(eventSystem);
        };
    }
}
