package com.artivisi.school.onlinetest.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="t_pilihan")
public class Pilihan {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @Column(nullable = false)
    @Min(0)
    private Integer urutan;

    @NotNull
    @Column(nullable = false)
    private String kode;

    @Column(nullable = false)
    private String isi;
}
