package com.elephant.dreamhi.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "user")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false, unique = true)
    private String email;

    @Column(length = 65, nullable = false)
    private String password;

    @Column(nullable = false)
    @ColumnDefault("1")
    private Boolean activated;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ROLE_USER'")
    private UserRole role;

    @OneToMany(mappedBy = "reporter")
    private List<Report> reportingHistories = new ArrayList<>();

    @OneToMany(mappedBy = "suspect")
    private List<Report> reportedHistories = new ArrayList<>();

}
