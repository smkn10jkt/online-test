package com.artivisi.school.onlinetest.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name="t_pertanyaan")
public class Pertanyaan {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    @Lob
    private String isi;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String jawaban;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    @Min(1)
    private Integer bobot;

}
