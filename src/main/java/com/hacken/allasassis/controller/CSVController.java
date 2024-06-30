package com.hacken.allasassis.controller;

import com.hacken.allasassis.entity.CsvFile;
import com.hacken.allasassis.service.CsvFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CSVController {

    @Autowired
    private CsvFileService csvFileService;

    @GetMapping("/search")
    public ResponseEntity<List<CsvFile>> searchData(@RequestParam("query") String query) {
        return ResponseEntity.ok(csvFileService.searchCsvData(query));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            csvFileService.uploadCsvData(file);
            return ResponseEntity.ok("File uploaded and data stored successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to upload file: " + e.getMessage());
        }
    }
}
