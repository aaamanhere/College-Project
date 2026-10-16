package com.collegeproject.resumescreener.util;

import org.springframework.stereotype.Component;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class RankingEngine {
    public Set<String> findMatchingSkills(Set<String> resumeSkills, Set<String> jobSkills) {
        Set<String> matches = new LinkedHashSet<>(resumeSkills); matches.retainAll(jobSkills); return matches;
    }
    public int calculateScore(Set<String> resumeSkills, String resumeText, Set<String> jobSkills, String jobDescription) {
        double skill = jobSkills.isEmpty() ? 0 : (double) findMatchingSkills(resumeSkills, jobSkills).size() / jobSkills.size();
        double keywords = similarity(tokens(resumeText), tokens(jobDescription));
        String text = resumeText.toLowerCase();
        double experience = Pattern.compile("\\b\\d+\\+?\\s+years?\\b").matcher(text).find() ? .45 : 0;
        if (Pattern.compile("\\b(senior|lead|principal|architect)\\b").matcher(text).find()) experience += .35;
        if (Pattern.compile("\\b(manager|director|head)\\b").matcher(text).find()) experience += .20;
        double education = Pattern.compile("\\b(phd|ph.d|doctorate|master|mba|bachelor|b.tech|b.e)\\b").matcher(text).find() ? .6 : 0;
        if (Pattern.compile("\\b(certified|certification|certificate)\\b").matcher(text).find()) education += .4;
        return (int) Math.round(Math.min(100, skill * 60 + keywords * 20 + Math.min(1, experience) * 15 + Math.min(1, education) * 5));
    }
    private Set<String> tokens(String text) { return Pattern.compile("[^a-z0-9]+", Pattern.CASE_INSENSITIVE).splitAsStream(text.toLowerCase()).filter(word -> word.length() > 2).collect(Collectors.toSet()); }
    private double similarity(Set<String> first, Set<String> second) { Set<String> common = new LinkedHashSet<>(first); common.retainAll(second); int union = first.size() + second.size() - common.size(); return union == 0 ? 0 : (double) common.size() / union; }
}
