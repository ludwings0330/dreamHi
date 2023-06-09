package com.elephant.dreamhi.model.entity;

import com.elephant.dreamhi.model.dto.FileDto;
import com.elephant.dreamhi.model.statics.UserRole;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "users")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class User extends BaseTimeEntity {

    @OneToMany(mappedBy = "follower")
    private final List<Follow> followers = new ArrayList<>();
    @OneToMany(mappedBy = "actor", orphanRemoval = true, cascade = CascadeType.ALL)
    private final List<Follow> followActors = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60, nullable = false, unique = true)
    private String email;
    @Column(length = 65, nullable = false)
    private String password;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 20)
    private String phone;
    @Column(nullable = false)
    @ColumnDefault("1")
    private Boolean activated;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ROLE_USER'")
    private UserRole role;
    @Embedded
    private Picture picture;

    public void setMainProfile(FileDto fileDto) {
        this.picture.updatePicture(fileDto);
    }

    public void changeName(String name) {
        this.name = name;
    }

}
