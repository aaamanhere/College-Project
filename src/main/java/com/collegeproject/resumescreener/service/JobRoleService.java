package com.collegeproject.resumescreener.service;

import com.collegeproject.resumescreener.model.JobRole;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobRoleService {
    private final List<JobRole> roles = List.of(
        new JobRole("java-developer", "Java Developer", "Java developer with Spring Boot, REST API, microservices, Hibernate, JPA, SQL, Git, Maven, and database experience."),
        new JobRole("data-scientist", "Data Scientist", "Data scientist with Python, machine learning, data analysis, SQL, TensorFlow, PyTorch, and visualization skills."),
        new JobRole("devops-engineer", "DevOps Engineer", "DevOps engineer with Docker, Kubernetes, CI/CD, AWS, Linux, Jenkins, monitoring, and infrastructure automation skills."),
        new JobRole("frontend-engineer", "Frontend Engineer", "Frontend engineer with HTML, CSS, JavaScript, React, Angular, UI/UX, Git, and responsive web development experience."),
        new JobRole("qa-engineer", "QA Engineer", "QA engineer with Selenium, JUnit, test automation, manual testing, Jira, quality assurance, and bug tracking skills.")
    );
    public List<JobRole> findAll() { return roles; }
    public JobRole findById(String id) { return roles.stream().filter(role -> role.getId().equals(id)).findFirst().orElse(null); }
}
