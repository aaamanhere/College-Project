package com.collegeproject.resumescreener.service;

import com.collegeproject.resumescreener.model.ResumeAnalysis;
import com.collegeproject.resumescreener.model.ResumeComparisonResponse;
import com.collegeproject.resumescreener.util.DocumentParser;
import com.collegeproject.resumescreener.util.RankingEngine;
import com.collegeproject.resumescreener.util.SkillExtractor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class ResumeProcessingService {
    private final DocumentParser parser; private final SkillExtractor extractor; private final RankingEngine ranking;
    public ResumeProcessingService(DocumentParser parser, SkillExtractor extractor, RankingEngine ranking) { this.parser = parser; this.extractor = extractor; this.ranking = ranking; }
    public ResumeComparisonResponse compare(List<MultipartFile> files, String jobDescription) {
        Set<String> jobSkills = extractor.extractSkills(jobDescription);
        List<ResumeAnalysis> analyses = files.stream().filter(file -> !file.isEmpty()).map(file -> analyze(file, jobDescription, jobSkills)).sorted(Comparator.comparingInt(ResumeAnalysis::getScore).reversed()).toList();
        for (int index = 0; index < analyses.size(); index++) analyses.get(index).setRank(index + 1);
        ResumeComparisonResponse response = new ResumeComparisonResponse(); response.setJobDescription(jobDescription); response.setAnalyses(analyses); return response;
    }
    private ResumeAnalysis analyze(MultipartFile file, String jobDescription, Set<String> jobSkills) {
        String text = parser.extractText(file); Set<String> skills = extractor.extractSkills(text);
        ResumeAnalysis result = new ResumeAnalysis(); result.setResumeFileName(file.getOriginalFilename()); result.setExtractedSkills(skills); result.setJobSkills(jobSkills); result.setMatchingSkills(ranking.findMatchingSkills(skills, jobSkills)); result.setScore(ranking.calculateScore(skills, text, jobSkills, jobDescription)); return result;
    }
}
