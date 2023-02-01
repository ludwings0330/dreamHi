package com.elephant.dreamhi.model.dto;

import com.elephant.dreamhi.model.entity.Producer;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProducerAnnouncementDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    public static ProducerAnnouncementDto entityToDto(Producer producer) {
        return ProducerAnnouncementDto.builder()
                                      .id(producer.getId())
                                      .name(producer.getName())
                                      .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProducerAnnouncementDto that = (ProducerAnnouncementDto) o;

        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

}
