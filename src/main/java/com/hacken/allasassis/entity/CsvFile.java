package com.hacken.allasassis.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Entity
@Getter
@NoArgsConstructor
public class CsvFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ElementCollection
    @CollectionTable(name = "csv_data_attributes", joinColumns = @JoinColumn(name = "csv_data_id"))
    @MapKeyColumn(name = "attribute_key")
    @Column(name = "attribute_value", length = 4000)
    private Map<String, String> attributes;

    public CsvFile(Map<String, String> csvRow) {
        this.attributes = csvRow;
    }
}
