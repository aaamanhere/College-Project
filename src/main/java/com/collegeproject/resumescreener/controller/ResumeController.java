package com.collegeproject.resumescreener.controller;

import com.collegeproject.resumescreener.model.JobRole;
import com.collegeproject.resumescreener.model.ResumeComparisonResponse;
import com.collegeproject.resumescreener.service.JobRoleService;
import com.collegeproject.resumescreener.service.ResumeProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {
    private final JobRoleService roles; private final ResumeProcessingService processing;
    public ResumeController(JobRoleService roles, ResumeProcessingService processing) { this.roles = roles; this.processing = processing; }
    @GetMapping("/roles") public List<JobRole> getRoles() { return roles.findAll(); }
    @GetMapping("/roles/{id}") public ResponseEntity<JobRole> getRole(@PathVariable String id) { JobRole role = roles.findById(id); return role == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(role); }
    @PostMapping(value = "/compare", consumes = "multipart/form-data") public ResumeComparisonResponse compare(@RequestPart("resumes") List<MultipartFile> resumes, @RequestPart("jobDescription") String jobDescription) { return processing.compare(resumes, jobDescription); }
}
