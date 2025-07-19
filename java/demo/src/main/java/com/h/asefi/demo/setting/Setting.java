package com.h.asefi.demo.setting;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Setting {

    @Transient
    public static final String EntityTitle = "Setting";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String key;

    @Column(nullable = false)
    private String value;
}
