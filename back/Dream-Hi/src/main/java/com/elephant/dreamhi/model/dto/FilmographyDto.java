package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Filmography;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
public class FilmographyDto {

    private Long id;

    private List<Filmo> filmographies = new ArrayList<>();

    @Builder
    public FilmographyDto(Long id, List<Filmography> filmographies) {
        this.id = id;

        filmographies.forEach(f -> {
            Filmo filmo = Filmo.builder()
                               .id(f.getId())
                               .description(f.getDescription())
                               .url(f.getUrl())
                               .title(f.getTitle())
                               .build();
            this.filmographies.add(filmo);
        });
    }

    @Builder
    @Getter
    @Setter
    static class Filmo {

        private Long id;
        private String description;
        private String title;
        private String url;

    }

}
