package org.quan.portfolio.infomation.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 500)
    private String description;

    private String link;

    private LocalDate createdAt = LocalDate.now();

    @ManyToMany
    @JoinTable(
            name = "project_skill",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    protected Project() {}

    public Project(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public void addSkill(Skill skill) { this.skills.add(skill); }
    public void removeSkill(Skill skill) { this.skills.remove(skill); }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getLink() { return link; }
    public LocalDate getCreatedAt() { return createdAt; }
    public Set<Skill> getSkills() { return skills; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setLink(String link) { this.link = link; }
}