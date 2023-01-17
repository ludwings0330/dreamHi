package com.elephant.dreamhi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Getter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 45)
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max = 65)
    private String password;

    @NotNull
    @Size(max = 21)
    private String name;

    @Size(max = 11)
    private String phone;

    @NotNull
    @ColumnDefault("1")
    private Boolean activated;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @ColumnDefault("'ROLE_USER'")
    @NotNull
    private UserRole role;

}
