package com.a701.nongstradamus.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "origin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OriginEntity {

    @Id
    @Column(name = "originId")
    private Long id;

    @Column(name = "originName")
    private String name;
}
