package com.hacken.allasassis.repository;

import com.hacken.allasassis.entity.CsvFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsvFileRepository extends JpaRepository<CsvFile, Long> {
}
