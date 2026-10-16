package com.collegeproject.resumescreener.model;

import java.util.Set;

public class ResumeAnalysis {
    private String resumeFileName;
    private Set<String> extractedSkills;
    private Set<String> matchingSkills;
    private Set<String> jobSkills;
    private int score;
    private int rank;
    public String getResumeFileName() { return resumeFileName; }
    public Set<String> getExtractedSkills() { return extractedSkills; }
    public Set<String> getMatchingSkills() { return matchingSkills; }
    public Set<String> getJobSkills() { return jobSkills; }
    public int getScore() { return score; }
    public int getRank() { return rank; }
    public void setResumeFileName(String value) { resumeFileName = value; }
    public void setExtractedSkills(Set<String> value) { extractedSkills = value; }
    public void setMatchingSkills(Set<String> value) { matchingSkills = value; }
    public void setJobSkills(Set<String> value) { jobSkills = value; }
    public void setScore(int value) { score = value; }
    public void setRank(int value) { rank = value; }
}
