package com.collegeproject.resumescreener.util;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Component
public class DocumentParser {
    private final Tika tika = new Tika();
    public String extractText(MultipartFile file) {
        try { return tika.parseToString(file.getInputStream()).trim(); }
        catch (Exception exception) { throw new IllegalArgumentException("Unable to read " + file.getOriginalFilename() + ". Upload a valid PDF, DOC, or DOCX file.", exception); }
    }
}
