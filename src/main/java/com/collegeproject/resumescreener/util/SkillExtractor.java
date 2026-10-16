package com.collegeproject.resumescreener.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class SkillExtractor {
    private final Set<String> skills = new LinkedHashSet<>();
    @PostConstruct
    void loadSkills() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("skills.txt").getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) if (!line.isBlank()) skills.add(line.trim().toLowerCase(Locale.ENGLISH));
        } catch (Exception exception) { throw new IllegalStateException("Unable to load skill dictionary", exception); }
    }
    public Set<String> extractSkills(String text) {
        String normalized = " " + text.toLowerCase(Locale.ENGLISH).replaceAll("[^a-z0-9+#.]+", " ").replaceAll("\\s+", " ") + " ";
        Set<String> found = new LinkedHashSet<>();
        for (String skill : skills) {
            String pattern = "(^|[^a-z0-9+#.])" + Pattern.quote(skill) + "($|[^a-z0-9+#.])";
            if (Pattern.compile(pattern).matcher(normalized).find()) found.add(skill);
        }
        return found;
    }
}
