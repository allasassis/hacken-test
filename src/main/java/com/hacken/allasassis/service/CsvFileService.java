package com.hacken.allasassis.service;

import com.hacken.allasassis.entity.CsvFile;
import com.hacken.allasassis.repository.CsvFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CsvFileService {

    @Autowired
    private CsvFileRepository csvFileRepository;

    public void uploadCsvData(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            String line;
            List<CsvFile> csvDataList = new ArrayList<>();
            String[] headers = null;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                if (headers == null) {
                    headers = values;
                    continue;
                }

                Map<String, String> csvRow = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    if (i < values.length) {
                        csvRow.put(headers[i], values[i]);
                    }
                }
                csvDataList.add(new CsvFile(csvRow));
            }

            reader.close();
            csvFileRepository.saveAll(csvDataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CsvFile> searchCsvData(String query) {
        List<CsvFile> results = new ArrayList<>();
        List<CsvFile> allData = csvFileRepository.findAll();

        for (CsvFile csvFile : allData) {
            for (Map.Entry<String, String> entry : csvFile.getAttributes().entrySet()) {
                if (entry.getValue().contains(query)) {
                    results.add(csvFile);
                    break;
                }
            }
        }

        return results;
    }
}
