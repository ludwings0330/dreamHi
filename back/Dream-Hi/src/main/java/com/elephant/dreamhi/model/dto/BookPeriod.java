package com.elephant.dreamhi.model.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class BookPeriod {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

}
