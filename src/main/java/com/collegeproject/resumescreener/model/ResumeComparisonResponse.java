package com.collegeproject.resumescreener.model;

import java.util.List;

public class ResumeComparisonResponse {
    private String jobDescription;
    private List<ResumeAnalysis> analyses;
    public String getJobDescription() { return jobDescription; }
    public List<ResumeAnalysis> getAnalyses() { return analyses; }
    public void setJobDescription(String value) { jobDescription = value; }
    public void setAnalyses(List<ResumeAnalysis> value) { analyses = value; }
}
